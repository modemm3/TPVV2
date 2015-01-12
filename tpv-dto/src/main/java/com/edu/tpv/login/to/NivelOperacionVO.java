package com.edu.tpv.login.to;

import java.io.Serializable;

import com.edu.tpv.login.jdbc.dto.DTO;

public class NivelOperacionTO extends DTO implements Serializable{
	
	private int digitos;

	public NivelOperacionTO(int id, String nombre, int digitos) {
		this.id = id;
		this.nombre = nombre;
		this.digitos = digitos;
	}

//	public NivelOperacionVO(NivelOperacion nivelOperacion) {
//		this.id = nivelOperacion.getId();
//		this.nombre = nivelOperacion.getNombre();
//		this.digitos = nivelOperacion.getDigitos();
//		this.setStatus(nivelOperacion.getStatus());
//		this.setInicialReg(nivelOperacion.getInicialReg());
//		this.setFinalReg(nivelOperacion.getFinalReg());
//		this.setTotalRegistros(nivelOperacion.getTotalRegistros());
//	}

	public NivelOperacionTO() {
		super();
	}

	public int getDigitos() {
		return digitos;
	}

	public void setDigitos(int digitos) {
		this.digitos = digitos;
	}
}
