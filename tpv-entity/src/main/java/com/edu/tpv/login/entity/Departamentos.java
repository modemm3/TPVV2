package com.edu.tpv.entity;


public class Departamentos {
	
	private int id;
	private String nombre;
	private double porcentajeImpuesto;
	private boolean activo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPorcentajeImpuesto() {
		return porcentajeImpuesto;
	}
	public void setPorcentajeImpuesto(double porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
}
