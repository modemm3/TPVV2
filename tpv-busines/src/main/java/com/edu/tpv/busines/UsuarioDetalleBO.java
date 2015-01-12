package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.UsuarioDetalle;
import com.edu.tpv.login.to.AsigPermisosVO;
import com.edu.tpv.login.to.UsuarioDetalleVO;

public class UsuarioDetalleBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO = null;
	private UsuarioDetalleBO usuarioDetalleBO = this;
	private int tipoTransaccion = 0;
	private UsuarioDetalle usuarioDetalle = new UsuarioDetalle();
	private ArrayList<UsuarioDetalleVO> lstUsuarioDetalleVO = new ArrayList<UsuarioDetalleVO>();

	public UsuarioDetalleBO(AsigPermisosVO permisoVO) {
		packageDAO = new PackageDAO(permisoVO.getEmpresasVO().getIdC());
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			UsuarioDetalle ultimoUsuarioDetalle = new UsuarioDetalle();
			ultimoUsuarioDetalle = (UsuarioDetalle) unParcelSession.getLastIdSintTx(ultimoUsuarioDetalle);
			usuarioDetalle.setId(ultimoUsuarioDetalle.getId());
			usuarioDetalle.setFechaAcceso(unParcelSession.getDateCurrentSinTx());
			unParcelSession.saveSinTx(usuarioDetalle);
		} else if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(usuarioDetalle);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(usuarioDetalle);
		}
		return this;
	}

	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Vector<InterfaceEntityBase> usuarioDetV = null;
		Iterator<InterfaceEntityBase> it = null;

		if (tipoTransaccion == 5) {
			usuarioDetV = packageDAO.listDataSin(this.usuarioDetalle);
			it = usuarioDetV.iterator();
			while (it.hasNext()) {
				usuarioDetalle = (UsuarioDetalle) it.next();
				lstUsuarioDetalleVO.add(new UsuarioDetalleVO(usuarioDetalle));
			}
		} else if (tipoTransaccion == 6) {
			usuarioDetalle = (UsuarioDetalle) packageDAO.getTotalRegSinTx(this.usuarioDetalle);
		}
		return null;
	}

	public InterfaceEntityBase guardarUsuarioDetalle(InterfaceEntityBase usuarioDetalleVO) throws Exception {
		tipoTransaccion = 1;
		this.usuarioDetalle = new UsuarioDetalle((UsuarioDetalleVO) usuarioDetalleVO);
		usuarioDetalleBO = (UsuarioDetalleBO) packageDAO.launchesTransaction(this);
		return new UsuarioDetalleVO(usuarioDetalleBO.usuarioDetalle);
	}

	public InterfaceEntityBase modificarUsuarioDetalle(InterfaceEntityBase usuarioDetalleVO) throws Exception {
		tipoTransaccion = 2;
		this.usuarioDetalle = new UsuarioDetalle((UsuarioDetalleVO) usuarioDetalleVO);
		usuarioDetalleBO = (UsuarioDetalleBO) packageDAO.launchesTransaction(this);
		return new UsuarioDetalleVO(usuarioDetalleBO.usuarioDetalle);
	}

	public InterfaceEntityBase eliminarUsuarioDetalle(InterfaceEntityBase usuarioDetalleVO) throws Exception {
		tipoTransaccion = 3;
		this.usuarioDetalle = new UsuarioDetalle((UsuarioDetalleVO) usuarioDetalleVO);
		usuarioDetalleBO = (UsuarioDetalleBO) packageDAO.launchesTransaction(this);
		return new UsuarioDetalleVO(usuarioDetalleBO.usuarioDetalle);
	}

	public InterfaceEntityBase buscarUsuarioDetalle(InterfaceEntityBase usuarioDetalleVO) throws Exception {
		usuarioDetalle=(UsuarioDetalle)packageDAO.findById(new UsuarioDetalle((UsuarioDetalleVO)usuarioDetalleVO));
		return new UsuarioDetalleVO(usuarioDetalle);
	}

	public ArrayList<UsuarioDetalleVO> obtenerUsuarioDetalleRangoList(UsuarioDetalleVO usuarioDetalleVO) throws Exception {
		this.usuarioDetalle = new UsuarioDetalle((UsuarioDetalleVO) usuarioDetalleVO);
		this.usuarioDetalle.setQuery("from com.mx.coram.login.entity.UsuarioDetalle usuariodetalle order by usuariodetalle.id");

		ArrayList<InterfaceEntityBase> usuarioDetalleV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.usuarioDetalle);
		Iterator<InterfaceEntityBase> usuarioDetalleIt = usuarioDetalleV.iterator();
		while (usuarioDetalleIt.hasNext()) {
			usuarioDetalle = (UsuarioDetalle) usuarioDetalleIt.next();
			lstUsuarioDetalleVO.add(new UsuarioDetalleVO(usuarioDetalle));
		}

		return usuarioDetalleBO.lstUsuarioDetalleVO;
	}

	public ArrayList<UsuarioDetalleVO> obtenerUsuarioDetalleList(UsuarioDetalleVO usuarioDetalleVO) throws Exception {
		tipoTransaccion = 5;
		this.usuarioDetalle = new UsuarioDetalle(usuarioDetalleVO);
		usuarioDetalleBO = (UsuarioDetalleBO) packageDAO.launchesTransactionQuery(this);
		return usuarioDetalleBO.lstUsuarioDetalleVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase usuarioDetalleVO) throws Exception {
		tipoTransaccion = 6;
		this.usuarioDetalle = new UsuarioDetalle((UsuarioDetalleVO) usuarioDetalleVO);
		usuarioDetalleBO = (UsuarioDetalleBO) packageDAO.launchesTransactionQuery(this);
		return (new UsuarioDetalleVO(usuarioDetalleBO.usuarioDetalle));
	}
}
