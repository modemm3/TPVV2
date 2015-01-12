package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.TipoAplicacion;
import com.edu.tpv.login.to.TipoAplicacionVO;

/**
 * Clase referente al Business Object del catálogo de tipo de aplicación,
 * contiene los métodos para realizar las transacciones y las consultas al
 * catálogo
 * 
 * @author EGH
 */
public class TipoAplicacionBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private TipoAplicacionBO tipoAplicacionBO = this;
	private int tipoTransaccion = 0;
	private TipoAplicacion tipoAplicacion = new TipoAplicacion();
	private ArrayList<TipoAplicacionVO> lstTipoAplicacionVO = new ArrayList<TipoAplicacionVO>();

	public TipoAplicacionBO() {
		packageDAO = new PackageDAO("01");
	}

	/***
	 * Método que se encarga de gestionar las operaciones de guardar, modificar
	 * y eliminar de la tabla catálogo de tipo aplicación, de acuerdo al tipo de
	 * transacción que se ha solicitado.
	 */
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			TipoAplicacion ultimoIdTipoApl = new TipoAplicacion();
			ultimoIdTipoApl = (TipoAplicacion) unParcelSession.getLastIdSintTx(ultimoIdTipoApl);
			tipoAplicacion.setId(ultimoIdTipoApl.getId());
			unParcelSession.saveSinTx(tipoAplicacion);
		} else if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(tipoAplicacion);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(tipoAplicacion);
		}
		return this;
	}

	/**
	 * Método que realiza la consulta en el catálogo de tipo aplicación, ya sea
	 * para obtener el listado de los registros con el que cuenta, o una
	 * búsqueda especifica, o bien para obtener el total de registros con el que
	 * cuenta dicho cátalogo.
	 */
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Vector<InterfaceEntityBase> tipoAplicacionV = null;
		Iterator<InterfaceEntityBase> tipoAplicacionIt = null;

		if (tipoTransaccion == 4) {
			tipoAplicacionV = packageDAO.findByQuerySinTx(this.tipoAplicacion);
			tipoAplicacionIt = tipoAplicacionV.iterator();
			while (tipoAplicacionIt.hasNext()) {
				tipoAplicacion = (TipoAplicacion) tipoAplicacionIt.next();
			}
		} else if (tipoTransaccion == 5) {
			tipoAplicacionV = packageDAO.listDataSin(this.tipoAplicacion);
			tipoAplicacionIt = tipoAplicacionV.iterator();
			while (tipoAplicacionIt.hasNext()) {
				tipoAplicacion = (TipoAplicacion) tipoAplicacionIt.next();
				lstTipoAplicacionVO.add(new TipoAplicacionVO(tipoAplicacion));
			}
		} else if (tipoTransaccion == 6) {
			tipoAplicacion = (TipoAplicacion) packageDAO.getTotalRegSinTx(this.tipoAplicacion);
		}
		return this;
	}

	/**
	 * Método que guarda un registro en el catálogo de tipo aplicación.
	 * 
	 * @param tipoAplicacionVO
	 * @return una entidad el cual contiene un estatus que indica si la
	 *         operacion fue exitosa. los estatus son: 0 si ocurrió un error
	 *         durante la operación y 1 si fue exitosa dicha operación.
	 * @throws Exception
	 */
	public InterfaceEntityBase guardarTipoAplicacion(InterfaceEntityBase tipoAplicacionVO) throws Exception {
		tipoTransaccion = 1;
		this.tipoAplicacion = new TipoAplicacion((TipoAplicacionVO) tipoAplicacionVO);
		tipoAplicacionBO = (TipoAplicacionBO) packageDAO.launchesTransaction(this);
		return new TipoAplicacionVO(tipoAplicacionBO.tipoAplicacion);
	}

	/**
	 * Método que realiza la modificación de un registro en el catálogo de tipo
	 * aplicación.
	 * 
	 * @param tipoAplicacionVO
	 * @return una entidad la cual contiene un estatus, el cual indica si el
	 *         registro fue modificado correctamente, los tipos de estatus son:
	 *         0 si ocurrió un error durante la operación y 1 si fue exitosa.
	 * @throws Exception
	 */
	public InterfaceEntityBase modificarTipoAplicacion(InterfaceEntityBase tipoAplicacionVO) throws Exception {
		tipoTransaccion = 2;
		this.tipoAplicacion = new TipoAplicacion((TipoAplicacionVO) tipoAplicacionVO);
		tipoAplicacionBO = (TipoAplicacionBO) packageDAO.launchesTransaction(this);
		return new TipoAplicacionVO(tipoAplicacionBO.tipoAplicacion);
	}

	/**
	 * Método que elimina un registro en el catálogo de tipo de aplicación
	 * 
	 * @param tipoAplicacionVO
	 * @return una entidad la cual contiene el estatus que indica si el registro
	 *         fue eliminado. los tipos de estatus son: 0 si ocurrió un error en
	 *         la operación y 1 si el registro fue eliminado correctamente.
	 * @throws Exception
	 */
	public InterfaceEntityBase eliminarTipoAplicacion(InterfaceEntityBase tipoAplicacionVO) throws Exception {
		tipoTransaccion = 3;
		this.tipoAplicacion = new TipoAplicacion((TipoAplicacionVO) tipoAplicacionVO);
		tipoAplicacionBO = (TipoAplicacionBO) packageDAO.launchesTransaction(this);
		return new TipoAplicacionVO(tipoAplicacionBO.tipoAplicacion);
	}

	/**
	 * Método que busca un registro en el catálogo de tipo aplicación de acuerdo
	 * a su id.
	 * 
	 * @param tipoAplicacionVO
	 * @return retorna una entidad que contiene el estatus para saber si fue
	 *         encontrado (0 si ocurrió un error en la búsqueda, 1 si se
	 *         encontro el registro y 2 si el registro no fue localizado).
	 * @throws Exception
	 */
	public InterfaceEntityBase buscarTipoAplicacion(InterfaceEntityBase tipoAplicacionVO) throws Exception {
		tipoAplicacion=(TipoAplicacion)packageDAO.findById(new TipoAplicacion((TipoAplicacionVO)tipoAplicacionVO));
		return new TipoAplicacionVO(tipoAplicacion);
	}

	/**
	 * Método que obtiene de acuerdo a un rango definido previamente, el listado
	 * de los registros con el que cuenta la tabla de tipo aplicación.
	 * 
	 * @param tipoAplicacionVO
	 * @return un listado de los registros de acuerdo a los parametros recibidos
	 *         (start y limit), así como un estatus que indica si la operacion
	 *         fue satisfactoria (0 si ocurrió un error y 1 si fue exitosa)
	 * @throws Exception
	 */
	public ArrayList<TipoAplicacionVO> obtenerTipoAplicacionRangoList(TipoAplicacionVO tipoAplicacionVO) throws Exception {
		this.tipoAplicacion = new TipoAplicacion((TipoAplicacionVO) tipoAplicacionVO);
		this.tipoAplicacion.setQuery("from com.mx.coram.login.entity.TipoAplicacion ta order by ta.id");

		ArrayList<InterfaceEntityBase> tipoAplicacionV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.tipoAplicacion);
		Iterator<InterfaceEntityBase> tipoAplicacionIt = tipoAplicacionV.iterator();
		while (tipoAplicacionIt.hasNext()) {
			tipoAplicacion = (TipoAplicacion) tipoAplicacionIt.next();
			lstTipoAplicacionVO.add(new TipoAplicacionVO(tipoAplicacion));
		}
		return tipoAplicacionBO.lstTipoAplicacionVO;
	}

	/**
	 * Método que obtiene los registros de la tabla catálogo de tipo aplicación
	 * 
	 * @param tipoAplicacionVO
	 * @return una lista de registros con el que cuenta dicha tabla. y el cual
	 *         contiene a su vez un estatus con los valores siguientes: 0 si
	 *         ocurrió un error y 1 si fue exitosa la operación.
	 * @throws Exception
	 */
	public ArrayList<TipoAplicacionVO> obtenerTipoAplicacionList(TipoAplicacionVO tipoAplicacionVO) throws Exception {
		tipoTransaccion = 5;
		this.tipoAplicacion = new TipoAplicacion(tipoAplicacionVO);
		tipoAplicacionBO = (TipoAplicacionBO) packageDAO.launchesTransactionQuery(this);
		return tipoAplicacionBO.lstTipoAplicacionVO;
	}

	/**
	 * Método que obtiene el total de registros de la tabla de catálogo de tipo
	 * de aplicación
	 * 
	 * @param tipoAplicacionVO
	 * @return una entidad de tipo aplicación el cual contiene un estatus que
	 *         indica si se realizó correctamente la operación o si resulto
	 *         fallido para su posterior tratamiento. (0 si ocurrió un error y 1
	 *         si fue exitosa)
	 * @throws Exception
	 */
	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase tipoAplicacionVO) throws Exception {
		tipoTransaccion = 6;
		this.tipoAplicacion = new TipoAplicacion((TipoAplicacionVO) tipoAplicacionVO);
		tipoAplicacionBO = (TipoAplicacionBO) packageDAO.launchesTransactionQuery(this);
		return (new TipoAplicacionVO(tipoAplicacionBO.tipoAplicacion));
	}
}
