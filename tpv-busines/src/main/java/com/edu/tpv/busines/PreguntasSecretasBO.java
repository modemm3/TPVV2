package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.PreguntasSecretas;
import com.edu.tpv.login.to.PreguntasSecretasVO;

/**
 * Clase referente al Business Object del catálogo de preguntas secretas
 * (login), contiene los métodos para realizar las transacciones y las consultas
 * al catálogo.
 * 
 * @author EGH
 */
public class PreguntasSecretasBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private PreguntasSecretasBO preguntasSecretasBO = this;
	private int tipoTransaccion = 0;
	private PreguntasSecretas preguntasSecretas = new PreguntasSecretas();
	private ArrayList<PreguntasSecretasVO> lstPreguntasSecretasVO = new ArrayList<PreguntasSecretasVO>();


	public PreguntasSecretasBO() {
		packageDAO = new PackageDAO("01");
	}

	/***
	 * Método que se encarga de gestionar las operaciones de guardar, modificar
	 * y eliminar de la tabla catálogo de preguntas secretas, de acuerdo al tipo
	 * de transacción que se ha solicitado.
	 */
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			PreguntasSecretas ultimoIdPregSecret = new PreguntasSecretas();
			ultimoIdPregSecret = (PreguntasSecretas) unParcelSession.getLastIdSintTx(ultimoIdPregSecret);
			preguntasSecretas.setId(ultimoIdPregSecret.getId());
			unParcelSession.saveSinTx(preguntasSecretas);
		}
		if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(preguntasSecretas);
		}
		if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(preguntasSecretas);
		}
		return this;
	}

	/**
	 * Método que realiza la consulta en el catálogo de preguntas secretas, ya
	 * sea para obtener el listado de los registros con el que cuenta, o una
	 * búsqueda especifica, o bien para obtener el total de registros con el que
	 * cuenta dicho cátalogo.
	 */
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {

		Vector<InterfaceEntityBase> preguntasSecretV = null;
		Iterator<InterfaceEntityBase> it = null;

		if (tipoTransaccion == 4) {
			preguntasSecretV = packageDAO.findByQuerySinTx(this.preguntasSecretas);
			it = preguntasSecretV.iterator();
			while (it.hasNext()) {
				preguntasSecretas = (PreguntasSecretas) it.next();
			}
		} else if (tipoTransaccion == 5) {
			preguntasSecretV = packageDAO.listDataSin(this.preguntasSecretas);
			it = preguntasSecretV.iterator();
			while (it.hasNext()) {
				preguntasSecretas = (PreguntasSecretas) it.next();
				lstPreguntasSecretasVO.add(new PreguntasSecretasVO(preguntasSecretas));
			}
		} else if (tipoTransaccion == 6) {
			preguntasSecretas = (PreguntasSecretas) packageDAO.getTotalRegSinTx(this.preguntasSecretas);
		}
		return this;
	}

	/**
	 * Método que guarda un registro en la tabla de catálogo de preguntas
	 * secretas.
	 * 
	 * @param preguntaSecretaVO
	 *            recibe en el VO el registro a ser guardado.
	 * @return una entidad el cual contiene un estatus que indica si la
	 *         operacion fue exitosa. los estatus son: 0 si ocurrió un error
	 *         durante la operación y 1 si fue exitosa dicha operación.
	 * @throws Exception
	 */
	public InterfaceEntityBase guardarPreguntaSecreta(InterfaceEntityBase preguntaSecretaVO) throws Exception {
		tipoTransaccion = 1;
		this.preguntasSecretas = new PreguntasSecretas((PreguntasSecretasVO) preguntaSecretaVO);
		preguntasSecretasBO = (PreguntasSecretasBO) packageDAO.launchesTransaction(this);
		return new PreguntasSecretasVO(preguntasSecretasBO.preguntasSecretas);
	}

	/**
	 * Método que realiza la modificación de un registro en la tabla de cátalogo
	 * de preguntas secretas.
	 * 
	 * @param preguntaSecretaVO
	 *            recibe el VO que contiene el registro a ser modificado.
	 * @return una entidad la cual contiene un estatus, el cual indica si el
	 *         registro fue modificado correctamente, los tipos de estatus son:
	 *         0 si ocurrió un error durante la operación y 1 si fue exitosa.
	 * @throws Exception
	 */
	public InterfaceEntityBase modificarPreguntaSecreta(InterfaceEntityBase preguntaSecretaVO) throws Exception {
		tipoTransaccion = 2;
		this.preguntasSecretas = new PreguntasSecretas((PreguntasSecretasVO) preguntaSecretaVO);
		preguntasSecretasBO = (PreguntasSecretasBO) packageDAO.launchesTransaction(this);
		return new PreguntasSecretasVO(preguntasSecretasBO.preguntasSecretas);
	}

	/**
	 * Método que elimina un registro en la tabla catálogo de preguntas
	 * secretas.
	 * 
	 * @param preguntaSecretaVO
	 *            recibe el VO que contiene el registro a ser eliminado.
	 * @return una entidad la cual contiene el estatus que indica si el registro
	 *         fue eliminado. los tipos de estatus son: 0 si ocurrió un error en
	 *         la operación y 1 si el registro fue eliminado correctamente.
	 * @throws Exception
	 */
	public InterfaceEntityBase eliminarPreguntaSecreta(InterfaceEntityBase preguntaSecretaVO) throws Exception {
		tipoTransaccion = 3;
		this.preguntasSecretas = new PreguntasSecretas((PreguntasSecretasVO) preguntaSecretaVO);
		preguntasSecretasBO = (PreguntasSecretasBO) packageDAO.launchesTransaction(this);
		return new PreguntasSecretasVO(preguntasSecretasBO.preguntasSecretas);
	}

	/**
	 * Método que busca un registro en el catálogo de pregunta secreta de
	 * acuerdo a su id.
	 * 
	 * @param preguntaSecretaVO
	 * @return retorna una entidad que contiene el estatus para saber si fue
	 *         encontrado (0 si ocurrió un error en la búsqueda, 1 si se
	 *         encontro el registro y 2 si el registro no fue localizado).
	 * @throws Exception
	 */
	public InterfaceEntityBase buscarPreguntaSecreta(InterfaceEntityBase preguntaSecretaVO) throws Exception {
		preguntasSecretas=(PreguntasSecretas) packageDAO.findById(new PreguntasSecretas((PreguntasSecretasVO) preguntaSecretaVO));
		return new PreguntasSecretasVO(preguntasSecretas);
	}

	/**
	 * Método que obtiene de acuerdo a un rango definido previamente, el listado
	 * de los registros con el que cuenta la tabla de preguntas secretas.
	 * 
	 * @param preguntasSecretasVO
	 * @return un listado de los registros de acuerdo a los parametros recibidos
	 *         (start y limit), así como un estatus que indica si la operacion
	 *         fue satisfactoria (0 si ocurrió un error y 1 si fue exitosa)
	 * @throws Exception
	 */
	public ArrayList<PreguntasSecretasVO> obtenerPregSecretRangoList(PreguntasSecretasVO preguntasSecretasVO) throws Exception {
		this.preguntasSecretas = new PreguntasSecretas((PreguntasSecretasVO) preguntasSecretasVO);
		this.preguntasSecretas.setQuery("from com.mx.coram.login.entity.PreguntasSecretas ps order by ps.id");

		ArrayList<InterfaceEntityBase> pregSecretV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.preguntasSecretas);
		Iterator<InterfaceEntityBase> pregSecretIt = pregSecretV.iterator();
		while (pregSecretIt.hasNext()) {
			preguntasSecretas = (PreguntasSecretas) pregSecretIt.next();
			lstPreguntasSecretasVO.add(new PreguntasSecretasVO(preguntasSecretas));
		}

		return preguntasSecretasBO.lstPreguntasSecretasVO;
	}

	/**
	 * Método que obtiene los registros de la tabla catálogo de preguntas
	 * secretas
	 * 
	 * @param preguntasSecretasVO
	 * @return una lista de registros con el que cuenta dicha tabla. y el cual
	 *         contiene a su vez un estatus con los valores siguientes: 0 si
	 *         ocurrió un error y 1 si fue exitosa la operación.
	 * @throws Exception
	 */
	public ArrayList<PreguntasSecretasVO> obtenerPreguntasSecretasList(PreguntasSecretasVO preguntasSecretasVO) throws Exception {
		tipoTransaccion = 5;
		this.preguntasSecretas = new PreguntasSecretas(preguntasSecretasVO);
		preguntasSecretasBO = (PreguntasSecretasBO) packageDAO.launchesTransactionQuery(this);
		return preguntasSecretasBO.lstPreguntasSecretasVO;
	}

	/**
	 * Método que obtiene el total de registros de la tabla de catálogo de
	 * preguntas secretas.
	 * 
	 * @param preguntasSecretasVO
	 * @return una entidad de tipo preguntasSecretas el cual contiene un estatus
	 *         que indica si se realizó correctamente la operación o si resulto
	 *         fallido para su posterior tratamiento (0 si ocurrió un error y 1
	 *         si fue exitosa), así como el total de registros en caso sea
	 *         exitosa la operación y existan registros.
	 * @throws Exception
	 */
	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase preguntasSecretasVO) throws Exception {
		tipoTransaccion = 6;
		this.preguntasSecretas = new PreguntasSecretas((PreguntasSecretasVO) preguntasSecretasVO);
		preguntasSecretasBO = (PreguntasSecretasBO) packageDAO.launchesTransactionQuery(this);
		return (new PreguntasSecretasVO(preguntasSecretasBO.preguntasSecretas));
	}

}
