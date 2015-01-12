package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;


public class BarraMesesTO extends DTO {
	
	private String anio;
	private String mes;
	private Boolean tipoReporte;
	private Boolean polizaXMes;
	private String periodo;
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Boolean getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(Boolean tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	public Boolean getPolizaXMes() {
		return polizaXMes;
	}
	public void setPolizaXMes(Boolean polizaXMes) {
		this.polizaXMes = polizaXMes;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
}
