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
import com.edu.tpv.login.entity.GrupoSistemas;
import com.edu.tpv.login.entity.Mapeos;
import com.edu.tpv.login.to.MapeosVO;

/**
 * Método que se encarga de realizar las operaciones sobre el catálogo
 * tbcmapeos, tales operaciones son: guardar, modificar, y eliminar un registro,
 * consultar un registro en particular, obtener registros de acuerdo a un rango
 * predeterminado, etc.
 * 
 * @author EGH
 */
public class MapeosBO implements TransaccionHibernateIfz, TransactionHibernateQueryIfz {
	PackageDAO packageDAO;
	private MapeosBO mapeosBO = this;
	private int tipoTransaccion = 0;
	private Mapeos mapeos = new Mapeos();
	private ArrayList<MapeosVO> lstMapeosVO = new ArrayList<MapeosVO>();

	public MapeosBO() {
		packageDAO = new PackageDAO("01");
	}

	/**
	 * Método en el cual se realizan las operaciones de guardado(1),
	 * modificado(2) y eliminado(3) en el catálogo de tbcmapeos.
	 */
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			Mapeos ultimoMapeo = new Mapeos();
			ultimoMapeo = (Mapeos) unParcelSession.getLastIdSintTx(ultimoMapeo);
			mapeos.setId(ultimoMapeo.getId());
			unParcelSession.saveSinTx(mapeos);
		} else if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(mapeos);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(mapeos);

		}
		return this;
	}

	/**
	 * Método de consulta en el catálogo de tbcmapeos, como son la búsqueda de
	 * un registro en particular (4), obtiene los registros existentes en la
	 * tabla(5), así como el número total de registros(6).
	 */
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		GrupoSistemas gpoSistemas = null;
		Vector<InterfaceEntityBase> mapeosV = null;
		Iterator<InterfaceEntityBase> mapeosIt = null;
		if (tipoTransaccion == 4) {
			mapeosV = packageDAO.findByQuerySinTx(mapeos);
			if (mapeosV != null && mapeosV.size() > 0) {
				mapeosIt = mapeosV.iterator();
				while (mapeosIt.hasNext()) {
					mapeos = (Mapeos) mapeosIt.next();
				}
				gpoSistemas = new GrupoSistemas();
				gpoSistemas.setId(mapeos.getGrupoSistemas().getId());
				mapeos.setGrupoSistemas((GrupoSistemas) packageDAO.getByIdSinTx(gpoSistemas));
				mapeos.setStatus(BdConstant.EXITO);
			} else
				mapeos.setStatus(BdConstant.OBJETO_VACIO);
		} else if (tipoTransaccion == 5) {
			mapeosV = packageDAO.listDataSin(mapeos);
			mapeosIt = mapeosV.iterator();
			while (mapeosIt.hasNext()) {
				mapeos = (Mapeos) mapeosIt.next();
				gpoSistemas = new GrupoSistemas();
				gpoSistemas.setId(mapeos.getGrupoSistemas().getId());
				mapeos.setGrupoSistemas((GrupoSistemas) packageDAO.getByIdSinTx(gpoSistemas));
				lstMapeosVO.add(new MapeosVO(mapeos));
			}
		} else if (tipoTransaccion == 6)
			mapeos = (Mapeos) packageDAO.getTotalRegSinTx(this.mapeos);

		return null;
	}

	/**
	 * Método que se encarga de llamar a la operación de guardar en el catálogo
	 * tbcmapeos
	 * 
	 * @param mapeosVO
	 * @return una entidad de tipo mapeosVO
	 * @throws Exception
	 *             en caso de presentarse algún error en el guardado.
	 */
	public InterfaceEntityBase guardarMapeo(InterfaceEntityBase mapeosVO) throws Exception {
		tipoTransaccion = 1;
		this.mapeos = new Mapeos((MapeosVO) mapeosVO);
		mapeosBO = (MapeosBO) packageDAO.launchesTransaction(this);
		return new MapeosVO(mapeosBO.mapeos);
	}

	/**
	 * Método que se encarga de llamar a la operación de modificar en el
	 * catálogo tbcmapeos
	 * 
	 * @param mapeosVO
	 * @return una entidad de tipo mapeosVO
	 * @throws Exception
	 */
	public InterfaceEntityBase modificarMapeo(InterfaceEntityBase mapeosVO) throws Exception {
		tipoTransaccion = 2;
		this.mapeos = new Mapeos((MapeosVO) mapeosVO);
		mapeosBO = (MapeosBO) packageDAO.launchesTransaction(this);
		return new MapeosVO(mapeosBO.mapeos);
	}

	/**
	 * Método que se encarga de llamar a la operación de eliminar un registro en
	 * el catálogo tbcmapeos
	 * 
	 * @param mapeosVO
	 * @return una entidad de tipo mapeosVO
	 * @throws Exception
	 */
	public InterfaceEntityBase eliminarMapeo(InterfaceEntityBase mapeosVO) throws Exception {
		tipoTransaccion = 3;
		this.mapeos = new Mapeos((MapeosVO) mapeosVO);
		mapeosBO = (MapeosBO) packageDAO.launchesTransaction(this);
		return new MapeosVO(mapeosBO.mapeos);
	}

	/**
	 * Método encargado de llamar a la operación de buscar un registro en el
	 * catálogo tbcmapeos
	 * 
	 * @param mapeosVO
	 * @return
	 * @throws Exception
	 */
	public InterfaceEntityBase buscarMapeo(InterfaceEntityBase mapeosVO) throws Exception {
		mapeos=(Mapeos)packageDAO.findById(new Mapeos((MapeosVO)mapeosVO));
		return new MapeosVO(mapeos);
	}

	/**
	 * Método que obtiene de acuerdo a un rango preestablecido una lista de los
	 * registros existentes en el catálogo tbcmapeos
	 * 
	 * @param mapeosVO
	 * @return
	 * @throws Exception
	 */
	public ArrayList<MapeosVO> obtenerMapeosRangoList(InterfaceEntityBase mapeosVO) throws Exception {
		this.mapeos = new Mapeos((MapeosVO) mapeosVO);
		this.mapeos.setQuery("from com.mx.coram.login.entity.Mapeos map  order by map.id");
		ArrayList<InterfaceEntityBase> mapeosV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.mapeos);
		Iterator<InterfaceEntityBase> mapeosIt = mapeosV.iterator();

		while (mapeosIt.hasNext()) {
			mapeos = (Mapeos) mapeosIt.next();
			lstMapeosVO.add(new MapeosVO(mapeos));
		}
		return mapeosBO.lstMapeosVO;
	}

	/**
	 * Método encargado de llamar a la operación de obtener el listado de los
	 * registros existentes en el catálogo tbcmapeos
	 * 
	 * @param mapeosVO
	 * @return
	 * @throws Exception
	 */
	public ArrayList<MapeosVO> obtenerMapeosList(InterfaceEntityBase mapeosVO) throws Exception {
		tipoTransaccion = 5;
		this.mapeos = new Mapeos((MapeosVO) mapeosVO);
		mapeosBO = (MapeosBO) packageDAO.launchesTransactionQuery(this);
		return mapeosBO.lstMapeosVO;
	}

	/**
	 * Método que se encarga de obtener el total de registros existentes en el
	 * catálogo tbcmapeos
	 * 
	 * @param mapeosVO
	 * @return
	 * @throws Exception
	 */
	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase mapeosVO) throws Exception {
		tipoTransaccion = 6;
		this.mapeos = new Mapeos((MapeosVO) mapeosVO);
		mapeosBO = (MapeosBO) packageDAO.launchesTransactionQuery(this);
		return new MapeosVO(mapeosBO.mapeos);
	}

}
