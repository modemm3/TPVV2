package com.edu.tpv.entity;

public class FacturacionFolios {
	
	private int id;
	private int idEmisor;
	private String serie;
	private int folioInicial;
	private int folioFinal;
	private int siguienteFolio;
	private String numeroAprobacion;
	private int anioAprobacion;
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
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public int getFolioInicial() {
		return folioInicial;
	}
	public void setFolioInicial(int folioInicial) {
		this.folioInicial = folioInicial;
	}
	public int getFolioFinal() {
		return folioFinal;
	}
	public void setFolioFinal(int folioFinal) {
		this.folioFinal = folioFinal;
	}
	public int getSiguienteFolio() {
		return siguienteFolio;
	}
	public void setSiguienteFolio(int siguienteFolio) {
		this.siguienteFolio = siguienteFolio;
	}
	public String getNumeroAprobacion() {
		return numeroAprobacion;
	}
	public void setNumeroAprobacion(String numeroAprobacion) {
		this.numeroAprobacion = numeroAprobacion;
	}
	public int getAnioAprobacion() {
		return anioAprobacion;
	}
	public void setAnioAprobacion(int anioAprobacion) {
		this.anioAprobacion = anioAprobacion;
	}
	
	

}
