package com.edu.tpv.login.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Clientes {
	
	private int id;
	private String nombre;
	private String direccion;
	private String telefono;
	private double saldoActual;
	private Date fechaActualizaSaldo;
	private double limiteCredito;
	private Timestamp fechaUltimoPago;
	private int folioCliente;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}
	public Date getFechaActualizaSaldo() {
		return fechaActualizaSaldo;
	}
	public void setFechaActualizaSaldo(Date fechaActualizaSaldo) {
		this.fechaActualizaSaldo = fechaActualizaSaldo;
	}
	public double getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public Timestamp getFechaUltimoPago() {
		return fechaUltimoPago;
	}
	public void setFechaUltimoPago(Timestamp fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
	}
	public int getFolioCliente() {
		return folioCliente;
	}
	public void setFolioCliente(int folioCliente) {
		this.folioCliente = folioCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	
}
