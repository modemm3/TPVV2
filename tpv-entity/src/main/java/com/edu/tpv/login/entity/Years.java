package com.edu.tpv.login.entity;


// Generated 15/08/2013 09:09:35 AM by Hibernate Tools 3.4.0.CR1

/**
 * Tbcyear generated by hbm2java
 */
public class Years extends EntityBase {

	private int clave;
	private String claveEmpresa;
	private String year;

	public Years() {
	}

//	public Years(YearsVO yearsVO) {
//		this.clave = yearsVO.getClave();
//		this.claveEmpresa = yearsVO.getClaveEmpresa();
//		this.year = yearsVO.getYear();
//	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getClaveEmpresa() {
		return claveEmpresa;
	}

	public void setClaveEmpresa(String claveEmpresa) {
		this.claveEmpresa = claveEmpresa;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
