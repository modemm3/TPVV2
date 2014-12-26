package com.edu.tpv.login.entity;


public class Mapeos extends EntityBase {
	private int id;
	private String nombre;
	private GrupoSistemas grupoSistemas;
	boolean activo;

	public Mapeos() {
		super();
	}

	public Mapeos(int id, String nombre, GrupoSistemas grupoSistemas, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.grupoSistemas = grupoSistemas;
		this.activo = activo;
	}

//	public Mapeos(MapeosVO mapeosVO) {
//		this.id = mapeosVO.getId();
//		this.nombre = mapeosVO.getNombre();
//		GrupoSistemas gpoSistemas = new GrupoSistemas(mapeosVO.getGrupoSistemasVO() == null ? new GrupoSistemasVO() : mapeosVO.getGrupoSistemasVO());
//		this.setGrupoSistemas(gpoSistemas);
//		this.activo = mapeosVO.isActivo();
//		this.setStatus(mapeosVO.getStatus());
//		this.setInicialReg(mapeosVO.getInicialReg());
//		this.setFinalReg(mapeosVO.getFinalReg());
//		this.setTotalRegistros(mapeosVO.getTotalRegistros());
//	}

	public GrupoSistemas getGrupoSistemas() {
		return grupoSistemas;
	}

	public void setGrupoSistemas(GrupoSistemas grupoSistemas) {
		this.grupoSistemas = grupoSistemas;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
