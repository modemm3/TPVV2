package com.edu.tpv.login.entity;


public class Intentos extends EntityBase {
	private int id;
	private Usuarios usuarios;
	private int noIntentos;
	private int intentosMaximos;
	private int minutos;
	private boolean activo;

	public Intentos() {
		super();
	}

	public Intentos(int id, Usuarios usuarios, int noIntentos, int intentosMaximos, int minutos, boolean activo) {
		this.id = id;
		this.usuarios = usuarios;
		this.noIntentos = noIntentos;
		this.intentosMaximos = intentosMaximos;
		this.minutos = minutos;
		this.activo = activo;
	}

//	public Intentos(IntentosVO intentosVO) {
//		this.id = intentosVO.getId();
//		Usuarios usuario = new Usuarios(intentosVO.getUsuariosVO() == null ? new UsuariosVO() : intentosVO.getUsuariosVO());
//		this.noIntentos = intentosVO.getNoIntentos();
//		this.intentosMaximos = intentosVO.getIntentosMaximos();
//		this.minutos = intentosVO.getMinutos();
//		this.activo = intentosVO.isActivo();
//		this.setUsuarios(usuario);
//		this.setMsgView(intentosVO.getMsgView());
//		this.setStatus(intentosVO.getStatus());
//		this.setInicialReg(intentosVO.getInicialReg());
//		this.setFinalReg(intentosVO.getFinalReg());
//		this.setTotalRegistros(intentosVO.getTotalRegistros());
//	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public int getNoIntentos() {
		return noIntentos;
	}

	public void setNoIntentos(int noIntentos) {
		this.noIntentos = noIntentos;
	}

	public int getIntentosMaximos() {
		return intentosMaximos;
	}

	public void setIntentosMaximos(int intentosMaximos) {
		this.intentosMaximos = intentosMaximos;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
