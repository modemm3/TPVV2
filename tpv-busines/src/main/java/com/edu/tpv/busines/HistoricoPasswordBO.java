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
import com.edu.tpv.login.entity.HistoricoPassword;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.HistoricoPasswordVO;

public class HistoricoPasswordBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private HistoricoPasswordBO historicoPasswordBO = this;
	private int tipoTransaccion = 0;
	private HistoricoPassword historicoPassword = new HistoricoPassword();
	private ArrayList<HistoricoPasswordVO> lstHistoricoPwdVO = new ArrayList<HistoricoPasswordVO>();

	public HistoricoPasswordBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			HistoricoPassword ultimoHistoricoPwd = new HistoricoPassword();
			ultimoHistoricoPwd = (HistoricoPassword) unParcelSession.getLastIdSintTx(ultimoHistoricoPwd);
			historicoPassword.setId(ultimoHistoricoPwd.getId());
			unParcelSession.saveSinTx(historicoPassword);
		} else if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(historicoPassword);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(historicoPassword);
		}
		return this;
	}

	@Override
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Usuarios usuarios = null;
		Vector<InterfaceEntityBase> historicoPwdV = null;
		Iterator<InterfaceEntityBase> historicoPwdIt = null;
		if (tipoTransaccion == 4) {
			historicoPwdV = packageDAO.findByQuerySinTx(this.historicoPassword);
			if(historicoPwdV!=null && historicoPwdV.size()>0){
				historicoPwdIt = historicoPwdV.iterator();
				while (historicoPwdIt.hasNext()) {
					historicoPassword = (HistoricoPassword) historicoPwdIt.next();
				}
				usuarios = new Usuarios();
				usuarios.setId(historicoPassword.getUsuario().getId());
				historicoPassword.setUsuario((Usuarios) packageDAO.getByIdSinTx(usuarios));
			}else
				historicoPassword.setStatus(BdConstant.OBJETO_VACIO);
		}
		if (tipoTransaccion == 5) {
			historicoPwdV = packageDAO.listDataSin(this.historicoPassword);
			historicoPwdIt = historicoPwdV.iterator();
			while (historicoPwdIt.hasNext()) {
				historicoPassword = (HistoricoPassword) historicoPwdIt.next();
				usuarios = new Usuarios();
				usuarios.setId(historicoPassword.getUsuario().getIdUsuario());
				historicoPassword.setUsuario((Usuarios) packageDAO.getByIdSinTx(usuarios));
				lstHistoricoPwdVO.add(new HistoricoPasswordVO(historicoPassword));
			}
		} else if (tipoTransaccion == 6) {
			historicoPassword = (HistoricoPassword) packageDAO.getTotalRegSinTx(this.historicoPassword);
		}
		return this;
	}

	public InterfaceEntityBase guardarHistoricoPassword(InterfaceEntityBase historicoPasswordVO) throws Exception {
		tipoTransaccion = 1;
		this.historicoPassword = new HistoricoPassword((HistoricoPasswordVO) historicoPasswordVO);
		historicoPasswordBO = (HistoricoPasswordBO) packageDAO.launchesTransaction(this);
		return new HistoricoPasswordVO(historicoPasswordBO.historicoPassword);
	}

	public InterfaceEntityBase modificarHistoricoPassword(InterfaceEntityBase historicoPasswordVO) throws Exception {
		tipoTransaccion = 2;
		this.historicoPassword = new HistoricoPassword((HistoricoPasswordVO) historicoPasswordVO);
		historicoPasswordBO = (HistoricoPasswordBO) packageDAO.launchesTransaction(this);
		return new HistoricoPasswordVO(historicoPasswordBO.historicoPassword);
	}

	public InterfaceEntityBase eliminarHistoricoPassword(InterfaceEntityBase historicoPasswordVO) throws Exception {
		tipoTransaccion = 3;
		this.historicoPassword = new HistoricoPassword((HistoricoPasswordVO) historicoPasswordVO);
		historicoPasswordBO = (HistoricoPasswordBO) packageDAO.launchesTransaction(this);
		return new HistoricoPasswordVO(historicoPasswordBO.historicoPassword);
	}

	public InterfaceEntityBase buscarHistoricoPassword(InterfaceEntityBase historicoPwdVO) throws Exception {
		historicoPassword = (HistoricoPassword) packageDAO.findById(new HistoricoPassword((HistoricoPasswordVO) historicoPwdVO));
		return new HistoricoPasswordVO(historicoPassword);
	}
	
	public InterfaceEntityBase buscarHistoricoPasswordIdUsuarioPwd(InterfaceEntityBase historicoPwdVO) throws Exception{
		tipoTransaccion=4;
		historicoPassword=new HistoricoPassword((HistoricoPasswordVO) historicoPwdVO);
		historicoPassword.setQuery("from com.mx.coram.login.entity.HistoricoPassword hpwd where hpwd.usuario.id=" + historicoPassword.getUsuario().getId()+" and hpwd.password='"+historicoPassword.getPassword()+"'");
		historicoPasswordBO=(HistoricoPasswordBO) packageDAO.launchesTransactionQuery(this);
		return new HistoricoPasswordVO(historicoPassword);
	}

	/**
	 * Obtiene un Rango especificado de los registros existentes en el catalogo
	 * tbchistoricopassword.
	 * 
	 * @param historicoPwdVO
	 * @return una lista de los registros de un rango especificado.
	 * @throws Exception
	 */
	public ArrayList<HistoricoPasswordVO> obtenerHistoricoPwdRangoList(HistoricoPasswordVO historicoPwdVO) throws Exception {
		this.historicoPassword = new HistoricoPassword((HistoricoPasswordVO) historicoPwdVO);
		this.historicoPassword.setQuery("from com.mx.coram.login.entity.HistoricoPassword histpwd order by histpwd.id");

		ArrayList<InterfaceEntityBase> historicoPwdV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.historicoPassword);
		Iterator<InterfaceEntityBase> historicoPwdIt = historicoPwdV.iterator();
		while (historicoPwdIt.hasNext()) {
			historicoPassword = (HistoricoPassword) historicoPwdIt.next();
			lstHistoricoPwdVO.add(new HistoricoPasswordVO(historicoPassword));
		}

		return historicoPasswordBO.lstHistoricoPwdVO;
	}

	/**
	 * Método que obtiene una lista de los registros existentes en el catálogo
	 * tbchistoricopassword.
	 * 
	 * @param historicoPwdVO
	 * @return un listado de historicopasswordVO
	 * @throws Exception
	 */
	public ArrayList<HistoricoPasswordVO> obtenerHistoricoPwdList(InterfaceEntityBase historicoPwdVO) throws Exception {
		tipoTransaccion = 5;
		this.historicoPassword = new HistoricoPassword((HistoricoPasswordVO) historicoPwdVO);
		historicoPasswordBO = (HistoricoPasswordBO) packageDAO.launchesTransactionQuery(this);
		return historicoPasswordBO.lstHistoricoPwdVO;
	}

	/**
	 * Método que obtiene el total de registros del catálogo
	 * tbchistoricopassword.
	 * 
	 * @param historicoPwdVO
	 * @return Una entidad que lleva en sus atributos la cantidad total de
	 *         registros.
	 * @throws Exception
	 */
	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase historicoPwdVO) throws Exception {
		tipoTransaccion = 6;
		this.historicoPassword = new HistoricoPassword((HistoricoPasswordVO) historicoPwdVO);
		historicoPasswordBO = (HistoricoPasswordBO) packageDAO.launchesTransactionQuery(this);
		return (new HistoricoPasswordVO(historicoPasswordBO.historicoPassword));
	}

}
