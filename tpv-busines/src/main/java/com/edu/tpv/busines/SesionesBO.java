package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.BdConstant;
import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.Sesiones;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.SesionesVO;

public class SesionesBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private SesionesBO sesionesBO = this;
	private int tipoTransaccion = 0;
	private Sesiones sesiones;
	private ArrayList<SesionesVO> lstSesionesVO = new ArrayList<SesionesVO>();

	public SesionesBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			Sesiones ultimaSesionId = new Sesiones();
			ultimaSesionId = (Sesiones) packageDAO.getLastIdSintTx(ultimaSesionId);
			sesiones.setId(ultimaSesionId.getId());
			sesiones.setFechaAcceso(unParcelSession.getDateCurrentSinTx());
			sesiones.setFechaUltimoAcceso(unParcelSession.getDateCurrentSinTx());
			sesiones.setActivo(true);
			unParcelSession.saveSinTx(sesiones);
		} else if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(sesiones);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(sesiones);
		}
		return this;
	}

	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Vector<InterfaceEntityBase> sesionesV;
		Iterator<InterfaceEntityBase> sesionesIt;
		Usuarios usuarios = null;

		if (tipoTransaccion == 4) {
			sesionesV = packageDAO.findByQuerySinTx(this.sesiones);
			if(sesionesV!=null && sesionesV.size()>0){
				sesionesIt = sesionesV.iterator();
				while (sesionesIt.hasNext()) {
					sesiones = (Sesiones) sesionesIt.next();
				}
				usuarios = new Usuarios();
				usuarios.setId(sesiones.getUsuarios().getId());
				sesiones.setUsuarios((Usuarios) packageDAO.getById(usuarios));
			}
			else
				sesiones.setStatus(BdConstant.OBJETO_VACIO);
			
		} else if (tipoTransaccion == 5) {
			sesionesV = packageDAO.listDataSin(sesiones);
			sesionesIt = sesionesV.iterator();
			while (sesionesIt.hasNext()) {
				sesiones = (Sesiones) sesionesIt.next();
				usuarios = new Usuarios();
				usuarios.setId(sesiones.getUsuarios().getId());
				sesiones.setUsuarios((Usuarios) packageDAO.findByIdSinTx(usuarios));
				lstSesionesVO.add(new SesionesVO(sesiones));
			}
		} else if (tipoTransaccion == 6) {
			sesiones = (Sesiones) packageDAO.getTotalRegSinTx(this.sesiones);
		}

		return this;
	}

	public InterfaceEntityBase guardarSesiones(InterfaceEntityBase sesionesVO) throws Exception {
		tipoTransaccion = 1;
		this.sesiones = new Sesiones((SesionesVO) sesionesVO);
		sesionesBO = (SesionesBO) packageDAO.launchesTransaction(this);
		return new SesionesVO(sesionesBO.sesiones);
	}

	public InterfaceEntityBase modificarSesiones(InterfaceEntityBase sesionesVO) throws Exception {
		tipoTransaccion = 2;
		this.sesiones = new Sesiones((SesionesVO) sesionesVO);
		sesionesBO = (SesionesBO) packageDAO.launchesTransaction(this);
		return new SesionesVO(sesionesBO.sesiones);
	}

	public InterfaceEntityBase eliminarSesiones(InterfaceEntityBase sesionesVO) throws Exception {
		tipoTransaccion = 3;
		this.sesiones = new Sesiones((SesionesVO) sesionesVO);
		sesionesBO = (SesionesBO) packageDAO.launchesTransaction(this);
		return new SesionesVO(sesionesBO.sesiones);
	}

	public InterfaceEntityBase buscarSesiones(InterfaceEntityBase sesionesVO) throws Exception {
		sesiones = (Sesiones) packageDAO.findById(new Sesiones((SesionesVO) sesionesVO));
		return new SesionesVO(sesiones);
	}
	
	public SesionesVO findSessionUser(SesionesVO sesionesVO) throws Exception{
		tipoTransaccion=4;
		this.sesiones=new Sesiones(sesionesVO);
		this.sesiones.setQuery("from com.mx.coram.login.entity.Sesiones sesion where sesion.usuarios.nombre='"+sesiones.getUsuarios().getNombre()+"' and sesion.activo="+sesiones.isActivo());
		sesionesBO=(SesionesBO) packageDAO.launchesTransactionQuery(this);
		sesionesVO=new SesionesVO(sesionesBO.sesiones);
		return sesionesVO;
	}
	
	public SesionesVO findSessionIdSession(SesionesVO sesionesVO) throws Exception{
		tipoTransaccion=4;
		this.sesiones=new Sesiones(sesionesVO);
		this.sesiones.setQuery("from com.mx.coram.login.entity.Sesiones sesion where sesion.usuarios.id="+sesiones.getUsuarios().getId()+" and sesion.idSesiones='"+sesiones.getIdSesiones()+"'");
		sesionesBO=(SesionesBO) packageDAO.launchesTransactionQuery(this);
		sesionesVO=new SesionesVO(sesionesBO.sesiones);
		return sesionesVO;
	}

	public ArrayList<SesionesVO> obtenerSesionesRangoList(SesionesVO sesionesVO) throws Exception {
		this.sesiones = new Sesiones((SesionesVO) sesionesVO);
		this.sesiones.setQuery("from com.mx.coram.login.entity.Sesiones sesion order by sesion.id");

		ArrayList<InterfaceEntityBase> sesionV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.sesiones);
		Iterator<InterfaceEntityBase> sesionIt = sesionV.iterator();
		while (sesionIt.hasNext()) {
			sesiones = (Sesiones) sesionIt.next();
			lstSesionesVO.add(new SesionesVO(sesiones));
		}
		return sesionesBO.lstSesionesVO;
	}

	public ArrayList<SesionesVO> obtenerSesionesList(SesionesVO sesionesVO) throws Exception {
		tipoTransaccion = 5;
		this.sesiones = new Sesiones(sesionesVO);
		sesionesBO = (SesionesBO) packageDAO.launchesTransactionQuery(this);
		return sesionesBO.lstSesionesVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase sesionesVO) throws Exception {
		tipoTransaccion = 6;
		this.sesiones = new Sesiones((SesionesVO) sesionesVO);
		sesionesBO = (SesionesBO) packageDAO.launchesTransactionQuery(this);
		return (new SesionesVO(sesionesBO.sesiones));
	}
	public SesionesVO getUltimo(SesionesVO s)
	{
		Sesiones s1= new Sesiones();
		s1=(Sesiones) packageDAO.getLastId(s1);
		return new SesionesVO(s1);
	}
}
