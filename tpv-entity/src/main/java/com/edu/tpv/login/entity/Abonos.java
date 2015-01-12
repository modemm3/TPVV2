package com.edu.tpv.login.entity;

import java.util.Date;

public class Abonos {
	
	private int id;
	private int idCliente;
	private Date fecha;
	private double monto;
	private boolean contar;
	private double ganancia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public boolean isContar() {
		return contar;
	}
	public void setContar(boolean contar) {
		this.contar = contar;
	}
	public double getGanancia() {
		return ganancia;
	}
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

}
