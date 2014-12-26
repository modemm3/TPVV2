package com.edu.tpv.entity;

import java.sql.Timestamp;


public class Cajas {

	private int id;
	private String nombre;
	private String ultimaIp;
	private Timestamp fechaUltIngreso;
	private String nombrePc;
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
	public String getUltimaIp() {
		return ultimaIp;
	}
	public void setUltimaIp(String ultimaIp) {
		this.ultimaIp = ultimaIp;
	}
	public Timestamp getFechaUltIngreso() {
		return fechaUltIngreso;
	}
	public void setFechaUltIngreso(Timestamp fechaUltIngreso) {
		this.fechaUltIngreso = fechaUltIngreso;
	}
	public String getNombrePc() {
		return nombrePc;
	}
	public void setNombrePc(String nombrePc) {
		this.nombrePc = nombrePc;
	}
	
}
