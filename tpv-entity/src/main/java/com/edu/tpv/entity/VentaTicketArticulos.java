package com.edu.tpv.entity;

import java.sql.Timestamp;

public class VentaTicketArticulos {
	
	private int id;
	private int idTicket;
	private String productoCodigo;
	private String productoNombre;
	private double cantidad;
	private double ganancia;
	private int departamento;
	private Timestamp pagado;
	private boolean usaMayoreo;
	private double porcentajeDescuento;
	private String componentes;
	private String impuestosUsados;
	private double impuestoUnitario;
	private double precioUsado;
	private double cantidadDevuelta;
	private boolean devuelto;
	private int porcentajePagado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
	public String getProductoCodigo() {
		return productoCodigo;
	}
	public void setProductoCodigo(String productoCodigo) {
		this.productoCodigo = productoCodigo;
	}
	public String getProductoNombre() {
		return productoNombre;
	}
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getGanancia() {
		return ganancia;
	}
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}
	public int getDepartamento() {
		return departamento;
	}
	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
	public Timestamp getPagado() {
		return pagado;
	}
	public void setPagado(Timestamp pagado) {
		this.pagado = pagado;
	}
	public boolean isUsaMayoreo() {
		return usaMayoreo;
	}
	public void setUsaMayoreo(boolean usaMayoreo) {
		this.usaMayoreo = usaMayoreo;
	}
	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	public String getComponentes() {
		return componentes;
	}
	public void setComponentes(String componentes) {
		this.componentes = componentes;
	}
	public String getImpuestosUsados() {
		return impuestosUsados;
	}
	public void setImpuestosUsados(String impuestosUsados) {
		this.impuestosUsados = impuestosUsados;
	}
	public double getImpuestoUnitario() {
		return impuestoUnitario;
	}
	public void setImpuestoUnitario(double impuestoUnitario) {
		this.impuestoUnitario = impuestoUnitario;
	}
	public double getPrecioUsado() {
		return precioUsado;
	}
	public void setPrecioUsado(double precioUsado) {
		this.precioUsado = precioUsado;
	}
	public double getCantidadDevuelta() {
		return cantidadDevuelta;
	}
	public void setCantidadDevuelta(double cantidadDevuelta) {
		this.cantidadDevuelta = cantidadDevuelta;
	}
	public boolean isDevuelto() {
		return devuelto;
	}
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	public int getPorcentajePagado() {
		return porcentajePagado;
	}
	public void setPorcentajePagado(int porcentajePagado) {
		this.porcentajePagado = porcentajePagado;
	}
	
	

}
