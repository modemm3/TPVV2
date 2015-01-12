package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class CambioPasswordUsuarioVO extends  DTO{

	private UsuariosVO usuarioVO=null;
	private String password;
	private String passwordNuevo;
	private PreguntasSecretasVO preguntasSecretasVO=new PreguntasSecretasVO();
	private String respuestaSecreta;
	
	public UsuariosVO getUsuarioVO() {
		return usuarioVO;
	}
	public void setUsuarioVO(UsuariosVO usuarioVO) {
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
	public PreguntasSecretasVO getPreguntasSecretasVO() {
		return preguntasSecretasVO;
	}
	public void setPreguntasSecretasVO(PreguntasSecretasVO preguntasSecretasVO) {
		this.preguntasSecretasVO = preguntasSecretasVO;
	}
	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}
	public void setRespuestaSecreta(String respuestaSecreta) {
		this.respuestaSecreta = respuestaSecreta;
	}
}
