package com.edu.tpv.login.entity;


public class Empresas extends EntityBase {
	private String idC;
	private String tituloEmpresa;
	private String primerTitulo;
	private String segundoTitulo;
	private String dirUno;
	private String dirDos;
	private String rfc;
	private String padron;
	private String iva;
	private String imss;
	private String regCamara;
	private String regEstatal;
	private String curp;

	public Empresas() {
		super();
	}

	public Empresas(String idC, String tituloEmpresa, String primerTitulo, String segundoTitulo, String dirUno,
			String dirDos, String rfc, String padron, String iva, String imss, String regCamara, String regEstatal, String curp) {
		this.idC = idC;
		this.tituloEmpresa = tituloEmpresa;
		this.primerTitulo = primerTitulo;
		this.segundoTitulo = segundoTitulo;
		this.dirUno = dirUno;
		this.dirDos = dirDos;
		this.rfc = rfc;
		this.padron = padron;
		this.iva = iva;
		this.imss = imss;
		this.regCamara = regCamara;
		this.regEstatal = regEstatal;
		this.curp = curp;
	}

//	public Empresas(EmpresasVO empresasVO) {
//		this.idC = empresasVO.getIdC();
//		this.tituloEmpresa = empresasVO.getTituloEmpresa();
//		this.primerTitulo = empresasVO.getPrimerTitulo();
//		this.segundoTitulo = empresasVO.getSegundoTitulo();
//		this.dirUno = empresasVO.getDirUno();
//		this.dirDos = empresasVO.getDirDos();
//		this.rfc = empresasVO.getRfc();
//		this.padron = empresasVO.getPadron();
//		this.iva = empresasVO.getIva();
//		this.imss = empresasVO.getImss();
//		this.regCamara = empresasVO.getRegCamara();
//		this.regEstatal = empresasVO.getRegEstatal();
//		this.curp = empresasVO.getCurp();
//		this.setStatus(empresasVO.getStatus());
//		this.setInicialReg(empresasVO.getInicialReg());
//		this.setFinalReg(empresasVO.getFinalReg());
//		this.setTotalRegistros(empresasVO.getTotalRegistros());
//	}

	public String getTituloEmpresa() {
		return tituloEmpresa;
	}

	public void setTituloEmpresa(String tituloEmpresa) {
		this.tituloEmpresa = tituloEmpresa;
	}

	public String getPrimerTitulo() {
		return primerTitulo;
	}

	public void setPrimerTitulo(String primerTitulo) {
		this.primerTitulo = primerTitulo;
	}

	public String getSegundoTitulo() {
		return segundoTitulo;
	}

	public void setSegundoTitulo(String segundoTitulo) {
		this.segundoTitulo = segundoTitulo;
	}

	public String getDirUno() {
		return dirUno;
	}

	public void setDirUno(String dirUno) {
		this.dirUno = dirUno;
	}

	public String getDirDos() {
		return dirDos;
	}

	public void setDirDos(String dirDos) {
		this.dirDos = dirDos;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getPadron() {
		return padron;
	}

	public void setPadron(String padron) {
		this.padron = padron;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getImss() {
		return imss;
	}

	public void setImss(String imss) {
		this.imss = imss;
	}

	public String getRegCamara() {
		return regCamara;
	}

	public void setRegCamara(String regCamara) {
		this.regCamara = regCamara;
	}

	public String getRegEstatal() {
		return regEstatal;
	}

	public void setRegEstatal(String regEstatal) {
		this.regEstatal = regEstatal;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}
}
