package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

/**
 * Tbcyear generated by hbm2java
 */
public class YearsVO extends DTO{

	
	private static final long serialVersionUID = -6426868876327422456L;
	private int clave;
	private String claveEmpresa;
	private String year;

	public YearsVO() {
	}

//	public YearsVO(Years years) {
//		this.clave = years.getClave();
//		this.claveEmpresa = years.getClaveEmpresa();
//		this.year = years.getYear();
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
