package com.edu.tpv.entity;

public class PromocionesCantidad {
	
	private int id;
	private String nombre;
	private String codigoProducto;
	private double fechaDesde;
	private double fechaHasta;
	private double precioPromocion;
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
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public double getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(double fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public double getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(double fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public double getPrecioPromocion() {
		return precioPromocion;
	}
	public void setPrecioPromocion(double precioPromocion) {
		this.precioPromocion = precioPromocion;
	}
	

}
