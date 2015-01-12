package com.edu.tpv.login.to;

import java.sql.Timestamp;

import com.edu.tpv.login.jdbc.dto.DTO;

public class LogAccesosVO extends DTO{
	
	private static final long serialVersionUID = -1234141293326620862L;
	private ModuloOperacionalVO moduloOperacionalVO;
	private Timestamp fechaAcceso;
	private UsuariosVO usuariosVO;
	private String ipPublica;
	private String ipPrivada;

	public LogAccesosVO() {
		super();
	}

	public LogAccesosVO(int id, ModuloOperacionalVO moduloOperacionalVO, Timestamp fechaAcceso, UsuariosVO usuariosVO, String ipPublica, String ipPrivada) {
		this.id = id;
		this.moduloOperacionalVO = moduloOperacionalVO;
		this.fechaAcceso = fechaAcceso;
		this.usuariosVO = usuariosVO;
		this.ipPublica = ipPublica;
		this.ipPrivada = ipPrivada;
	}

//	public LogAccesosVO(LogAccesos logAccesos) {
//		this.id = logAccesos.getId();
//		ModuloOperacionalVO modOperacionalVO = new ModuloOperacionalVO(logAccesos.getModuloOperacional() == null ? new ModuloOperacional()
//				: logAccesos.getModuloOperacional());
//		this.setModuloOperacionalVO(modOperacionalVO);
//		this.fechaAcceso = logAccesos.getFechaAcceso();
//		UsuariosVO usuarioVO = new UsuariosVO(logAccesos.getUsuarios() == null ? new Usuarios() : logAccesos.getUsuarios());
//		this.setUsuariosVO(usuarioVO);
//		this.ipPublica = logAccesos.getIpPublica();
//		this.ipPrivada = logAccesos.getIpPrivada();
//		this.setStatus(logAccesos.getStatus());
//		this.setInicialReg(logAccesos.getInicialReg());
//		this.setFinalReg(logAccesos.getFinalReg());
//		this.setTotalRegistros(logAccesos.getTotalRegistros());
//	}

	public ModuloOperacionalVO getModuloOperacionalVO() {
		return moduloOperacionalVO;
	}

	public void setModuloOperacionalVO(ModuloOperacionalVO moduloOperacionalVO) {
		this.moduloOperacionalVO = moduloOperacionalVO;
	}

	public Timestamp getFechaAcceso() {
		return fechaAcceso;
	}

	public void setFechaAcceso(Timestamp fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public UsuariosVO getUsuariosVO() {
		return usuariosVO;
	}

	public void setUsuariosVO(UsuariosVO usuariosVO) {
		this.usuariosVO = usuariosVO;
	}

	public String getIpPublica() {
		return ipPublica;
	}

	public void setIpPublica(String ipPublica) {
		this.ipPublica = ipPublica;
	}

	public String getIpPrivada() {
		return ipPrivada;
	}

	public void setIpPrivada(String ipPrivada) {
		this.ipPrivada = ipPrivada;
	}
}
