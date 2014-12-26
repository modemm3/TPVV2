package com.edu.tpv.login.entity;


public class Rol extends EntityBase
{
	private Usuarios usuario;
	private ModuloOperacional moduloOperacional;
	
	public Rol()
	{
		super();
	}
//	public Rol(RolVO rolVO)
//	{
//		this.id=rolVO.getId();
//		this.usuario=new Usuarios(rolVO.getUsuarioVO());
//		this.moduloOperacional= new ModuloOperacional(rolVO.getModuloOperacional());
//	}
	
	public Rol(int id, Usuarios usuario, ModuloOperacional moduloOperacional)
	{
		this.id = id;
		this.usuario = usuario;
		this.moduloOperacional = moduloOperacional;
	}
	public Usuarios getUsuario()
	{
		return usuario;
	}
	public void setUsuario(Usuarios usuario)
	{
		this.usuario = usuario;
	}
	public ModuloOperacional getModuloOperacional()
	{
		return moduloOperacional;
	}
	public void setModuloOperacional(ModuloOperacional moduloOperacional)
	{
		this.moduloOperacional = moduloOperacional;
	}
}
