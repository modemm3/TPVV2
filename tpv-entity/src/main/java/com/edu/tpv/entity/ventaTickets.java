package com.edu.tpv.entity;

import java.sql.Timestamp;

public class ventaTickets {
	
	private int id;
	private int folio;
	private int idCaja;
	private int idCajero;
	private  String nombre;
	private Timestamp creado;
	private double subtotal;
	private double impuestos;
	private double total;
	private double ganancia;
	private boolean abierto;
	private int idCliente;
	private Timestamp vendido;
	private boolean modificable;
	private double pago;
	private String moneda;
	private int numeroArticulos;
	private Timestamp pagado;
	private boolean cancelado;
	private int idOperacion;
	private int idOldTicket;
	private String notas;
	private boolean imprimirNota;
	private char formaPago;
	private String referencia;
	private int idFactura;
	private double totalDevuelto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public int getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}
	public int getIdCajero() {
		return idCajero;
	}
	public void setIdCajero(int idCajero) {
		this.idCajero = idCajero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Timestamp getCreado() {
		return creado;
	}
	public void setCreado(Timestamp creado) {
		this.creado = creado;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getGanancia() {
		return ganancia;
	}
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}
	public boolean isAbierto() {
		return abierto;
	}
	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public Timestamp getVendido() {
		return vendido;
	}
	public void setVendido(Timestamp vendido) {
		this.vendido = vendido;
	}
	public boolean isModificable() {
		return modificable;
	}
	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}
	public double getPago() {
		return pago;
	}
	public void setPago(double pago) {
		this.pago = pago;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public int getNumeroArticulos() {
		return numeroArticulos;
	}
	public void setNumeroArticulos(int numeroArticulos) {
		this.numeroArticulos = numeroArticulos;
	}
	public Timestamp getPagado() {
		return pagado;
	}
	public void setPagado(Timestamp pagado) {
		this.pagado = pagado;
	}
	public boolean isCancelado() {
		return cancelado;
	}
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	public int getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(int idOperacion) {
		this.idOperacion = idOperacion;
	}
	public int getIdOldTicket() {
		return idOldTicket;
	}
	public void setIdOldTicket(int idOldTicket) {
		this.idOldTicket = idOldTicket;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public boolean isImprimirNota() {
		return imprimirNota;
	}
	public void setImprimirNota(boolean imprimirNota) {
		this.imprimirNota = imprimirNota;
	}
	public char getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(char formaPago) {
		this.formaPago = formaPago;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public double getTotalDevuelto() {
		return totalDevuelto;
	}
	public void setTotalDevuelto(double totalDevuelto) {
		this.totalDevuelto = totalDevuelto;
	}

	
}
