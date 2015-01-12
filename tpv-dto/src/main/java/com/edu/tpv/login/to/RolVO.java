package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class RolTO extends DTO
{
	private UsuariosTO usuarioVO;
	private ModuloOperacionalTO moduloOperacional;
	public RolTO()
	{
		super();
	}
//	public RolVO(Rol rol)
//	{
//		this.id=rol.getId();
//		this.usuarioVO=new UsuariosVO(rol.getUsuario());
//		this.moduloOperacional = new ModuloOperacionalVO(rol.getModuloOperacional());
//	}
	
	public RolTO(int id, UsuariosTO usuarioVO, ModuloOperacionalTO moduloOperacional)
	{
		this.id = id;
		this.usuarioVO = usuarioVO;
		this.moduloOperacional = moduloOperacional;
	}

	public UsuariosTO getUsuarioVO()
	{
		return usuarioVO;
	}
	public void setUsuarioVO(UsuariosTO usuarioVO)
	{
		this.usuarioVO = usuarioVO;
	}
	public ModuloOperacionalTO getModuloOperacional()
	{
		return moduloOperacional;
	}
	public void setModuloOperacional(ModuloOperacionalTO moduloOperacional)
	{
		this.moduloOperacional = moduloOperacional;
	}
}
