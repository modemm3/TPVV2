package com.edu.tpv.busines;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.jdbc.connection.dao.ConnectionJDBC;
import com.edu.tpv.login.entity.AsigPermisos;
import com.edu.tpv.login.entity.Empresas;
import com.edu.tpv.login.entity.ModuloOperacional;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.AsigPermisosVO;
import com.edu.tpv.login.to.EmpresasVO;
import com.edu.tpv.login.to.ModuloOperacionalVO;
import com.edu.tpv.login.to.PermisosVO;
import com.edu.tpv.login.to.UsuariosVO;

public class AsigPermisosBO implements TransaccionHibernateIfz, TransactionHibernateQueryIfz
{
	private PackageDAO packageDAO=new PackageDAO("01");
	private AsigPermisosBO asigPermisosBO=this;
	private int tipoTransaccion=0;
	private ArrayList<InterfaceEntityBase> lstAsigPermisosVO =new ArrayList<InterfaceEntityBase>();
	private ArrayList<PermisosVO> lstPermisosVO =  new ArrayList<PermisosVO>();
	private AsigPermisos asigPermisos=new AsigPermisos();
	private ArrayList<InterfaceEntityBase> lstAsigPermisos =new ArrayList<InterfaceEntityBase>();
	@Override
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception
	{
		Vector<InterfaceEntityBase> permisosV=packageDAO.findByQuerySinTx(this.asigPermisos);
		Iterator<InterfaceEntityBase> it=permisosV.iterator();
		Usuarios usuarios;
		ModuloOperacional moduloOperacional;
		Empresas empresas;
//		if(tipoTransaccion==4)
//		{
//		
//			while(it.hasNext())
//			{
//				asigPermisos=(AsigPermisos) it.next();
//			}
//			usuarios=new Usuarios();
//			moduloOperacional=new ModuloOperacional();
//			empresas=new Empresas();
//			
//			usuarios.setId(asigPermisos.getUsuarios().getId());
//			moduloOperacional.setId(asigPermisos.getModuloOperacional().getId());
//			empresas.setIdC(asigPermisos.getEmpresas().getIdC());
//			
//			asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
//			asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
//			asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
//		}
//		if(tipoTransaccion==5)
//		{
//		    it=packageDAO.findByQuerySinTxAL(this.asigPermisos).iterator();
//			while (it.hasNext())
//			{
//				asigPermisos = (AsigPermisos) it.next();
//				Usuarios usuario= new Usuarios();
//				usuario.setId(asigPermisos.getUsuarios().getId());
//				moduloOperacional= new ModuloOperacional();
//				moduloOperacional.setIdC(asigPermisos.getModuloOperacional().getIdC());
//				empresas= new Empresas();
//				empresas.setIdC(asigPermisos.getEmpresas().getIdC());
//				asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuario));
//				asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
//				asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
//				lstAsigPermisos.add(new AsigPermisosVO(asigPermisos));
//			}
//
//
//		}
//		if(tipoTransaccion==6)
//		{
//			lstAsigPermisos=packageDAO.findByQuerySinTxAL(this.asigPermisos);
//			while(it.hasNext())
//			{
//				asigPermisos = (AsigPermisos) it.next();
//				Usuarios usuario= new Usuarios();
//				usuario.setId(asigPermisos.getUsuarios().getId());
//				moduloOperacional= new ModuloOperacional();
//				moduloOperacional.setIdC(asigPermisos.getModuloOperacional().getIdC());
//				empresas= new Empresas();
//				empresas.setIdC(asigPermisos.getEmpresas().getIdC());
//				asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuario));
//				asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
//				asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
//				lstAsigPermisosVO.add(new AsigPermisosVO(asigPermisos));
//			}
//		}
//		if(tipoTransaccion==7)
//		{
//		
//			if(!permisosV.isEmpty())
//			{
//				asigPermisos = (AsigPermisos) permisosV.get(0);
//				usuarios=new Usuarios();
//				moduloOperacional=new ModuloOperacional();
//				empresas=new Empresas();
//				
//				usuarios.setId(asigPermisos.getUsuarios().getId());
//				moduloOperacional.setIdC(asigPermisos.getModuloOperacional().getIdC());
//				empresas.setIdC(asigPermisos.getEmpresas().getIdC());
//				
//				asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
//				asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
//				asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
//			}
//		}
//		if(tipoTransaccion==8)
//		{
//		
//			while(it.hasNext())
//			{
//				asigPermisos=(AsigPermisos) it.next();
//			}
//			usuarios=new Usuarios();
//			moduloOperacional=new ModuloOperacional();
//			empresas=new Empresas();
//			
//			usuarios.setId(asigPermisos.getUsuarios().getId());
//			moduloOperacional.setId(asigPermisos.getModuloOperacional().getId());
//			empresas.setIdC(asigPermisos.getEmpresas().getIdC());
//			
//			asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
//			asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
//			asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
//		}
//		
		switch (tipoTransaccion) {
		case 4:
			while(it.hasNext())
			{
				asigPermisos=(AsigPermisos) it.next();
			}
			usuarios=new Usuarios();
			moduloOperacional=new ModuloOperacional();
			empresas=new Empresas();
			
			usuarios.setId(asigPermisos.getUsuarios().getId());
			moduloOperacional.setId(asigPermisos.getModuloOperacional().getId());
			empresas.setIdC(asigPermisos.getEmpresas().getIdC());
			
			asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
			asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
			asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
			break;
		case 5:
			it=packageDAO.findByQuerySinTxAL(this.asigPermisos).iterator();
			while (it.hasNext())
			{
				asigPermisos = (AsigPermisos) it.next();
				Usuarios usuario= new Usuarios();
				usuario.setId(asigPermisos.getUsuarios().getId());
				moduloOperacional= new ModuloOperacional();
				moduloOperacional.setIdC(asigPermisos.getModuloOperacional().getIdC());
				empresas= new Empresas();
				empresas.setIdC(asigPermisos.getEmpresas().getIdC());
				asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuario));
				asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
				asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
				lstAsigPermisos.add(new AsigPermisosVO(asigPermisos));
			}
			break;
		case 6:
			lstAsigPermisos=packageDAO.findByQuerySinTxAL(this.asigPermisos);
			while(it.hasNext())
			{
				asigPermisos = (AsigPermisos) it.next();
				Usuarios usuario= new Usuarios();
				usuario.setId(asigPermisos.getUsuarios().getId());
				moduloOperacional= new ModuloOperacional();
				moduloOperacional.setIdC(asigPermisos.getModuloOperacional().getIdC());
				empresas= new Empresas();
				empresas.setIdC(asigPermisos.getEmpresas().getIdC());
				asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuario));
				asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
				asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
				lstAsigPermisosVO.add(new AsigPermisosVO(asigPermisos));
			}
			break;
		case 7:
			if(!permisosV.isEmpty())
			{
				asigPermisos = (AsigPermisos) permisosV.get(0);
				usuarios=new Usuarios();
				moduloOperacional=new ModuloOperacional();
				empresas=new Empresas();
				
				usuarios.setId(asigPermisos.getUsuarios().getId());
				moduloOperacional.setIdC(asigPermisos.getModuloOperacional().getIdC());
				empresas.setIdC(asigPermisos.getEmpresas().getIdC());
				
				asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
				asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
				asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
			}
			break;
		case 8:
			while(it.hasNext())
			{
				asigPermisos=(AsigPermisos) it.next();
			}
			usuarios=new Usuarios();
			moduloOperacional=new ModuloOperacional();
			empresas=new Empresas();
			
			usuarios.setId(asigPermisos.getUsuarios().getId());
			moduloOperacional.setId(asigPermisos.getModuloOperacional().getId());
			empresas.setIdC(asigPermisos.getEmpresas().getIdC());
			
			asigPermisos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
			asigPermisos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
			asigPermisos.setEmpresas((Empresas) packageDAO.getByIdCSinTx(empresas));
			break;
		case 9:
//			 List<Object> lstObjectSQL=null;			
	            final ArrayList<java.util.List<Object>> listDataSQLAL = unParcelSession.listDataSQLAL(this.asigPermisos);
				for(Object permisos : listDataSQLAL)
				{
					PermisosVO per=new PermisosVO();
					Object []temp=(Object[]) permisos;
					String cadena= temp[0].toString();
					per.setIdC(cadena);
					per.setTitulo(temp[1].toString());
					boolean band=(boolean)temp[2];
					per.setAcumulativa(band);
					lstPermisosVO.add(per);
				}
			break;

		
		}
		
		return this;

	}

	@Override
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception
	{
		if(tipoTransaccion==1)
		{
			unParcelSession.saveSinTx(asigPermisos);
		}
		else if(tipoTransaccion==2)
		{
			unParcelSession.modifySinTx(asigPermisos);
		}
		else if(tipoTransaccion==3)
		{
			unParcelSession.deleteSintTx(asigPermisos);
		}
		return this;

	}
	public InterfaceEntityBase guardarAsigPermisos(InterfaceEntityBase asigPermisosVO) throws Exception
	{
		tipoTransaccion=1;
		this.asigPermisos=new AsigPermisos((AsigPermisosVO) asigPermisosVO);
		asigPermisosBO=(AsigPermisosBO) packageDAO.launchesTransaction(this);
		return new AsigPermisosVO(asigPermisosBO.asigPermisos);
	}
	public InterfaceEntityBase modificarAsigPermisos(InterfaceEntityBase asigPermisosVO)throws Exception
	{
		tipoTransaccion=2;
		this.asigPermisos=new AsigPermisos((AsigPermisosVO) asigPermisosVO);
		asigPermisosBO=(AsigPermisosBO) packageDAO.launchesTransaction(this);
		return new AsigPermisosVO(asigPermisosBO.asigPermisos);
	}
	public InterfaceEntityBase eliminarAsigPermisos(InterfaceEntityBase asigPermisosVO)throws Exception
	{
		tipoTransaccion=3;
		this.asigPermisos=new AsigPermisos((AsigPermisosVO) asigPermisosVO);
		asigPermisosBO=(AsigPermisosBO) packageDAO.launchesTransaction(this);
		return new AsigPermisosVO(asigPermisosBO.asigPermisos);
	}
	public InterfaceEntityBase buscarAsigPermiso(InterfaceEntityBase asigPermisoVO) throws Exception
	{
		tipoTransaccion=4;
		asigPermisoVO.setQuery("from com.mx.coram.login.entity.AsigPermisos ap where ap.id="+asigPermisoVO.getId() +" order by ap.moduloOperacional.idC asc");
		this.asigPermisos.setQuery(asigPermisoVO.getQuery());
		asigPermisosBO=(AsigPermisosBO) packageDAO.launchesTransactionQuery(this);
		AsigPermisosVO asigPermisosVO=new AsigPermisosVO(asigPermisosBO.asigPermisos);
		return asigPermisosVO;
	}
	public ArrayList<InterfaceEntityBase> buscarAsigPermisosPorUsuarioList(InterfaceEntityBase asigPermisos) throws Exception
	{
		tipoTransaccion = 6;
		// this.asigPermisos=new AsigPermisos((AsigPermisosVO) asigPermisosVO);
		this.asigPermisos = new AsigPermisos((AsigPermisosVO) asigPermisos);
		this.asigPermisos.setQuery("from com.mx.coram.login.entity.AsigPermisos ap where ap.usuarios.id=" + this.asigPermisos.getUsuarios().getId()
				+ " and ap.moduloOperacional.idC like '" + this.asigPermisos.getModuloOperacional().getIdC() + "%' order by ap.moduloOperacional.idC asc");
		// asigPermisos.setQuery("from com.mx.coram.login.entity.AsigPermisos ap where ap.id="+asigPermisos.getId());
		asigPermisosBO = (AsigPermisosBO) packageDAO.launchesTransactionQuery(this);
		return asigPermisosBO.lstAsigPermisosVO;

	}
	public AsigPermisosVO buscarAsigPermisoPorModuloEmpresa(AsigPermisosVO asigPermisoVO) throws Exception
	{
		tipoTransaccion = 7;
		asigPermisoVO.setQuery("from com.mx.coram.login.entity.AsigPermisos ap where ap.usuarios.id=" + (asigPermisoVO).getUsuariosVO().getId()
				+ " and ap.moduloOperacional.idC='" + (asigPermisoVO).getModuloOperacionalVO().getIdC() + "' order by ap.moduloOperacional.idC asc");
		this.asigPermisos.setQuery(asigPermisoVO.getQuery());
		asigPermisosBO = (AsigPermisosBO) packageDAO.launchesTransactionQuery(this);
		AsigPermisosVO asigPermisosVO = new AsigPermisosVO(
				asigPermisosBO.asigPermisos);
		return asigPermisosVO;
	}
	public InterfaceEntityBase buscarAsigPermisoPorModulo(InterfaceEntityBase asigPermisoVO) throws Exception
	{
		tipoTransaccion=8;
		this.asigPermisos.setQuery("from com.mx.coram.login.entity.AsigPermisos ap where ap.usuarios.id=" + this.asigPermisos.getUsuarios().getId()
				+ " and ap.moduloOperacional.idC = '" + this.asigPermisos.getModuloOperacional().getIdC() + "' order by ap.moduloOperacional.idC asc");
		this.asigPermisos.setQuery(asigPermisoVO.getQuery());
		asigPermisosBO=(AsigPermisosBO) packageDAO.launchesTransactionQuery(this);
		AsigPermisosVO asigPermisosVO=new AsigPermisosVO(asigPermisosBO.asigPermisos);
		return asigPermisosVO;
	}	
	
	public void castingSavePermisos(List <PermisosVO> lstPermisos)
	{
		List<InterfaceEntityBase> lstAsignacionPermisos=new ArrayList<>();
		UsuariosVO usuariosVO  					= 	null;
		ModuloOperacionalVO moduloOperacionalVO =	null;
		EmpresasVO empresasVO					=	null;
		AsigPermisosVO			asignarPermisos =	null;
		String ente="";
		int numEmpleado=0;
		for (PermisosVO p : lstPermisos) 
		{
			usuariosVO  		= 	new UsuariosVO();
			moduloOperacionalVO =	new ModuloOperacionalVO();
			empresasVO			=	new EmpresasVO();
			asignarPermisos		=	new AsigPermisosVO();
			ente=p.getIdEmpresa();
			usuariosVO.setId(p.getIdEmpleado());
			numEmpleado=p.getIdEmpleado();
			moduloOperacionalVO.setIdC(p.getIdModulo());
			empresasVO.setIdC(p.getIdEmpresa());
			
			asignarPermisos.setUsuariosVO(usuariosVO);
			asignarPermisos.setModuloOperacionalVO(moduloOperacionalVO);
			asignarPermisos.setEmpresasVO(empresasVO);
			asignarPermisos.setEnte(ente);
			lstAsignacionPermisos.add(asignarPermisos);
		}
		int borrado=eliminarPermisosPrevios(numEmpleado);
		if(borrado>0)
			System.out.println("Permisos borrados.");
			//Guardando los permisos
			for (InterfaceEntityBase iEB : lstAsignacionPermisos) 
			{
				try 
				{
					guardarAsigPermisos(iEB);
				} catch (Exception e) 
				{
					System.out.println("Problemas al guardar.");
				}
			}
		
	}
	
	
	private int eliminarPermisosPrevios(int numEmpleado)
	{
		ConnectionJDBC con = new ConnectionJDBC(new data.conexionGral.Util.ConstantesUtil().getPropiedades());
		String cadSql="delete from tbcasigpermisos where fiiduser="+numEmpleado;
		PreparedStatement sentencia;
		int respuesta=0;
		try {
			sentencia = con.getConexion().prepareStatement(cadSql);
			respuesta=sentencia.executeUpdate(cadSql);
			if(con.getConexion()!=null)
				con.getConexion().close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return respuesta;
	}
	
	
}
