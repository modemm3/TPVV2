package com.edu.tpv.login.entity;

import java.io.Serializable;

public class EntityBase implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int id;
	protected String idC;
	protected String nombre;
	protected String descripcion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdC() {
		return idC;
	}
	public void setIdC(String idC) {
		this.idC = idC;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
