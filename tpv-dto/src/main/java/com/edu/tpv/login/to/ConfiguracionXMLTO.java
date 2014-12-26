package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class ConfiguracionXMLTO extends DTO{
	
	
	private String controlador;
	private String password;
	private String url;
	private String nameUsuario;
	private String lenguaje;
	private EmpresasTO empresaVO;
	private GrupoSistemasTO gpoSistemasVO;

	public ConfiguracionXMLTO() {
		super();
	}

	public ConfiguracionXMLTO(int id, String controlador, String password, String url, String nameUsuario, String lenguaje, EmpresasTO empresa,
			GrupoSistemasTO gpoSistemas) {
		this.id = id;
		this.controlador = controlador;
		this.password = password;
		this.url = url;
		this.nameUsuario = nameUsuario;
		this.lenguaje = lenguaje;
		this.empresaVO = empresa;
		this.gpoSistemasVO = gpoSistemas;
	}

//	public ConfiguracionXMLVO(ConfiguracionXML configXML) {
//		this.id = configXML.getId();
//		this.controlador = configXML.getControlador();
//		this.password = configXML.getPassword();
//		this.url = configXML.getUrl();
//		this.nameUsuario = configXML.getNameUsuario();
//		this.lenguaje = configXML.getLenguaje();
//		EmpresasVO empVO = new EmpresasVO(configXML.getEmpresa() == null ? new Empresas() : configXML.getEmpresa());
//		GrupoSistemasVO gpoVO = new GrupoSistemasVO(configXML.getGpoSistemas() == null ? new GrupoSistemas() : configXML.getGpoSistemas());
//		this.setEmpresaVO(empVO);
//		this.setGpoSistemasVO(gpoVO);
//		this.setStatus(configXML.getStatus());
//		this.setInicialReg(configXML.getInicialReg());
//		this.setFinalReg(configXML.getFinalReg());
//		this.setTotalRegistros(configXML.getTotalRegistros());
//	}

	public String getControlador() {
		return controlador;
	}

	public void setControlador(String controlador) {
		this.controlador = controlador;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public EmpresasTO getEmpresaVO() {
		return empresaVO;
	}

	public void setEmpresaVO(EmpresasTO empresaVO) {
		this.empresaVO = empresaVO;
	}

	public GrupoSistemasTO getGpoSistemasVO() {
		return gpoSistemasVO;
	}

	public void setGpoSistemasVO(GrupoSistemasTO gpoSistemasVO) {
		this.gpoSistemasVO = gpoSistemasVO;
	}
}
