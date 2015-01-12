package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class AsigPermisosVO extends DTO
{
	private int id;
	private UsuariosVO usuariosVO;
	private ModuloOperacionalVO moduloOperacionalVO;
	private EmpresasVO empresasVO;
	private String ente;
	public AsigPermisosVO()
	{
		super();
	}
	public AsigPermisosVO(int id, UsuariosVO usuariosVO, ModuloOperacionalVO moduloOperacionalVO, EmpresasVO empresasVO)
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
	
	public AsigPermisosVO(int id, UsuariosVO usuariosVO,
			ModuloOperacionalVO moduloOperacionalVO, EmpresasVO empresasVO,
			String ente) {
		this.id = id;
		this.usuariosVO = usuariosVO;
		this.moduloOperacionalVO = moduloOperacionalVO;
		this.empresasVO = empresasVO;
		this.ente = ente;
	}
	public UsuariosVO getUsuariosVO()
	{
		return usuariosVO;
	}
	public void setUsuariosVO(UsuariosVO usuariosVO)
	{
		this.usuariosVO = usuariosVO;
	}
	public ModuloOperacionalVO getModuloOperacionalVO()
	{
		return moduloOperacionalVO;
	}
	public void setModuloOperacionalVO(ModuloOperacionalVO moduloOperacionalVO)
	{
		this.moduloOperacionalVO = moduloOperacionalVO;
	}
	public EmpresasVO getEmpresasVO()
	{
		return empresasVO;
	}
	public void setEmpresasVO(EmpresasVO empresasVO)
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
