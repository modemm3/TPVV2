package com.edu.tpv.login.entity;

import java.sql.Timestamp;

public class LogAccesos extends EntityBase {
	private int id;
	private ModuloOperacional moduloOperacional;
	private Timestamp fechaAcceso;
	private Usuarios usuarios;
	private String ipPublica;
	private String ipPrivada;

	public LogAccesos() {
		super();
	}

	public LogAccesos(int id, ModuloOperacional moduloOperacional, Timestamp fechaAcceso, Usuarios usuarios, String ipPublica, String ipPrivada) {
		this.id = id;
		this.moduloOperacional = moduloOperacional;
		this.fechaAcceso = fechaAcceso;
		this.usuarios = usuarios;
		this.ipPublica = ipPublica;
		this.ipPrivada = ipPrivada;
	}

//	public LogAccesos(LogAccesosVO logAccesosVO) {
//		this.id = logAccesosVO.getId();
//		ModuloOperacional modOperacional = new ModuloOperacional(logAccesosVO.getModuloOperacionalVO() == null ? new ModuloOperacionalVO()
//				: logAccesosVO.getModuloOperacionalVO());
//		this.setModuloOperacional(modOperacional);
//		this.fechaAcceso = logAccesosVO.getFechaAcceso();
//		Usuarios usuario = new Usuarios(logAccesosVO.getUsuariosVO() == null ? new UsuariosVO() : logAccesosVO.getUsuariosVO());
//		this.setUsuarios(usuario);
//		this.ipPublica = logAccesosVO.getIpPublica();
//		this.ipPrivada = logAccesosVO.getIpPrivada();
//		this.setStatus(logAccesosVO.getStatus());
//		this.setInicialReg(logAccesosVO.getInicialReg());
//		this.setFinalReg(logAccesosVO.getFinalReg());
//		this.setTotalRegistros(logAccesosVO.getTotalRegistros());
//	}

	public ModuloOperacional getModuloOperacional() {
		return moduloOperacional;
	}

	public void setModuloOperacional(ModuloOperacional moduloOperacional) {
		this.moduloOperacional = moduloOperacional;
	}

	public Timestamp getFechaAcceso() {
		return fechaAcceso;
	}

	public void setFechaAcceso(Timestamp fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
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
