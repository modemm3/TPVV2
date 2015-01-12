package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class TipoAplicacionTO extends DTO{

	

	public TipoAplicacionTO() {
		super();
	}

//	public TipoAplicacionVO(TipoAplicacion tipoAplicacion) {
//		this.id = tipoAplicacion.getId();
//		this.nombre = tipoAplicacion.getNombre();
//		this.descripcion = tipoAplicacion.getDescripcion();
//		this.setStatus(tipoAplicacion.getStatus());
//		this.setInicialReg(tipoAplicacion.getInicialReg());
//		this.setFinalReg(tipoAplicacion.getFinalReg());
//		this.setTotalRegistros(tipoAplicacion.getTotalRegistros());
//	}

	public TipoAplicacionTO(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
}
