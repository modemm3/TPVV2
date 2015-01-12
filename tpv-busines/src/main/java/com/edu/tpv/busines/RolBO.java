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
import com.edu.tpv.login.entity.Rol;
import com.edu.tpv.login.to.RolVO;

public class RolBO implements TransaccionHibernateIfz, TransactionHibernateQueryIfz {
	private PackageDAO dao = new PackageDAO("01");
	private RolBO rolBO = this;
	private int tipoTransaccion = 0;
	private Rol rol = new Rol();
	private ArrayList<InterfaceEntityBase> permisos = new ArrayList<InterfaceEntityBase>();

	@Override
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1)// guarda
			unParcelSession.saveSinTx(rol);
		else if (tipoTransaccion == 2)
			unParcelSession.modifySinTx(rol);
		else if (tipoTransaccion == 3)
			unParcelSession.deleteSintTx(rol);

		return this;
	}

	public PackageDAO getDao() {
		return dao;
	}

	@Override
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 4) {
			Vector<InterfaceEntityBase> l = dao.findByQuerySinTx(this.rol);
			Iterator<InterfaceEntityBase> it = l.iterator();
			while (it.hasNext()) {
				rol = (Rol) it.next();
			}
		}
		if (tipoTransaccion == 5) {
			Vector<InterfaceEntityBase> l = dao.findByQuerySinTx(this.rol);
			Iterator<InterfaceEntityBase> it = l.iterator();
			while (it.hasNext()) {
				rol = (Rol) it.next();

				ModuloOperacional p = new ModuloOperacional();
				p.setIdC(rol.getModuloOperacional().getIdC());
				rol.setModuloOperacional((ModuloOperacional) dao.getByIdCSinTx(p));
				permisos.add(new RolVO(rol));
			}
		}
		return this;
	}

	public InterfaceEntityBase guardarRol(InterfaceEntityBase rol) throws Exception {
		tipoTransaccion = 1;
		this.rol = (Rol) rol;
		rolBO = (RolBO) dao.launchesTransaction(this);
		return new RolVO(rolBO.rol);
	}

	public InterfaceEntityBase modificarRol(InterfaceEntityBase rol) throws Exception {
		tipoTransaccion = 2;
		this.rol = (Rol) rol;
		rolBO = (RolBO) dao.launchesTransaction(this);
		return new RolVO(rolBO.rol);
	}

	public InterfaceEntityBase eliminarRol(InterfaceEntityBase rol) throws Exception {
		tipoTransaccion = 3;
		this.rol = (Rol) rol;
		rolBO = (RolBO) dao.launchesTransaction(this);
		return new RolVO(rolBO.rol);
	}

	// public InterfazEntidadBase buscarUnRol(InterfazEntidadBase rol) throws
	// Exception
	// {
	// tipoTransaccion = 4;
	// rol.setQuery("from com.mx.coram.login.entity.Rol u where u.nombre='" +
	// rol.getNombre() + "' and u.password='" + ((RolVO) rol).getPassword() +
	// "' and u.activo=true");
	// this.rol.setQuery(rol.getQuery());
	// rolBO = (RolBO) dao.lanzaTransaccionConsulta(this);
	// RolVO rolVO = new RolVO(rolBO.rol);
	// return rolVO;
	// }
	public ArrayList<InterfaceEntityBase> obtenerRolesporUsuario(InterfaceEntityBase usuario) throws Exception {
		tipoTransaccion = 5;
		rol.setQuery("from com.mx.coram.login.entity.Rol u where u.usuario.id='" + usuario.getId() + "'");
		this.rol.setQuery(rol.getQuery());
		rolBO = (RolBO) dao.launchesTransactionQuery(this);
		// RolVO rolVO = new RolVO(rolBO.rol);
		return rolBO.permisos;
	}
}
