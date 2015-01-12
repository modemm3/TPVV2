package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class GrupoSistemasTO extends DTO{

	
	private static final long serialVersionUID = 348423932218766087L;

	public GrupoSistemasTO() {
		super();
	}

	public GrupoSistemasTO(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

//	public GrupoSistemasVO(GrupoSistemas grupoSistemas) {
//		this.id = grupoSistemas.getId();
//		this.nombre = grupoSistemas.getNombre();
//		this.descripcion = grupoSistemas.getDescripcion();
//		this.setStatus(grupoSistemas.getStatus());
//		this.setInicialReg(grupoSistemas.getInicialReg());
//		this.setFinalReg(grupoSistemas.getFinalReg());
//		this.setTotalRegistros(grupoSistemas.getTotalRegistros());
//	}

}
