package com.edu.tpv.login.entity;


public class AsigPermisos extends EntityBase
{
	private int id;
	private Usuarios usuarios;
	private ModuloOperacional moduloOperacional;
	private Empresas empresas;
	
	public AsigPermisos()
	{
		super();
	}
	public AsigPermisos(int id, Usuarios usuarios, ModuloOperacional moduloOperacional, Empresas empresas)
	{
		this.id = id;
		this.usuarios = usuarios;
		this.moduloOperacional = moduloOperacional;
		this.empresas = empresas;
	}
//	public AsigPermisos(AsigPermisosVO asigPermisosVO)
//	{
//		this.id=asigPermisosVO.getId();
//		this.usuarios=new Usuarios(asigPermisosVO.getUsuariosVO()==null?new UsuariosVO():asigPermisosVO.getUsuariosVO());
//		this.moduloOperacional=new ModuloOperacional(asigPermisosVO.getModuloOperacionalVO()==null?new ModuloOperacionalVO():asigPermisosVO.getModuloOperacionalVO());
//		this.empresas=new Empresas(asigPermisosVO.getEmpresasVO()==null?new EmpresasVO():asigPermisosVO.getEmpresasVO());
//	}
	public Usuarios getUsuarios()
	{
		return usuarios;
	}
	public void setUsuarios(Usuarios usuarios)
	{
		this.usuarios = usuarios;
	}
	public ModuloOperacional getModuloOperacional()
	{
		return moduloOperacional;
	}
	public void setModuloOperacional(ModuloOperacional moduloOperacional)
	{
		this.moduloOperacional = moduloOperacional;
	}
	public Empresas getEmpresas() {
		return empresas;
	}
	public void setEmpresas(Empresas empresas) {
		this.empresas = empresas;
	}
}
