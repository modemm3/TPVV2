package com.edu.tpv.entity;

import java.sql.Timestamp;

public class Facturas {
	
	private int id;
	private String serie;
	private String folio;
	private int idFolio;
	private Timestamp generada;
	private Timestamp transaccion;
	private int idFacturacionCliente;
	private int idFacturacionEmisor;
	private int idVentaTicket;
	private int ventaTicketFolio;
	private double subtotal;
	private double impuestos;
	private double total;
	private Timestamp cancelada;
	private char tipoFactura;
	private int idFacturacionInforme;
	private int idFacturacionCertificado;
	private Object xml;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public int getIdFolio() {
		return idFolio;
	}
	public void setIdFolio(int idFolio) {
		this.idFolio = idFolio;
	}
	public Timestamp getGenerada() {
		return generada;
	}
	public void setGenerada(Timestamp generada) {
		this.generada = generada;
	}
	public Timestamp getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(Timestamp transaccion) {
		this.transaccion = transaccion;
	}
	public int getIdFacturacionCliente() {
		return idFacturacionCliente;
	}
	public void setIdFacturacionCliente(int idFacturacionCliente) {
		this.idFacturacionCliente = idFacturacionCliente;
	}
	public int getIdFacturacionEmisor() {
		return idFacturacionEmisor;
	}
	public void setIdFacturacionEmisor(int idFacturacionEmisor) {
		this.idFacturacionEmisor = idFacturacionEmisor;
	}
	public int getIdVentaTicket() {
		return idVentaTicket;
	}
	public void setIdVentaTicket(int idVentaTicket) {
		this.idVentaTicket = idVentaTicket;
	}
	public int getVentaTicketFolio() {
		return ventaTicketFolio;
	}
	public void setVentaTicketFolio(int ventaTicketFolio) {
		this.ventaTicketFolio = ventaTicketFolio;
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
	public Timestamp getCancelada() {
		return cancelada;
	}
	public void setCancelada(Timestamp cancelada) {
		this.cancelada = cancelada;
	}
	public char getTipoFactura() {
		return tipoFactura;
	}
	public void setTipoFactura(char tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	public int getIdFacturacionInforme() {
		return idFacturacionInforme;
	}
	public void setIdFacturacionInforme(int idFacturacionInforme) {
		this.idFacturacionInforme = idFacturacionInforme;
	}
	public int getIdFacturacionCertificado() {
		return idFacturacionCertificado;
	}
	public void setIdFacturacionCertificado(int idFacturacionCertificado) {
		this.idFacturacionCertificado = idFacturacionCertificado;
	}
	public Object getXml() {
		return xml;
	}
	public void setXml(Object xml) {
		this.xml = xml;
	}
	
	

}
