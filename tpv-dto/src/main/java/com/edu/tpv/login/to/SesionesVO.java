package com.edu.tpv.login.to;

import java.sql.Timestamp;

import com.edu.tpv.login.jdbc.dto.DTO;

public class SesionesVO extends DTO {
	private UsuariosVO usuariosVO;
	private String idSesiones;
	private Timestamp fechaAcceso;
	private Timestamp fechaUltimoAcceso;
	private boolean activo;

	public SesionesVO() {
		super();
	}

	public SesionesVO(int id, UsuariosVO usuariosVO, String nameIdSesiones, Timestamp fechaAcceso, Timestamp fechaUltimoAcceso, boolean activo) {
		this.id = id;
		this.usuariosVO = usuariosVO;
		this.idSesiones = nameIdSesiones;
		this.fechaAcceso=fechaAcceso;
		this.fechaUltimoAcceso=fechaUltimoAcceso;
		this.activo=activo;
	}

//	public SesionesVO(Sesiones sesiones) {
//		this.id = sesiones.getId();
//		UsuariosVO usuarioVO = new UsuariosVO(sesiones.getUsuarios() == null ? new Usuarios() : sesiones.getUsuarios());
//		this.setUsuariosVO(usuarioVO);
//		this.idSesiones = sesiones.getIdSesiones();
//		this.setStatus(sesiones.getStatus());
//		this.setInicialReg(sesiones.getInicialReg());
//		this.setFinalReg(sesiones.getFinalReg());
//		this.setTotalRegistros(sesiones.getTotalRegistros());
//		this.setFechaAcceso(sesiones.getFechaAcceso());
//		this.setFechaUltimoAcceso(sesiones.getFechaUltimoAcceso());
//		this.activo=sesiones.isActivo();
//	}

	public UsuariosVO getUsuariosVO() {
		return usuariosVO;
	}

	public void setUsuariosVO(UsuariosVO usuariosVO) {
		this.usuariosVO = usuariosVO;
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
