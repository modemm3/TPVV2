package com.edu.tpv.login.entity;


public class NivelOperacion extends EntityBase {
	private int digitos;

	public NivelOperacion() {
		super();
	}

	public NivelOperacion(int id, String nombre, int digitos) {
		this.id = id;
		this.nombre = nombre;
		this.digitos = digitos;
	}

//	public NivelOperacion(NivelOperacionVO nivelOperacionVO) {
//		this.id = nivelOperacionVO.getId();
//		this.nombre = nivelOperacionVO.getNombre();
//		this.digitos = nivelOperacionVO.getDigitos();
//		this.setStatus(nivelOperacionVO.getStatus());
//		this.setInicialReg(nivelOperacionVO.getInicialReg());
//		this.setFinalReg(nivelOperacionVO.getFinalReg());
//		this.setTotalRegistros(nivelOperacionVO.getTotalRegistros());
//	}

	public int getDigitos() {
		return digitos;
	}

	public void setDigitos(int digitos) {
		this.digitos = digitos;
	}

}
