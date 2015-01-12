package com.edu.tpv.login.entity;

import java.sql.Timestamp;

public class FacturacionInformes {
	
	private int id;
	private int mes;
	private int anio;
	private Timestamp generado;
	private Object contenido;
	private Timestamp enviado;
	private char tipo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public Timestamp getGenerado() {
		return generado;
	}
	public void setGenerado(Timestamp generado) {
		this.generado = generado;
	}
	public Object getContenido() {
		return contenido;
	}
	public void setContenido(Object contenido) {
		this.contenido = contenido;
	}
	public Timestamp getEnviado() {
		return enviado;
	}
	public void setEnviado(Timestamp enviado) {
		this.enviado = enviado;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	

}

