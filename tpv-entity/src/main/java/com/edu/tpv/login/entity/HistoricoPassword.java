package com.edu.tpv.login.entity;


public class HistoricoPassword extends EntityBase {
	private int id;
	private Usuarios usuario;
	private String password;
	private boolean activo;

	public HistoricoPassword() {
		super();
	}

	public HistoricoPassword(int id, Usuarios usuario, String password, boolean activo) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.activo = activo;
	}

//	public HistoricoPassword(HistoricoPasswordVO historicoPasswordVO) {
//		this.id = historicoPasswordVO.getId();
//		this.usuario = new Usuarios(historicoPasswordVO.getUsuariosVO() == null ? new UsuariosVO() : historicoPasswordVO.getUsuariosVO());
//		this.password = historicoPasswordVO.getPassword();
//		this.activo = historicoPasswordVO.isActivo();
//		this.setStatus(historicoPasswordVO.getStatus());
//		this.setInicialReg(historicoPasswordVO.getInicialReg());
//		this.setFinalReg(historicoPasswordVO.getFinalReg());
//		this.setTotalRegistros(historicoPasswordVO.getTotalRegistros());
//	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
