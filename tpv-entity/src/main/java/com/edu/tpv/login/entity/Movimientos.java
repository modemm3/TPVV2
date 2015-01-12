package com.edu.tpv.login.entity;

import java.sql.Timestamp;

public class Movimientos {
	
	private int id;
	private int idOperacion;
	private double monto;
	private Timestamp fechaRegistroAnt;
	private String comentarios;
	private char tipoMovimiento;
	private int idCliente;
	private int idCaja;
	private int idCajero;
	private int idAbono;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(int idOperacion) {
		this.idOperacion = idOperacion;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Timestamp getFechaRegistroAnt() {
		return fechaRegistroAnt;
	}
	public void setFechaRegistroAnt(Timestamp fechaRegistroAnt) {
		this.fechaRegistroAnt = fechaRegistroAnt;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public char getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(char tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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
	public int getIdAbono() {
		return idAbono;
	}
	public void setIdAbono(int idAbono) {
		this.idAbono = idAbono;
	}
	
	
}
