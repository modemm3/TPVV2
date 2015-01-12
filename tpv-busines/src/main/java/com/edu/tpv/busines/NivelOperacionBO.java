package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.NivelOperacion;
import com.edu.tpv.login.to.NivelOperacionVO;

/**
 * Clase referente al Business Object del catálogo de nivel de operacion,
 * contiene los métodos para realizar las transacciones y las consultas al
 * catalogo.
 * 
 * @author EGH
 */
public class NivelOperacionBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private NivelOperacionBO nivelOperacionBO = this;
	private int tipoTransaccion = 0;
	private NivelOperacion nivelOperacion = new NivelOperacion();
	private ArrayList<NivelOperacionVO> lstNivelOperacionVO = new ArrayList<NivelOperacionVO>();

	public NivelOperacionBO() {
		packageDAO = new PackageDAO("01");
	}

	/***
	 * Método que se encarga de gestionar las operaciones de guardar, modificar
	 * y eliminar de la tabla catálogo de nivel operacion, de acuerdo al tipo de
	 * transacción que se ha solicitado.
	 */
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		switch(tipoTransaccion)
		{
			case 1:
				NivelOperacion ultimoIdNivelOp = new NivelOperacion();
				ultimoIdNivelOp = (NivelOperacion) unParcelSession.getLastIdSintTx(ultimoIdNivelOp);
				nivelOperacion.setId(ultimoIdNivelOp.getId());
				unParcelSession.saveSinTx(nivelOperacion);
				break;
			case 2:
				unParcelSession.modifySinTx(nivelOperacion);
				break;
			case 3:
				unParcelSession.deleteSintTx(nivelOperacion);
		}
		return this;
	}

	/**
	 * Método que realiza la consulta en el catálogo de nivel operación, ya sea
	 * para obtener el listado de los registros con el que cuenta, o una
	 * búsqueda especifica, o bien para obtener el total de registros con el que
	 * cuenta dicho cátalogo.
	 */
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Vector<InterfaceEntityBase> nivelOperacionV = null;
		Iterator<InterfaceEntityBase> nivelOperacionIt = null;
		
		switch(tipoTransaccion)
		{
			case 4:
				nivelOperacionV = packageDAO.findByQuerySinTx(this.nivelOperacion);
				nivelOperacionIt = nivelOperacionV.iterator();
				while (nivelOperacionIt.hasNext()) {
					nivelOperacion = (NivelOperacion) nivelOperacionIt.next();
				}
				break;
			case 5:
				nivelOperacionV = packageDAO.listDataSin(this.nivelOperacion);
				nivelOperacionIt = nivelOperacionV.iterator();
				while (nivelOperacionIt.hasNext()) {
					nivelOperacion = (NivelOperacion) nivelOperacionIt.next();
					lstNivelOperacionVO.add(new NivelOperacionVO(nivelOperacion));
				}
				break;
			case 6:
				nivelOperacion = (NivelOperacion) packageDAO.getTotalRegSinTx(this.nivelOperacion);
		}
		return this;
	}

	/**
	 * Método que guarda un registro en la tabla de catálogo de nivel operación.
	 * 
	 * @param nivelOperacionVO
	 * @return una entidad el cual contiene un estatus que indica si la
	 *         operacion fue exitosa. los estatus son: 0 si ocurrió un error
	 *         durante la operación y 1 si fue exitosa dicha operación.
	 * @throws Exception
	 */
	public InterfaceEntityBase guardarNivelOperacion(InterfaceEntityBase nivelOperacionVO) throws Exception {
		tipoTransaccion = 1;
		this.nivelOperacion = new NivelOperacion((NivelOperacionVO) nivelOperacionVO);
		nivelOperacionBO = (NivelOperacionBO) packageDAO.launchesTransaction(this);
		return new NivelOperacionVO(nivelOperacionBO.nivelOperacion);
	}

	/**
	 * Método que realiza la modificación de un registro en la tabla de cátalogo
	 * de nivel operación.
	 * 
	 * @param nivelOperacionVO
	 * @return una entidad la cual contiene un estatus, el cual indica si el
	 *         registro fue modificado correctamente, los tipos de estatus son:
	 *         0 si ocurrió un error durante la operación y 1 si fue exitosa.
	 * @throws Exception
	 */
	public InterfaceEntityBase modificarNivelOperacion(InterfaceEntityBase nivelOperacionVO) throws Exception {
		tipoTransaccion = 2;
		this.nivelOperacion = new NivelOperacion((NivelOperacionVO) nivelOperacionVO);
		nivelOperacionBO = (NivelOperacionBO) packageDAO.launchesTransaction(this);
		return new NivelOperacionVO(nivelOperacionBO.nivelOperacion);
	}

	/**
	 * Método que elimina un registro en la tabla catálogo de nivel operación.
	 * 
	 * @param nivelOperacionVO
	 * @return una entidad la cual contiene el estatus que indica si el registro
	 *         fue eliminado. los tipos de estatus son: 0 si ocurrió un error en
	 *         la operación y 1 si el registro fue eliminado correctamente.
	 * @throws Exception
	 */
	public InterfaceEntityBase eliminaNivelOperacion(InterfaceEntityBase nivelOperacionVO) throws Exception {
		tipoTransaccion = 3;
		this.nivelOperacion = new NivelOperacion((NivelOperacionVO) nivelOperacionVO);
		nivelOperacionBO = (NivelOperacionBO) packageDAO.launchesTransaction(this);
		return new NivelOperacionVO(nivelOperacionBO.nivelOperacion);
	}

	/**
	 * Método que busca un registro en el catálogo de nivel operación de acuerdo
	 * a su id.
	 * 
	 * @param nivelOperacionVO
	 * @return retorna una entidad que contiene el estatus para saber si fue
	 *         encontrado (0 si ocurrió un error en la búsqueda, 1 si se
	 *         encontro el registro y 2 si el registro no fue localizado).
	 * @throws Exception
	 */
	public InterfaceEntityBase buscarNivelOperacion(InterfaceEntityBase nivelOperacionVO) throws Exception {
		nivelOperacion=(NivelOperacion) packageDAO.findById(new NivelOperacion((NivelOperacionVO) nivelOperacionVO));
		return new NivelOperacionVO(nivelOperacion);
	}

	/**
	 * Método que obtiene de acuerdo a un rango definido previamente, el listado
	 * de los registros con el que cuenta la tabla de Nivel Operación.
	 * 
	 * @param nivelOperacionVO
	 * @return un listado de los registros de acuerdo a los parametros recibidos
	 *         (start y limit), así como un estatus que indica si la operacion
	 *         fue satisfactoria (0 si ocurrió un error y 1 si fue exitosa)
	 * @throws Exception
	 */
	public ArrayList<NivelOperacionVO> obtenerNivelOperacionRangoList(NivelOperacionVO nivelOperacionVO) throws Exception {
		this.nivelOperacion = new NivelOperacion((NivelOperacionVO) nivelOperacionVO);
		this.nivelOperacion.setQuery("from com.mx.coram.login.entity.NivelOperacion no order by no.id");

		ArrayList<InterfaceEntityBase> nivelOperacionV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.nivelOperacion);
		Iterator<InterfaceEntityBase> nivelOperacionIt = nivelOperacionV.iterator();
		while (nivelOperacionIt.hasNext()) {
			nivelOperacion = (NivelOperacion) nivelOperacionIt.next();
			lstNivelOperacionVO.add(new NivelOperacionVO(nivelOperacion));
		}

		return nivelOperacionBO.lstNivelOperacionVO;
	}

	/**
	 * Método que obtiene los registros de la tabla catálogo de Nivel Operación
	 * 
	 * @param nivelOperacionVO
	 * @return una lista de registros con el que cuenta dicha tabla. y el cual
	 *         contiene a su vez un estatus con los valores siguientes: 0 si
	 *         ocurrió un error y 1 si fue exitosa la operación.
	 * @throws Exception
	 */
	public ArrayList<NivelOperacionVO> obtenerNivelOperacionList(NivelOperacionVO nivelOperacionVO) throws Exception {
		tipoTransaccion = 5;
		this.nivelOperacion = new NivelOperacion((NivelOperacionVO) nivelOperacionVO);
		nivelOperacionBO = (NivelOperacionBO) packageDAO.launchesTransactionQuery(this);
		return nivelOperacionBO.lstNivelOperacionVO;
	}

	/**
	 * Método que obtiene el total de registros de la tabla de catálogo de Nivel
	 * Operación
	 * 
	 * @param nivelVO
	 * @return una entidad de tipo NivelOperacion el cual contiene un estatus
	 *         que indica si se realizó correctamente la operación o si resulto
	 *         fallido para su posterior tratamiento. (0 si ocurrió un error y 1
	 *         si fue exitosa)
	 * @throws Exception
	 */
	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase nivelVO) throws Exception {
		tipoTransaccion = 6;
		this.nivelOperacion = new NivelOperacion((NivelOperacionVO) nivelVO);
		nivelOperacionBO = (NivelOperacionBO) packageDAO.launchesTransactionQuery(this);
		return (new NivelOperacionVO(nivelOperacionBO.nivelOperacion));
	}
}
