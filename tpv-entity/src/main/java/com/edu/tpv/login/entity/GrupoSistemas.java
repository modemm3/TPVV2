package com.edu.tpv.login.entity;


public class GrupoSistemas extends EntityBase {
	private int id;
	private String nombre;
	private String descripcion;
	public GrupoSistemas() {
		super();
	}

	public GrupoSistemas(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

//	public GrupoSistemas(GrupoSistemasVO grupoSistemasVO) {
//		this.id = grupoSistemasVO.getId();
//		this.nombre = grupoSistemasVO.getNombre();
//		this.descripcion = grupoSistemasVO.getDescripcion();
//		this.setStatus(grupoSistemasVO.getStatus());
//		this.setInicialReg(grupoSistemasVO.getInicialReg());
//		this.setFinalReg(grupoSistemasVO.getFinalReg());
//		this.setTotalRegistros(grupoSistemasVO.getTotalRegistros());
//	}
}
