package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class RolVO extends DTO
{
	private UsuariosVO usuarioVO;
	private ModuloOperacionalVO moduloOperacional;
	public RolVO()
	{
		super();
	}
//	public RolVO(Rol rol)
//	{
//		this.id=rol.getId();
//		this.usuarioVO=new UsuariosVO(rol.getUsuario());
//		this.moduloOperacional = new ModuloOperacionalVO(rol.getModuloOperacional());
//	}
	
	public RolVO(int id, UsuariosVO usuarioVO, ModuloOperacionalVO moduloOperacional)
	{
		this.id = id;
		this.usuarioVO = usuarioVO;
		this.moduloOperacional = moduloOperacional;
	}

	public UsuariosVO getUsuarioVO()
	{
		return usuarioVO;
	}
	public void setUsuarioVO(UsuariosVO usuarioVO)
	{
		this.usuarioVO = usuarioVO;
	}
	public ModuloOperacionalVO getModuloOperacional()
	{
		return moduloOperacional;
	}
	public void setModuloOperacional(ModuloOperacionalVO moduloOperacional)
	{
		this.moduloOperacional = moduloOperacional;
	}
}
