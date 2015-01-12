package com.edu.tpv.login.entity;

import java.util.Date;

public class Productos {
	
	private String id;
	private String descripcion;
	private char tipoVenta;
	private double costo;
	private double venta;
	private int idDepartamento;
	private int idProveedores;
	private int idUnidadMedida;
	private double mayoreo;
	private boolean prioridad;
	private double cantidadInventario;
	private double cantInventarioMinimo;
	private double cantInventarioMaximo;
	private Date fechaChecado;
	private boolean porcentajeGanancia;
	private String componentes;
	private String impuestos;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public char getTipoVenta() {
		return tipoVenta;
	}
	public void setTipoVenta(char tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public double getVenta() {
		return venta;
	}
	public void setVenta(double venta) {
		this.venta = venta;
	}
	public int getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public int getIdProveedores() {
		return idProveedores;
	}
	public void setIdProveedores(int idProveedores) {
		this.idProveedores = idProveedores;
	}
	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	public double getMayoreo() {
		return mayoreo;
	}
	public void setMayoreo(double mayoreo) {
		this.mayoreo = mayoreo;
	}
	public boolean isPrioridad() {
		return prioridad;
	}
	public void setPrioridad(boolean prioridad) {
		this.prioridad = prioridad;
	}
	public double getCantidadInventario() {
		return cantidadInventario;
	}
	public void setCantidadInventario(double cantidadInventario) {
		this.cantidadInventario = cantidadInventario;
	}
	public double getCantInventarioMinimo() {
		return cantInventarioMinimo;
	}
	public void setCantInventarioMinimo(double cantInventarioMinimo) {
		this.cantInventarioMinimo = cantInventarioMinimo;
	}
	public double getCantInventarioMaximo() {
		return cantInventarioMaximo;
	}
	public void setCantInventarioMaximo(double cantInventarioMaximo) {
		this.cantInventarioMaximo = cantInventarioMaximo;
	}
	public Date getFechaChecado() {
		return fechaChecado;
	}
	public void setFechaChecado(Date fechaChecado) {
		this.fechaChecado = fechaChecado;
	}
	public boolean isPorcentajeGanancia() {
		return porcentajeGanancia;
	}
	public void setPorcentajeGanancia(boolean porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}
	public String getComponentes() {
		return componentes;
	}
	public void setComponentes(String componentes) {
		this.componentes = componentes;
	}
	public String getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}
	
	

}
