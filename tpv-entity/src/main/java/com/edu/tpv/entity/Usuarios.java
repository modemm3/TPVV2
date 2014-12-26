package com.edu.tpv.entity;

import java.sql.Timestamp;

public class Usuarios {
	
	private int id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String usuario;
	private String clave;
	private boolean activo;
	private String permisos; //text
	private Timestamp fechaCreado;
	private String correo;
	private int idEstaEnCaja;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getPermisos() {
		return permisos;
	}
	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}
	public Timestamp getFechaCreado() {
		return fechaCreado;
	}
	public void setFechaCreado(Timestamp fechaCreado) {
		this.fechaCreado = fechaCreado;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getIdEstaEnCaja() {
		return idEstaEnCaja;
	}
	public void setIdEstaEnCaja(int idEstaEnCaja) {
		this.idEstaEnCaja = idEstaEnCaja;
	}
	
	

}
