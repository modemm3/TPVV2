package com.edu.tpv.login.entity;


public class ConfiguracionXML extends EntityBase {
	private int id;
	private String controlador;
	private String password;
	private String url;
	private String nameUsuario;
	private String lenguaje;
	private GrupoSistemas gpoSistemas;
	private Empresas empresa;

	public ConfiguracionXML() {
		super();
	}

	public ConfiguracionXML(int id, String controlador, String password, String url, String nameUsuario, String lenguaje, GrupoSistemas gpoSistemas,
			Empresas empresa) {
		this.id = id;
		this.controlador = controlador;
		this.password = password;
		this.url = url;
		this.nameUsuario = nameUsuario;
		this.lenguaje = lenguaje;
		this.gpoSistemas = gpoSistemas;
		this.empresa = empresa;
	}

//	public ConfiguracionXML(ConfiguracionXMLVO configXMLVO) {
//		this.id = configXMLVO.getId();
//		this.controlador = configXMLVO.getControlador();
//		this.password = configXMLVO.getPassword();
//		this.url = configXMLVO.getUrl();
//		this.nameUsuario = configXMLVO.getNameUsuario();
//		this.lenguaje = configXMLVO.getLenguaje();
//		Empresas emp = new Empresas(configXMLVO.getEmpresaVO() == null ? new EmpresasVO() : configXMLVO.getEmpresaVO());
//		GrupoSistemas gpo = new GrupoSistemas(configXMLVO.getGpoSistemasVO() == null ? new GrupoSistemasVO() : configXMLVO.getGpoSistemasVO());
//		this.setEmpresa(emp);
//		this.setGpoSistemas(gpo);
//		this.setStatus(configXMLVO.getStatus());
//		this.setInicialReg(configXMLVO.getInicialReg());
//		this.setFinalReg(configXMLVO.getFinalReg());
//		this.setTotalRegistros(configXMLVO.getTotalRegistros());
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

	public GrupoSistemas getGpoSistemas() {
		return gpoSistemas;
	}

	public void setGpoSistemas(GrupoSistemas gpoSistemas) {
		this.gpoSistemas = gpoSistemas;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}

}
