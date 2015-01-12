package com.edu.tpv.entity;

import java.sql.Timestamp;

public class HistorialInventario {
	
	private int id;
	private int idUsuario;
	private Timestamp fechaRegistro;
	private char tipoMovimiento;
	private double cantidadAnterior;
	private double cantidad;
	private String codigoProducto;
	private int idCaja;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public char getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(char tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public double getCantidadAnterior() {
		return cantidadAnterior;
	}
	public void setCantidadAnterior(double cantidadAnterior) {
		this.cantidadAnterior = cantidadAnterior;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public int getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}
	

}
