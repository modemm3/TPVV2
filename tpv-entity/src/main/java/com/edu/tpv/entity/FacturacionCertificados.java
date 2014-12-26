package com.edu.tpv.entity;

import java.sql.Timestamp;

public class FacturacionCertificados {
	
	private int id;
	private int idEmisor;
	private String numeroSerie;
	private Timestamp fechaVigInicio;
	private Timestamp fechaVigFin;
	private String llavePrivada;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdEmisor() {
		return idEmisor;
	}
	public void setIdEmisor(int idEmisor) {
		this.idEmisor = idEmisor;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public Timestamp getFechaVigInicio() {
		return fechaVigInicio;
	}
	public void setFechaVigInicio(Timestamp fechaVigInicio) {
		this.fechaVigInicio = fechaVigInicio;
	}
	public Timestamp getFechaVigFin() {
		return fechaVigFin;
	}
	public void setFechaVigFin(Timestamp fechaVigFin) {
		this.fechaVigFin = fechaVigFin;
	}
	public String getLlavePrivada() {
		return llavePrivada;
	}
	public void setLlavePrivada(String llavePrivada) {
		this.llavePrivada = llavePrivada;
	}
	

}
