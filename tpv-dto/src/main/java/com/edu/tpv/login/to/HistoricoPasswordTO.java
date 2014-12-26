package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class HistoricoPasswordTO extends DTO{
	
	private UsuariosTO usuariosVO;
	private String password;
	private boolean activo;

	public HistoricoPasswordTO() {
		super();
	}

	public HistoricoPasswordTO(int id, UsuariosTO usuariosVO, String password, boolean activo) {
		this.id = id;
		this.usuariosVO = usuariosVO;
		this.password = password;
		this.activo = activo;
	}

//	public HistoricoPasswordVO(HistoricoPassword historicoPassword) {
//		this.id = historicoPassword.getId();
//		this.usuariosVO = new UsuariosVO(historicoPassword.getUsuario());
//		this.password = historicoPassword.getPassword();
//		this.activo = historicoPassword.isActivo();
//		this.setStatus(historicoPassword.getStatus());
//		this.setInicialReg(historicoPassword.getInicialReg());
//		this.setFinalReg(historicoPassword.getFinalReg());
//		this.setTotalRegistros(historicoPassword.getTotalRegistros());
//	}

	public UsuariosTO getUsuariosVO() {
		return usuariosVO;
	}

	public void setUsuariosVO(UsuariosTO usuariosVO) {
		this.usuariosVO = usuariosVO;
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

	public void setActivo(String activo) {
		this.activo = Boolean.parseBoolean(activo);
		if (activo.equals("on"))
			this.activo = true;
	}
}
