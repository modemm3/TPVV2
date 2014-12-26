package com.edu.tpv.entity;

import java.sql.Timestamp;

public class Operaciones {
	
	private int id;
	private double dineroCaja;
	private double tipoCambio;
	private int idInicioUsuario;
	private Timestamp inicio;
	private Timestamp cerro;
	private int idCaja;
	private boolean abierta;
	private double ventas;
	private double salidas;
	private double entradas;
	private double pagos;
	private double impuestos;
	private double ganancias;
	private int abono;
	private double ingresoTarjetas;
	private double ingresoVales;
	private double ingresosEfectivo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDineroCaja() {
		return dineroCaja;
	}
	public void setDineroCaja(double dineroCaja) {
		this.dineroCaja = dineroCaja;
	}
	public double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public int getIdInicioUsuario() {
		return idInicioUsuario;
	}
	public void setIdInicioUsuario(int idInicioUsuario) {
		this.idInicioUsuario = idInicioUsuario;
	}
	public Timestamp getInicio() {
		return inicio;
	}
	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}
	public Timestamp getCerro() {
		return cerro;
	}
	public void setCerro(Timestamp cerro) {
		this.cerro = cerro;
	}
	public int getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}
	public boolean isAbierta() {
		return abierta;
	}
	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}
	public double getVentas() {
		return ventas;
	}
	public void setVentas(double ventas) {
		this.ventas = ventas;
	}
	public double getSalidas() {
		return salidas;
	}
	public void setSalidas(double salidas) {
		this.salidas = salidas;
	}
	public double getEntradas() {
		return entradas;
	}
	public void setEntradas(double entradas) {
		this.entradas = entradas;
	}
	public double getPagos() {
		return pagos;
	}
	public void setPagos(double pagos) {
		this.pagos = pagos;
	}
	public double getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}
	public double getGanancias() {
		return ganancias;
	}
	public void setGanancias(double ganancias) {
		this.ganancias = ganancias;
	}
	public int getAbono() {
		return abono;
	}
	public void setAbono(int abono) {
		this.abono = abono;
	}
	public double getIngresoTarjetas() {
		return ingresoTarjetas;
	}
	public void setIngresoTarjetas(double ingresoTarjetas) {
		this.ingresoTarjetas = ingresoTarjetas;
	}
	public double getIngresoVales() {
		return ingresoVales;
	}
	public void setIngresoVales(double ingresoVales) {
		this.ingresoVales = ingresoVales;
	}
	public double getIngresosEfectivo() {
		return ingresosEfectivo;
	}
	public void setIngresosEfectivo(double ingresosEfectivo) {
		this.ingresosEfectivo = ingresosEfectivo;
	}
	

}
