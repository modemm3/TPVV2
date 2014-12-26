package com.edu.tpv.login.entity;


public class PreguntasSecretas extends EntityBase {

	public PreguntasSecretas() {
		super();
	}

	public PreguntasSecretas(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

//	public PreguntasSecretas(PreguntasSecretasVO preguntasSecretasVO) {
//		this.id = preguntasSecretasVO.getId();
//		this.nombre = preguntasSecretasVO.getNombre();
//		this.setStatus(preguntasSecretasVO.getStatus());
//		this.setInicialReg(preguntasSecretasVO.getInicialReg());
//		this.setFinalReg(preguntasSecretasVO.getFinalReg());
//		this.setTotalRegistros(preguntasSecretasVO.getTotalRegistros());
//	}

}
