package com.edu.tpv.login.entity;

import java.sql.Timestamp;

public class Sesiones extends EntityBase {
	private Usuarios usuarios;
	private String idSesiones;
	private Timestamp fechaAcceso;
	private Timestamp fechaUltimoAcceso;
	private boolean activo;

	public Sesiones() {
		super();
	}

	public Sesiones(int id, Usuarios usuarios, String idSesiones, Timestamp fechaAcceso, Timestamp fechaUltimoAcceso, boolean activo) {
		this.id = id;
		this.usuarios = usuarios;
		this.idSesiones = idSesiones;
		this.fechaAcceso=fechaAcceso;
		this.fechaUltimoAcceso=fechaUltimoAcceso;
		this.activo=activo;
	}

//	public Sesiones(SesionesVO sesionesVO) {
//		this.id = sesionesVO.getId();
//		Usuarios usuario = new Usuarios(sesionesVO.getUsuariosVO() == null ? new UsuariosVO() : sesionesVO.getUsuariosVO());
//		this.setUsuarios(usuario);
//		this.idSesiones = sesionesVO.getIdSesiones();
//		this.setStatus(sesionesVO.getStatus());
//		this.setInicialReg(sesionesVO.getInicialReg());
//		this.setFinalReg(sesionesVO.getFinalReg());
//		this.setTotalRegistros(sesionesVO.getTotalRegistros());
//		this.setFechaAcceso(sesionesVO.getFechaAcceso());
//		this.setFechaUltimoAcceso(sesionesVO.getFechaUltimoAcceso());
//		this.activo=sesionesVO.isActivo();
//	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public String getIdSesiones() {
		return idSesiones;
	}

	public void setIdSesiones(String idSesiones) {
		this.idSesiones = idSesiones;
	}

	public Timestamp getFechaAcceso() {
		return fechaAcceso;
	}

	public void setFechaAcceso(Timestamp fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public Timestamp getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(Timestamp fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
