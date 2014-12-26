package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class AsigPermisosVO extends DTO
{
	private int id;
	private UsuariosTO usuariosVO;
	private ModuloOperacionalTO moduloOperacionalVO;
	private EmpresasTO empresasVO;
	private String ente;
	public AsigPermisosVO()
	{
		super();
	}
	public AsigPermisosVO(int id, UsuariosTO usuariosVO, ModuloOperacionalTO moduloOperacionalVO, EmpresasTO empresasVO)
	{
		this.id = id;
		this.usuariosVO = usuariosVO;
		this.moduloOperacionalVO = moduloOperacionalVO;
		this.empresasVO = empresasVO;
	}
//	public AsigPermisosVO(AsigPermisos asigPermisos)
//	{
//		this.id=asigPermisos.getId();
//		this.usuariosVO=new UsuariosVO(asigPermisos.getUsuarios());
//		this.moduloOperacionalVO=new ModuloOperacionalVO(asigPermisos.getModuloOperacional());
//		this.empresasVO=new EmpresasVO(asigPermisos.getEmpresas());
//	}
	
	public AsigPermisosVO(int id, UsuariosTO usuariosVO,
			ModuloOperacionalTO moduloOperacionalVO, EmpresasTO empresasVO,
			String ente) {
		this.id = id;
		this.usuariosVO = usuariosVO;
		this.moduloOperacionalVO = moduloOperacionalVO;
		this.empresasVO = empresasVO;
		this.ente = ente;
	}
	public UsuariosTO getUsuariosVO()
	{
		return usuariosVO;
	}
	public void setUsuariosVO(UsuariosTO usuariosVO)
	{
		this.usuariosVO = usuariosVO;
	}
	public ModuloOperacionalTO getModuloOperacionalVO()
	{
		return moduloOperacionalVO;
	}
	public void setModuloOperacionalVO(ModuloOperacionalTO moduloOperacionalVO)
	{
		this.moduloOperacionalVO = moduloOperacionalVO;
	}
	public EmpresasTO getEmpresasVO()
	{
		return empresasVO;
	}
	public void setEmpresasVO(EmpresasTO empresasVO)
	{
		this.empresasVO = empresasVO;
	}
	public String getEnte() {
		return ente;
	}
	public void setEnte(String ente) {
		this.ente = ente;
	}
	
}
