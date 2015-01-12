package com.edu.tpv.entity;

public class Impuestos {
	
	private int id;
	private String nombre;
	private double porcentaje;
	private boolean idDefecto;
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
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public boolean isIdDefecto() {
		return idDefecto;
	}
	public void setIdDefecto(boolean idDefecto) {
		this.idDefecto = idDefecto;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	

}
