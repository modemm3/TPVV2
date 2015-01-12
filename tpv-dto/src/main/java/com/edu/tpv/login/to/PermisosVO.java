package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class PermisosTO extends DTO{
	
	private int idEmpleado;
	private String idEmpresa; 
	private String idModulo;
	private String titulo;
	private boolean asignado;
	private boolean esMenuPadre;
	private boolean acumulativa;
	public String getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(String idModulo) {
		this.idModulo = idModulo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isAsignado() {
		return asignado;
	}
	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
	}
	public boolean isEsMenuPadre() {
		return esMenuPadre;
	}
	public void setEsMenuPadre(boolean esMenuPadre) {
		this.esMenuPadre = esMenuPadre;
	}
	public boolean isAcumulativa() {
		return acumulativa;
	}
	public void setAcumulativa(boolean acumulativa) {
		this.acumulativa = acumulativa;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
}
