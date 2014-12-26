package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class CambioPasswordUsuarioTO extends  DTO{

	private UsuariosTO usuarioVO=null;
	private String password;
	private String passwordNuevo;
	private PreguntasSecretasTO preguntasSecretasVO=new PreguntasSecretasTO();
	private String respuestaSecreta;
	
	public UsuariosTO getUsuarioVO() {
		return usuarioVO;
	}
	public void setUsuarioVO(UsuariosTO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordNuevo() {
		return passwordNuevo;
	}
	public void setPasswordNuevo(String passwordNuevo) {
		this.passwordNuevo = passwordNuevo;
	}
	public PreguntasSecretasTO getPreguntasSecretasVO() {
		return preguntasSecretasVO;
	}
	public void setPreguntasSecretasVO(PreguntasSecretasTO preguntasSecretasVO) {
		this.preguntasSecretasVO = preguntasSecretasVO;
	}
	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}
	public void setRespuestaSecreta(String respuestaSecreta) {
		this.respuestaSecreta = respuestaSecreta;
	}
}
