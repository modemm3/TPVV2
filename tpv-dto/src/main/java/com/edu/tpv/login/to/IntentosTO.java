package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class IntentosTO extends DTO{
	
	private static final long serialVersionUID = 8122194412823816167L;
	private UsuariosTO usuariosVO;
	private int noIntentos;
	private int intentosMaximos;
	private int minutos;
	private boolean activo;

	public IntentosTO() {
		super();
	}

	public IntentosTO(int id, UsuariosTO usuariosVO, int noIntentos, int intentosMaximos, int minutos, boolean activo) {
		this.id = id;
		this.usuariosVO = usuariosVO;
		this.noIntentos = noIntentos;
		this.intentosMaximos = intentosMaximos;
		this.minutos = minutos;
		this.activo = activo;
	}

//	public IntentosVO(Intentos intentos) {
//		this.id = intentos.getId();
//		UsuariosVO usuarioVO = new UsuariosVO(intentos.getUsuarios() == null ? new Usuarios() : intentos.getUsuarios());
//		this.setUsuariosVO(usuarioVO);
//		this.noIntentos = intentos.getNoIntentos();
//		this.intentosMaximos = intentos.getIntentosMaximos();
//		this.minutos = intentos.getMinutos();
//		this.activo = intentos.isActivo();
//		this.setMsgView(intentos.getMsgView());
//		this.setStatus(intentos.getStatus());
//		this.setInicialReg(intentos.getInicialReg());
//		this.setFinalReg(intentos.getFinalReg());
//		this.setTotalRegistros(intentos.getTotalRegistros());
//	}

	public UsuariosTO getUsuariosVO() {
		return usuariosVO;
	}

	public void setUsuariosVO(UsuariosTO usuariosVO) {
		this.usuariosVO = usuariosVO;
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

	public void setActivo(String activo) {
		this.activo = Boolean.parseBoolean(activo);
		if (activo.equals("on"))
			this.activo = true;
	}
}
