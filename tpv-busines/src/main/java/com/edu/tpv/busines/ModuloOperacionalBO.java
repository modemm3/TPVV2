package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.ModuloOperacional;
import com.edu.tpv.login.entity.NivelOperacion;
import com.edu.tpv.login.entity.TipoAplicacion;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.ModuloOperacionalVO;

public class ModuloOperacionalBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private ModuloOperacionalBO moduloOperacionalBO = this;
	private int tipoTransaccion = 0;
	private ModuloOperacional moduloOperacional = null;
	private ArrayList<ModuloOperacionalVO> lstModuloOperacionalVO = new ArrayList<ModuloOperacionalVO>();

	public ModuloOperacionalBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			moduloOperacional.setFechaCreacion(unParcelSession.getDateCurrentSinTx());
			moduloOperacional.setFechaModificacion(unParcelSession.getDateCurrentSinTx());

			unParcelSession.saveSinTx(moduloOperacional);
		} else if (tipoTransaccion == 2) {
			moduloOperacional.setFechaModificacion(unParcelSession.getDateCurrentSinTx());
			unParcelSession.modifySinTx(moduloOperacional);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(moduloOperacional);
		}
		return this;
	}

	@Override
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Usuarios usuarios = null;
		TipoAplicacion tipoAplicacion = null;
		NivelOperacion nivelOperacion = null;

		if (tipoTransaccion == 5) {
			Vector<InterfaceEntityBase> moduloOperacionalV = packageDAO.listDataSin(this.moduloOperacional);
			Iterator<InterfaceEntityBase> moduloOperacionalIt = moduloOperacionalV.iterator();
			while (moduloOperacionalIt.hasNext()) {
				moduloOperacional = (ModuloOperacional) moduloOperacionalIt.next();
				usuarios = new Usuarios();
				tipoAplicacion = new TipoAplicacion();
				nivelOperacion = new NivelOperacion();
				usuarios.setId(moduloOperacional.getUsuario().getId());
				tipoAplicacion.setId(moduloOperacional.getTipoAplicacion().getId());
				nivelOperacion.setId(moduloOperacional.getNivelOperacion().getId());
				moduloOperacional.setUsuario((Usuarios) packageDAO.getByIdSinTx(usuarios));
				moduloOperacional.setTipoAplicacion((TipoAplicacion) packageDAO.getByIdSinTx(tipoAplicacion));
				moduloOperacional.setNivelOperacion((NivelOperacion) packageDAO.getByIdSinTx(nivelOperacion));
				lstModuloOperacionalVO.add(new ModuloOperacionalVO(moduloOperacional));
			}
		} else if (tipoTransaccion == 6) {
			moduloOperacional = (ModuloOperacional) packageDAO.getTotalRegSinTx(this.moduloOperacional);
		}

		return this;
	}

	public InterfaceEntityBase guardarModuloOperacional(InterfaceEntityBase moduloOperacionalVO) throws Exception {
		tipoTransaccion = 1;
		this.moduloOperacional = new ModuloOperacional((ModuloOperacionalVO) moduloOperacionalVO);
		moduloOperacionalBO = (ModuloOperacionalBO) packageDAO.launchesTransaction(this);
		return new ModuloOperacionalVO(moduloOperacionalBO.moduloOperacional);
	}
	public InterfaceEntityBase modificarModuloOperacional(InterfaceEntityBase moduloOperacionalVO) throws Exception {
		tipoTransaccion = 2;
		this.moduloOperacional = new ModuloOperacional((ModuloOperacionalVO) moduloOperacionalVO);
		moduloOperacionalBO = (ModuloOperacionalBO) packageDAO.launchesTransaction(this);
		return new ModuloOperacionalVO(moduloOperacionalBO.moduloOperacional);
	}

	public InterfaceEntityBase eliminarModuloOperacional(InterfaceEntityBase moduloOperacionalVO) throws Exception {
		tipoTransaccion = 3;
		this.moduloOperacional = new ModuloOperacional((ModuloOperacionalVO) moduloOperacionalVO);
		moduloOperacionalBO = (ModuloOperacionalBO) packageDAO.launchesTransaction(this);
		return new ModuloOperacionalVO(moduloOperacionalBO.moduloOperacional);
	}

	public InterfaceEntityBase buscarModuloOperacional(InterfaceEntityBase moduloOperacionalVO) throws Exception {
		moduloOperacional = (ModuloOperacional) packageDAO.findByIdC(new ModuloOperacional((ModuloOperacionalVO) moduloOperacionalVO));
		return new ModuloOperacionalVO(moduloOperacional);
	}

	public ArrayList<ModuloOperacionalVO> obtenerModuloOperacionalRangoList(ModuloOperacionalVO moduloOperacionalVO) throws Exception {
		this.moduloOperacional = new ModuloOperacional((ModuloOperacionalVO) moduloOperacionalVO);
		this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop order by modop.idC");

		ArrayList<InterfaceEntityBase> moduloOperacionalV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.moduloOperacional);
		Iterator<InterfaceEntityBase> moduloOperacionalIt = moduloOperacionalV.iterator();
		while (moduloOperacionalIt.hasNext()) {
			moduloOperacional = (ModuloOperacional) moduloOperacionalIt.next();
			lstModuloOperacionalVO.add(new ModuloOperacionalVO(moduloOperacional));
		}

		return moduloOperacionalBO.lstModuloOperacionalVO;
	}

	public ArrayList<ModuloOperacionalVO> obtenerModuloOperacionalList(ModuloOperacionalVO moduloOperacionalVO) throws Exception {
		tipoTransaccion = 5;
		this.moduloOperacional = new ModuloOperacional((ModuloOperacionalVO) moduloOperacionalVO);
		moduloOperacionalBO = (ModuloOperacionalBO) packageDAO.launchesTransactionQuery(this);
		return moduloOperacionalBO.lstModuloOperacionalVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase moduloOperacionalVO) throws Exception {
		tipoTransaccion = 6;
		this.moduloOperacional = new ModuloOperacional((ModuloOperacionalVO) moduloOperacionalVO);
		moduloOperacionalBO = (ModuloOperacionalBO) packageDAO.launchesTransactionQuery(this);
		return (new ModuloOperacionalVO(moduloOperacionalBO.moduloOperacional));
	}
	
/**
 *  Realiza la busquedas de todos los modulos hermanos de de tal manera que se ingresa el modulo padre del nodo que se quiere insertar y 
 *  busca todos los nodos que esten al nivel del nodo padre del que se quiere insertar.
 * @param moduloOPeracionalVO
 * @return el listado de modulos en donde concida los nodos hermanos
 */
public ArrayList<ModuloOperacionalVO> getModulosHermanos(ModuloOperacionalVO moduloOPeracionalVO){
	this.moduloOperacional= new ModuloOperacional();
	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.nombre in(select nombre from com.mx.coram.login.entity.ModuloOperacional m where m.idC='"+moduloOPeracionalVO.getIdC()+"')");
	ArrayList<InterfaceEntityBase> modulos=packageDAO.findByQueryAL(moduloOperacional);
	Iterator<InterfaceEntityBase> moduloOperacionalIt = modulos.iterator();
	while (moduloOperacionalIt.hasNext()) {
		moduloOperacional = (ModuloOperacional) moduloOperacionalIt.next();
		lstModuloOperacionalVO.add(new ModuloOperacionalVO(moduloOperacional));
	}
	
	return lstModuloOperacionalVO;
	}
public ArrayList<ModuloOperacionalVO> getModulosHermanosExistentes(ModuloOperacionalVO moduloOPeracionalVO){
	this.moduloOperacional= new ModuloOperacional();
	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.idC like (select idC from com.mx.coram.login.entity.ModuloOperacional m where m.nombre like '"+moduloOPeracionalVO.getNombre()+"' group by m.nombre)+'%'");
//	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.nombre in(select nombre from com.mx.coram.login.entity.ModuloOperacional m where m.idC='"+moduloOPeracionalVO.getIdC()+"')");
	ArrayList<InterfaceEntityBase> modulos=packageDAO.findByQueryAL(moduloOperacional);
	Iterator<InterfaceEntityBase> moduloOperacionalIt = modulos.iterator();
	while (moduloOperacionalIt.hasNext()) {
		moduloOperacional = (ModuloOperacional) moduloOperacionalIt.next();
		lstModuloOperacionalVO.add(new ModuloOperacionalVO(moduloOperacional));
	}
	
	return lstModuloOperacionalVO;
}

public ArrayList<ModuloOperacionalVO> getModulosXNombre(ModuloOperacionalVO moduloOPeracionalVO){
	this.moduloOperacional= new ModuloOperacional();
	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.nombre like '"+moduloOPeracionalVO.getNombre()+"%'");
//	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.nombre in(select nombre from com.mx.coram.login.entity.ModuloOperacional m where m.idC='"+moduloOPeracionalVO.getIdC()+"')");
	ArrayList<InterfaceEntityBase> modulos=packageDAO.findByQueryAL(moduloOperacional);
	Iterator<InterfaceEntityBase> moduloOperacionalIt = modulos.iterator();
	while (moduloOperacionalIt.hasNext()) {
		moduloOperacional = (ModuloOperacional) moduloOperacionalIt.next();
		lstModuloOperacionalVO.add(new ModuloOperacionalVO(moduloOperacional));
	}
	
	return lstModuloOperacionalVO;
}
public ArrayList<ModuloOperacionalVO> getModulosXCoincidenciaId(ModuloOperacionalVO moduloOPeracionalVO){
	this.moduloOperacional= new ModuloOperacional();
	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.idC like '"+moduloOPeracionalVO.getIdC()+"%'");
//	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.nombre in(select nombre from com.mx.coram.login.entity.ModuloOperacional m where m.idC='"+moduloOPeracionalVO.getIdC()+"')");
	ArrayList<InterfaceEntityBase> modulos=packageDAO.findByQueryAL(moduloOperacional);
	Iterator<InterfaceEntityBase> moduloOperacionalIt = modulos.iterator();
	while (moduloOperacionalIt.hasNext()) {
		moduloOperacional = (ModuloOperacional) moduloOperacionalIt.next();
		lstModuloOperacionalVO.add(new ModuloOperacionalVO(moduloOperacional));
	}
	
	return lstModuloOperacionalVO;
}
public ArrayList<ModuloOperacionalVO> getModulosHijosXNombre(ModuloOperacionalVO moduloOPeracionalVO){
	try{
	this.moduloOperacional= new ModuloOperacional();
	moduloOPeracionalVO.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.nombre like '"+moduloOPeracionalVO.getNombre()+"%'");
	Vector<InterfaceEntityBase> listado=packageDAO.findByQuery(moduloOPeracionalVO);
	for(InterfaceEntityBase entidad:listado){
		moduloOPeracionalVO= new ModuloOperacionalVO((ModuloOperacional) entidad);
		break;
	}
	//this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.nombre like '"+moduloOPeracionalVO.getNombre()+"%'");
	this.moduloOperacional.setQuery("from com.mx.coram.login.entity.ModuloOperacional modop where modop.idC like '"+moduloOPeracionalVO.getIdC()+"%'");
	ArrayList<InterfaceEntityBase> modulos=packageDAO.findByQueryAL(moduloOperacional);
	Iterator<InterfaceEntityBase> moduloOperacionalIt = modulos.iterator();
	while (moduloOperacionalIt.hasNext()) {
		moduloOperacional = (ModuloOperacional) moduloOperacionalIt.next();
		lstModuloOperacionalVO.add(new ModuloOperacionalVO(moduloOperacional));
	}
	}catch(Exception ex){
//		LogProperties.log.error("un error al consultar los modulos",ex);
	}
	return lstModuloOperacionalVO;
}
	public ModuloOperacionalVO guardarModulosMasivo(ModuloOperacionalVO moduloOperacionalVO) {
		StringBuilder cuenta = new StringBuilder();
		String cuentaNueva = "";
		
		String[] cuentaPadre = moduloOperacionalVO.getIdC().split(" ");
		cuentaNueva = cuentaPadre[cuentaPadre.length - 1];
		for (int i = 0; i < cuentaPadre.length-1; i++) {
			cuenta.append(cuentaPadre[i] + " ");
		}
		System.out.println(cuenta.toString());
		ModuloOperacionalVO m2 = new ModuloOperacionalVO();
		m2.setIdC(cuenta.toString().trim());
		ArrayList<ModuloOperacionalVO> mod = this.getModulosHermanos(m2);
		if (!mod.isEmpty()) {
			for (ModuloOperacionalVO mo : mod) {
				moduloOperacionalVO.setIdC(mo.getIdC() + " " + cuentaNueva);
				try {
					m2 = (ModuloOperacionalVO) this.guardarModuloOperacional(moduloOperacionalVO);
					m2.setMsgView("Se guardo Correctamente");
				} catch (Exception ex) {
					m2.setStatus(0);
					m2.setMsgView("Ocurrio un error al guardar los datos");
					return m2;
				}
			}
			m2.setMsgView("NO se encontrola cuenta padre");
		} else if(cuentaPadre.length==1)
		{
			try {
				m2 = (ModuloOperacionalVO) this.guardarModuloOperacional(moduloOperacionalVO);
				m2.setMsgView("NO se encontro la cuenta padre se crea");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m2;
	}
	public ModuloOperacionalVO eliminaModulosMasivo(ModuloOperacionalVO moduloOperacionalVO) {
		StringBuilder cuenta = new StringBuilder();
		String cuentaNueva = "";
		String[] cuentaPadre = moduloOperacionalVO.getIdC().split(" ");
		cuentaNueva = cuentaPadre[cuentaPadre.length - 1];
		for (int i = 0; i < cuentaPadre.length - 1; i++) {
			cuenta.append(cuentaPadre[i] + " ");
		}
		System.out.println(cuenta.toString());
		ModuloOperacionalVO m2 = new ModuloOperacionalVO();
		m2.setIdC(cuenta.toString().trim());
		ArrayList<ModuloOperacionalVO> mod = this.getModulosHermanos(m2);
		if (!mod.isEmpty()) {
			for (ModuloOperacionalVO mo : mod) {
				moduloOperacionalVO.setIdC(mo.getIdC() + " " + cuentaNueva);
				try {
					m2 = (ModuloOperacionalVO) this.eliminarModuloOperacional(moduloOperacionalVO);
					m2.setMsgView("Se guardo Correctamente");
				} catch (Exception ex) {
					m2.setStatus(0);
					m2.setMsgView("Ocurrio un error al guardar los datos");
					return m2;
				}
			}
			m2.setMsgView("NO se encontrola cuenta padre");
		} else if(cuentaPadre.length==1){
			try {
				m2 = (ModuloOperacionalVO) this.eliminarModuloOperacional(moduloOperacionalVO);
				m2.setMsgView("NO se encontro la cuenta padre se crea");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m2;
	}
	public ModuloOperacionalVO modificaModulosMasivo(ModuloOperacionalVO moduloOperacionalVO) {
		StringBuilder cuenta = new StringBuilder();
		String cuentaNueva = "";
		String[] cuentaPadre = moduloOperacionalVO.getIdC().split(" ");
		cuentaNueva = cuentaPadre[cuentaPadre.length - 1];
		for (int i = 0; i < cuentaPadre.length-1; i++) {
			cuenta.append(cuentaPadre[i] + " ");
		}
		System.out.println(cuenta.toString());
		ModuloOperacionalVO m2 = new ModuloOperacionalVO();
		m2.setIdC(cuenta.toString().trim());
		ArrayList<ModuloOperacionalVO> mod = this.getModulosHermanos(m2);
		if (!mod.isEmpty()) {
			for (ModuloOperacionalVO mo : mod) {
				moduloOperacionalVO.setIdC(mo.getIdC() + " " + cuentaNueva);
				try {
					m2 = (ModuloOperacionalVO) this.modificarModuloOperacional(moduloOperacionalVO);
					m2.setMsgView("Se guardo Correctamente");
				} catch (Exception ex) {
					m2.setStatus(0);
					m2.setMsgView("Ocurrio un error al guardar los datos");
					return m2;
				}
			}
			m2.setMsgView("NO se encontro la cuenta padre");
		} else if(cuentaPadre.length==1){
			try {
				m2 = (ModuloOperacionalVO) this.modificarModuloOperacional(moduloOperacionalVO);
				m2.setMsgView("NO se encontro la cuenta padre se crea");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m2;
	}
}
