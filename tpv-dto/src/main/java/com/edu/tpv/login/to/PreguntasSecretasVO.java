package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class PreguntasSecretasVO extends DTO{


	private static final long serialVersionUID = -125188377079012086L;

	public PreguntasSecretasVO() {
		super();
	}

	public PreguntasSecretasVO(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

//	public PreguntasSecretasVO(PreguntasSecretas preguntasSecretas) {
//		this.id = preguntasSecretas.getId();
//		this.nombre = preguntasSecretas.getNombre();
//		this.setStatus(preguntasSecretas.getStatus());
//		this.setInicialReg(preguntasSecretas.getInicialReg());
//		this.setFinalReg(preguntasSecretas.getFinalReg());
//		this.setTotalRegistros(preguntasSecretas.getTotalRegistros());
//	}
}
