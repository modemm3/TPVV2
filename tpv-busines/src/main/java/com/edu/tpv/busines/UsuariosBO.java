package com.edu.tpv.busines;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.edu.tpv.login.entity.PreguntasSecretas;
import com.edu.tpv.login.entity.UsuarioDetalle;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.PermisosVO;
import com.edu.tpv.login.to.PreguntasSecretasVO;
import com.edu.tpv.login.to.UsuarioDetalleVO;
import com.edu.tpv.login.to.UsuariosVO;
public class UsuariosBO implements TransaccionHibernateIfz, TransactionHibernateQueryIfz
{
	private PackageDAO packageDAO = new PackageDAO("01");
	private UsuariosBO userBO = this;
	private int tipoTransaccion = 0;
	private Usuarios usuarios = new Usuarios();
	private ArrayList<PermisosVO> lstPermisosVO =  new ArrayList<PermisosVO>();
	private int idUsuarioLogueado;
	
	private ArrayList<UsuariosVO> lstUsuariosVO= new ArrayList<UsuariosVO>();
	
	public UsuariosBO() {
		super();
	}

	public UsuariosBO(int idUsuarioLogueado) {
		super();
		this.idUsuarioLogueado = idUsuarioLogueado;
	}

	@Override
	
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception
	{
		usuarios.setFechaCreacion(unParcelSession.getDateCurrentSinTx());
		usuarios.setFechaModificacion(unParcelSession.getDateCurrentSinTx());
		usuarios.setActivo(true);
		if (tipoTransaccion == 1)
		{
			Usuarios nextUsuario = new Usuarios();
			nextUsuario = (Usuarios) unParcelSession.getLastIdSintTx(nextUsuario);
			usuarios.setId(nextUsuario.getId());
			unParcelSession.saveSinTx(usuarios);
		}
			
			
		else if (tipoTransaccion == 2){
			
			unParcelSession.modifySinTx(usuarios);
		}
		else if (tipoTransaccion == 3){
			
			unParcelSession.deleteSintTx(usuarios);
		}
		else if (tipoTransaccion == 7){
			final ArrayList<java.util.List<Object>> listDataSQLAL = unParcelSession.listDataSQLAL(this.usuarios);
			for(Object permisos : listDataSQLAL)
			{
				PermisosVO per=new PermisosVO();
				Object []temp=(Object[]) permisos;
				String cadena= temp[0].toString();
				per.setIdC(cadena);
				per.setTitulo(temp[1].toString());
				boolean band=(boolean)temp[2];
				per.setAsignado(band);
				per.setAcumulativa(band);
				lstPermisosVO.add(per);
			}
		}

		return this;
	}
	public PackageDAO getDao()
	{
		return packageDAO;
	}
	@Override
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception
	{
		Vector<InterfaceEntityBase> usuariosV=null;
		Iterator<InterfaceEntityBase> it=null;
		PreguntasSecretas p;
		UsuarioDetalle usuarioDetalle;

		switch (tipoTransaccion) {
		case 4:
			usuariosV = packageDAO.findByQuerySinTx(this.usuarios);
			it = usuariosV.iterator();
			while (it.hasNext())
			{
				usuarios = (Usuarios) it.next();
			}
			p = new PreguntasSecretas();
			usuarioDetalle = new UsuarioDetalle();
			usuarioDetalle.setId(usuarios.getUsuarioDetalle().getId());
			p.setId(usuarios.getPreguntasSecretas().getId());
			usuarios.setPreguntasSecretas((PreguntasSecretas) packageDAO.getByIdSinTx(p));
			usuarios.setUsuarioDetalle((UsuarioDetalle) packageDAO.getByIdSinTx(usuarioDetalle));
			break;
			
			
			
		case 5:
			usuariosV = packageDAO.listDataSin(this.usuarios);
			it = usuariosV.iterator();
			while (it.hasNext())
			{
				usuarios = (Usuarios) it.next();
				p = new PreguntasSecretas();
				usuarioDetalle = new UsuarioDetalle();
				usuarioDetalle.setId(usuarios.getUsuarioDetalle().getId());
				p.setId(usuarios.getPreguntasSecretas().getId());
				usuarios.setPreguntasSecretas((PreguntasSecretas) packageDAO.getByIdSinTx(p));
				usuarios.setUsuarioDetalle((UsuarioDetalle) packageDAO.getByIdSinTx(usuarioDetalle));
				lstUsuariosVO.add(new UsuariosVO(usuarios));
			}
			break;
			
		case 6:
			usuarios = (Usuarios) packageDAO.getTotalRegSinTx(this.usuarios);
			break;
				
		}
		
		
		return this;
		
	}
	
	
	
	
	public InterfaceEntityBase guardarUsuario(InterfaceEntityBase usuarioVO) throws Exception
	{
		tipoTransaccion = 1;
		this.usuarios = new Usuarios((UsuariosVO) usuarioVO);
		userBO = (UsuariosBO) packageDAO.launchesTransaction(this);
		return new UsuariosVO(userBO.usuarios);
	}
	
	
	
	public InterfaceEntityBase modificarUsuario(InterfaceEntityBase usuarioVO) throws Exception
	{
		tipoTransaccion = 2;
		this.usuarios = new Usuarios((UsuariosVO) usuarioVO);
		userBO = (UsuariosBO) packageDAO.launchesTransaction(this);
		return new UsuariosVO(userBO.usuarios);
	}
	
	
	
	
	public InterfaceEntityBase eliminarUsuario(InterfaceEntityBase usuarioVO) throws Exception
	{
		tipoTransaccion = 3;
		this.usuarios = new Usuarios((UsuariosVO) usuarioVO);
		userBO = (UsuariosBO) packageDAO.launchesTransaction(this);
		return new UsuariosVO(userBO.usuarios);
	}
	
	
	
	
	public InterfaceEntityBase buscarUsuario(InterfaceEntityBase usuarioVO) throws Exception
	{
		tipoTransaccion = 4;
		usuarioVO.setQuery("from com.mx.coram.login.entity.Usuarios u where u.nombre='" + usuarioVO.getNombre() + "' and u.activo=true");
		this.usuarios.setQuery(usuarioVO.getQuery());
		userBO = (UsuariosBO) packageDAO.launchesTransactionQuery(this);
		UsuariosVO usuariosVO = new UsuariosVO(userBO.usuarios);
		return usuariosVO;
	}
	
	public InterfaceEntityBase buscarUsuarioId(InterfaceEntityBase usuarioVO) throws Exception{
		usuarios=(Usuarios) packageDAO.findById(new Usuarios((UsuariosVO) usuarioVO));
		return new UsuariosVO(usuarios);
	}
	
	public ArrayList<UsuariosVO> ObtenerUsuarioList(UsuariosVO usuarioVO) throws Exception
	{
		
		String cadSql="";
		if(idUsuarioLogueado==2)//usuario coram
		{
			tipoTransaccion = 5;
			this.usuarios = new Usuarios(usuarioVO);
			userBO = (UsuariosBO) packageDAO.launchesTransactionQuery(this);
			return userBO.lstUsuariosVO;
		}	
		else	
		{

		cadSql= "(select fiid"
				+ ",fiidusuariodetalle"
				+ ",fvcnombre"
				+ ",fvcpassword"
				+ ",ftiactivo"
				+ ",ftsfechacreacion"
				+ ",ftsfechamodificacion"
				+ ",fiidusuariomodifica"
				+ ",ftsultimafechaacceso"
				+ ",ftsperiodocambiocontrasenias"
				+ ",ftihabilitarcambiocontrasenia"
				+ ",finodiasanticipadoscambiocontrasenia"
				+ ",finosesiones"
				+ ",fiidpreguntasecreta"
				+ ",fvcrespuestasecreta"
				+ "  from tbcusuarios where fiid in(select "
				+"    fiiduser "
				+"from tbcasigpermisos "
				+"where fvcidmodulo in(select "
				+"                    fvcidmodulo "
				+"                    from tbcasigpermisos "
				+"                    where fiiduser="+idUsuarioLogueado
				+"                    order by fvcidmodulo "
				+"                    ) "
				+" and fiiduser<>"+idUsuarioLogueado
				+(idUsuarioLogueado==2?"":" and fiiduser<>2")
				+" group by fiiduser "
				+" order by fvcidmodulo) order by fiid)"
				+"UNION "
			    +"(SELECT "  
			    +"       u.fiid, "
			    +"       u.fiidusuariodetalle, "
			    +"       u.fvcnombre,u.fvcpassword,u.ftiactivo,u.ftsfechacreacion, "
			    +"       u.ftsfechamodificacion,u.fiidusuariomodifica,u.ftsultimafechaacceso, "
			    +"       u.ftsperiodocambiocontrasenias,u.ftihabilitarcambiocontrasenia, "
			    +"       u.finodiasanticipadoscambiocontrasenia,u.finosesiones, "
			    +"       u.fiidpreguntasecreta,u.fvcrespuestasecreta  "
			    +"FROM tbcusuarios u "
			    +"LEFT JOIN tbcasigpermisos ap "
			    +"on u.fiid = ap.fiiduser "
			    +"WHERE ap.fiiduser is null)";
		System.out.println(cadSql);	
		ConnectionJDBC con = new ConnectionJDBC(new data.conexionGral.Util.ConstantesUtil().getPropiedades());
		final ArrayList<List<Object>> results = new ArrayList<List<Object>>();
		final PreparedStatement sentencia=con.getConexion().prepareStatement(cadSql);
		final ResultSet resultado=sentencia.executeQuery(cadSql);
		while(resultado.next()){
			final ArrayList<Object> items= new ArrayList<Object>();
			for(int i=1;i<=resultado.getMetaData().getColumnCount();i++){
				items.add(resultado.getObject(i));
			}
			results.add(items);
		}

		int cont=0;
		
		 for(Object usuario : results)
			{
			 UsuariosVO user= new UsuariosVO();
				PermisosVO per=new PermisosVO();
				List<Object>lst=(ArrayList<Object>) usuario;
				
				user.setId(Integer.parseInt(lst.get(0).toString()));
				UsuarioDetalleVO detalle= new UsuarioDetalleVO();
				detalle.setId(Integer.parseInt(lst.get(1).toString()));
//				user.setUsuarioDetalleVO(detalle);
				user.setNombre(lst.get(2).toString());
				user.setPassword(lst.get(3).toString());
				user.setActivo(Boolean.parseBoolean(lst.get(4).toString()));
//				Timestamp fechaCreacion= new Timestamp(new Date(lst.get(5).toString()).getTime());
//				user.setFechaCreacion(fechaCreacion);
//				Timestamp fechaModificacion= new Timestamp(new Date(lst.get(6).toString()).getTime());
//				user.setFechaCreacion(fechaModificacion);
				UsuariosVO usuarioModificaVO= new  UsuariosVO();
				usuarioModificaVO.setId(Integer.parseInt(lst.get(7).toString()));
				user.setUsuarioModificaVO(usuarioModificaVO);
//				Timestamp fechaAcceso= new Timestamp(new Date(lst.get(8).toString()).getTime());
//				user.setFechaCreacion(fechaAcceso);
//				Timestamp fechaPeriodocambio= new Timestamp(new Date(lst.get(9).toString()).getTime());
//				user.setFechaCreacion(fechaPeriodocambio);
				user.setHabilitarCambioContrasenia(Boolean.parseBoolean(lst.get(10).toString()));
				user.setNoDiasAnticipadosCambContrasenia(Integer.parseInt(lst.get(11).toString()));
				user.setNoSesiones(Integer.parseInt(lst.get(12).toString()));
				PreguntasSecretasVO preg= new PreguntasSecretasVO();
				preg.setId(Integer.parseInt(lst.get(13).toString()));
				PreguntasSecretas p= new PreguntasSecretas();
				UsuarioDetalle d= new UsuarioDetalle();
				d.setId(detalle.getId());
				p.setId(preg.getId());
				preg= new PreguntasSecretasVO((PreguntasSecretas) packageDAO.findById(p));
				detalle=new UsuarioDetalleVO((UsuarioDetalle) packageDAO.findById(d));
				user.setPreguntasSecretasVO(preg);
				user.setUsuarioDetalleVO(detalle);
				user.setRespuestaSecreta(lst.get(14).toString());
				lstUsuariosVO.add(user);
//				
//				user.setPreguntasSecretasVO(new PreguntasSecretasVO((PreguntasSecretas) packageDAO.getByIdSinTx(p)));
//				user.setUsuarioDetalleVO(new UsuarioDetalleVO((UsuarioDetalle) packageDAO.getByIdSinTx(usuarioDetalle)));
				
			}
//			tipoTransaccion = 7;
//			userBO = (UsuariosBO) packageDAO.launchesTransaction(this);
			return userBO.lstUsuariosVO;
		}
		
	}

	
	
	public InterfaceEntityBase obtieneTotalRegistros(InterfaceEntityBase usuarioVO)throws Exception
	{
		tipoTransaccion = 6;
		this.usuarios=new Usuarios((UsuariosVO) usuarioVO);
		userBO=(UsuariosBO) packageDAO.launchesTransactionQuery(this);
		return (new UsuariosVO(userBO.usuarios)) ;
	}
	
	public ArrayList<PermisosVO> ObtenerPermisosUsuarioList(UsuariosVO usuarioVO) throws Exception
	{
	
			tipoTransaccion = 7;

			String cadSql= "select (CASE WHEN dos.Asignado IS null THEN 'false' ELSE 'true' END) Asignado, "
					+" uno.idmodulo,  "
					+"	   uno.titulo,"
					+"	   uno.acumulativa "
					+"from ("
					+"      select"
					+"    	mo.fvcid as idmodulo,"
					+"    	mo.fvctitle as titulo,"
					+"    	mo.fbacumulativa as acumulativa"
					+"      from tbcasigpermisos ap"
					+"      inner join tbcmodulooperacional mo on ap.fvcidmodulo=mo.fvcid where ap.fiiduser="+idUsuarioLogueado+") uno "
					+"left join "
					+"      (select		"
					+"			(CASE WHEN ap.fiiduser<>0 THEN 'true' ELSE 'false' END) AS Asignado,		"
					+"	    	mo.fvcid as idmodulo		"
					+"	    from	tbcmodulooperacional AS mo "
					+"	    inner JOIN tbcasigpermisos AS ap "
					+"	    ON (ap.fvcidmodulo= mo.fvcid AND ap.fiiduser="+usuarioVO.getId()+")"
					+"        ORDER BY mo.fvcid ) dos "
					+"on dos.idmodulo=uno.idmodulo "
					+ "order by uno.idmodulo";	   
			System.out.println(cadSql);	
			ConnectionJDBC con = new ConnectionJDBC(new data.conexionGral.Util.ConstantesUtil().getPropiedades());
			final ArrayList<List<Object>> results = new ArrayList<List<Object>>();
			final PreparedStatement sentencia=con.getConexion().prepareStatement(cadSql);
			final ResultSet resultado=sentencia.executeQuery(cadSql);
			while(resultado.next()){
				final ArrayList<Object> items= new ArrayList<Object>();
				for(int i=1;i<=resultado.getMetaData().getColumnCount();i++){
					items.add(resultado.getObject(i));
				}
				results.add(items);
			}

			int cont=0;
			String asignado		=	"";
			boolean asignadoB	=	true;
			String titulo		=	"";
			String idModulo		=	"";
			boolean acumulativa=false;
			Object []vector=new Object[4];
			 for(Object permisos : results)
				{
					PermisosVO per=new PermisosVO();
					List<Object>lst=(ArrayList<Object>) permisos;
					
					vector[0]=lst.get(0);//asignado
					vector[1]=lst.get(1);//idmodulo
					vector[2]=lst.get(2);//titulo
					vector[3]=lst.get(3);//acumulativa
					
					asignado= vector[0].toString();
					if(asignado.equals("false"))
						asignadoB=false;
					else
						asignadoB=true;
					
					per.setAsignado(asignadoB);//asignado

					idModulo=vector[1].toString();//idmodulo
					per.setIdModulo(idModulo);
					per.setIdC(idModulo);
					
					titulo=vector[2].toString();//titulo
					per.setTitulo(titulo);
					
					acumulativa=(boolean)vector[3];
					per.setAcumulativa(acumulativa);//acumulativa
					
					lstPermisosVO.add(per);
				}
			 
			 
			return userBO.lstPermisosVO;
	}
	

}
