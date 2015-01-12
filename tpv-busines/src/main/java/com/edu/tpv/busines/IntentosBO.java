package com.edu.tpv.busines;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.BdConstant;
import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.Intentos;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.IntentosVO;

public class IntentosBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private IntentosBO intentosBO = this;
	private int tipoTransaccion = 0;
	private Intentos intentos = new Intentos();
	private Usuarios usuarios=null;
	private ArrayList<IntentosVO> lstIntentosVO = new ArrayList<IntentosVO>();
	private long minutos=0;
	private long segundos=0;
	private Timestamp fechaActual;

	public IntentosBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		switch(tipoTransaccion)
		{
			case 1:
				Intentos ultimoIntentosId = new Intentos();
				ultimoIntentosId = (Intentos) unParcelSession.getLastIdSintTx(ultimoIntentosId);
				intentos.setId(ultimoIntentosId.getId());
				unParcelSession.saveSinTx(intentos);
				break;
			case 2:
				unParcelSession.modifySinTx(intentos);
				
				break;
			case 3:
				unParcelSession.deleteSintTx(intentos);
				break;
			case 4:
				unParcelSession.executeACIDSinTx(intentos);
				unParcelSession.executeACIDSinTx(usuarios);
		}
		
		return this;
	}

	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Vector<InterfaceEntityBase> intentosV = null;
		Iterator<InterfaceEntityBase> intentosIt = null;
		Usuarios usuarios = null;
		switch(tipoTransaccion)
		{
			case 4:
				intentosV = packageDAO.findByQuerySinTx(this.intentos);
				if(intentosV!=null && intentosV.size()>0)
				{
					try{
						intentosIt = intentosV.iterator();
						while (intentosIt.hasNext()) {
							intentos = (Intentos) intentosIt.next();
						}
						usuarios = new Usuarios();
						usuarios.setId(intentos.getUsuarios().getId());
						intentos.setUsuarios((Usuarios) packageDAO.getById(usuarios));
						intentos.setStatus(BdConstant.EXITO);
						
						fechaActual=unParcelSession.getDateCurrentSinTx();
						
						if(fechaActual.compareTo(intentos.getUsuarios().getUltimaFechaAcceso())>0)
						{
							   long diferenciaMils = fechaActual.getTime() - intentos.getUsuarios().getUltimaFechaAcceso().getTime();			 

							   segundos = diferenciaMils / 1000;
							   long horas = segundos / 3600;
							   segundos -= horas*3600;
							   minutos = segundos /60;
							   segundos -= minutos*60;
							   
						}else
						{
							intentos.setStatus(BdConstant.ERROR);
							intentos.setMsgView("Verifique la fecha de su ordenador");
						}
					}catch(Exception e){
						intentos.setStatus(BdConstant.ERROR);
						intentos.setMsgView("Intente accesar nuevamente...");	
					}
	
				}else
					intentos.setStatus(BdConstant.OBJETO_VACIO);
				break;
			case 5:
				intentosV = packageDAO.listDataSin(intentos);
				intentosIt = intentosV.iterator();
				while (intentosIt.hasNext()) {
					intentos = (Intentos) intentosIt.next();
					usuarios = new Usuarios();
					usuarios.setId(intentos.getUsuarios().getId());
					intentos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
					lstIntentosVO.add(new IntentosVO(intentos));
				}
				break;
			case 6:
				intentos = (Intentos) packageDAO.getTotalRegSinTx(this.intentos);
		}
		return this;
	}

	public InterfaceEntityBase guardarIntentos(InterfaceEntityBase intentosVO) throws Exception {
		tipoTransaccion = 1;
		this.intentos = new Intentos((IntentosVO) intentosVO);
		intentosBO = (IntentosBO) packageDAO.launchesTransaction(this);
		return new IntentosVO(intentosBO.intentos);
	}

	public InterfaceEntityBase modificarIntentos(InterfaceEntityBase intentosVO) throws Exception {
		tipoTransaccion = 2;
		this.intentos = new Intentos((IntentosVO) intentosVO);
		intentosBO = (IntentosBO) packageDAO.launchesTransaction(this);
		return new IntentosVO(intentosBO.intentos);
	}

	public InterfaceEntityBase eliminarIntentos(InterfaceEntityBase intentosVO) throws Exception {
		tipoTransaccion = 3;
		this.intentos = new Intentos((IntentosVO) intentosVO);
		intentosBO = (IntentosBO) packageDAO.launchesTransaction(this);
		return new IntentosVO(intentosBO.intentos);
	}

	public InterfaceEntityBase actualizaIntentosUsuario(InterfaceEntityBase intentosVO, String pass) throws Exception {
		tipoTransaccion=4;
		this.intentos=new Intentos((IntentosVO) intentosVO);
		this.intentos.setQuery("from com.mx.coram.login.entity.Intentos intentos where intentos.id in(" +
				"select min(intentos.id) from com.mx.coram.login.entity.Intentos intentos where intentos.usuarios.id="+intentos.getUsuarios().getId()+
				"and intentos.activo=true and intentos.noIntentos<intentos.intentosMaximos order by intentos.id)");
		this.intentosBO=(IntentosBO) packageDAO.launchesTransactionQuery(this);
		
		if(intentos.getStatus()==BdConstant.OBJETO_VACIO)
		{
			intentos.setStatus(BdConstant.ERROR);
			intentos.setMsgView("El ACCESO HA SIDO BLOQUEADO. CONSULTE A SU ADMINISTRADOR");
		}
		
		else if(minutos<intentos.getMinutos() && intentos.getNoIntentos()!=0)
		{
			if(intentos.getMinutos()>1)
			{
				minutos=intentos.getMinutos()-minutos;
			}
			intentos.setStatus(BdConstant.ERROR);
			intentos.setMsgView("DESPUÉS DE "+minutos+" MINUTOS Y "+(60-segundos)+" SEGUNDOS, INTENTE ACCESAR NUEVAMENTE");
		}
		
		else if(intentos.getUsuarios().getPassword().equals(pass) && intentos.getStatus()==BdConstant.EXITO)
		{
			tipoTransaccion=4;
			this.usuarios=new Usuarios();
			this.intentos.setQuery("update com.mx.coram.login.entity.Intentos intentos set intentos.noIntentos=0, intentos.activo=true" +
					" where intentos.usuarios.id="+intentos.getUsuarios().getId());
			this.usuarios.setQuery("update com.mx.coram.login.entity.Usuarios usuarios set usuarios.ultimaFechaAcceso='"+fechaActual+
					"' where usuarios.id="+ intentos.getUsuarios().getId());
			intentosBO=(IntentosBO) packageDAO.launchesTransaction(this);
		}
		else if(!intentos.getUsuarios().getPassword().equals(pass))
		{
				
				tipoTransaccion=2;
				this.usuarios=new Usuarios();
					intentos.setNoIntentos(intentos.getNoIntentos()+1);
	
					if(intentos.getNoIntentos()==intentos.getIntentosMaximos())
					{
						intentos.setActivo(false);
					}
					intentosBO=(IntentosBO) packageDAO.launchesTransaction(this);
					this.usuarios.setQuery("update com.mx.coram.login.entity.Usuarios usuarios set usuarios.ultimaFechaAcceso='"+fechaActual+
							"' where usuarios.id="+intentos.getUsuarios().getId());
					usuarios=(Usuarios) packageDAO.executeACID(usuarios);
					
					intentos.setStatus(BdConstant.ERROR);
					intentos.setMsgView("VERIFIQUE USUARIO Y/O PASSWORD...");
		}
		return new IntentosVO(this.intentosBO.intentos);
	}

	public ArrayList<IntentosVO> obtenerIntentosRangoList(IntentosVO intentosVO) throws Exception {
		this.intentos = new Intentos((IntentosVO) intentosVO);
		this.intentos.setQuery("from com.mx.coram.login.entity.Intentos intentos order by intentos.id");

		ArrayList<InterfaceEntityBase> intentosV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.intentos);
		Iterator<InterfaceEntityBase> intentosIt = intentosV.iterator();
		while (intentosIt.hasNext()) {
			intentos = (Intentos) intentosIt.next();
			lstIntentosVO.add(new IntentosVO(intentos));
		}
		return intentosBO.lstIntentosVO;
	}

	public ArrayList<IntentosVO> obtenerIntentosList(IntentosVO intentoVO) throws Exception {
		tipoTransaccion = 5;
		this.intentos = new Intentos(intentoVO);
		intentosBO = (IntentosBO) packageDAO.launchesTransactionQuery(this);
		return intentosBO.lstIntentosVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase intentosVO) throws Exception {
		tipoTransaccion = 6;
		this.intentos = new Intentos((IntentosVO) intentosVO);
		intentosBO = (IntentosBO) packageDAO.launchesTransactionQuery(this);
		return (new IntentosVO(intentosBO.intentos));
	}
}
