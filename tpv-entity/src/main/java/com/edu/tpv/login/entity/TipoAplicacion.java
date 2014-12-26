package com.edu.tpv.login.entity;


public class TipoAplicacion extends EntityBase {

	public TipoAplicacion() {
		super();
	}

//	public TipoAplicacion(TipoAplicacionVO tipoAplicacionVO) {
//		this.id = tipoAplicacionVO.getId();
//		this.nombre = tipoAplicacionVO.getNombre();
//		this.descripcion = tipoAplicacionVO.getDescripcion();
//		this.setStatus(tipoAplicacionVO.getStatus());
//		this.setInicialReg(tipoAplicacionVO.getInicialReg());
//		this.setFinalReg(tipoAplicacionVO.getFinalReg());
//		this.setTotalRegistros(tipoAplicacionVO.getTotalRegistros());
//	}

	public TipoAplicacion(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

}
