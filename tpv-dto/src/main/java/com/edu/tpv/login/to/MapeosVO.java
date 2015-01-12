package com.edu.tpv.login.to;

import com.edu.tpv.login.jdbc.dto.DTO;

public class MapeosVO extends DTO{
	
	private GrupoSistemasVO grupoSistemasVO;
	private boolean activo;

	public MapeosVO() {
		super();
	}

	public MapeosVO(int id, String nombre, GrupoSistemasVO grupoSistemasVO, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.grupoSistemasVO = grupoSistemasVO;
		this.activo = activo;
	}

//	public MapeosVO(Mapeos mapeos) {
//		this.id = mapeos.getId();
//		this.nombre = mapeos.getNombre();
//		GrupoSistemasVO gpoSistem = new GrupoSistemasVO(mapeos.getGrupoSistemas() == null ? new GrupoSistemas() : mapeos.getGrupoSistemas());
//		this.setGrupoSistemasVO(gpoSistem);
//		this.activo = mapeos.isActivo();
//		this.setStatus(mapeos.getStatus());
//		this.setInicialReg(mapeos.getInicialReg());
//		this.setFinalReg(mapeos.getFinalReg());
//		this.setTotalRegistros(mapeos.getTotalRegistros());
//	}

	public GrupoSistemasVO getGrupoSistemasVO() {
		return grupoSistemasVO;
	}

	public void setGrupoSistemasVO(GrupoSistemasVO grupoSistemasVO) {
		this.grupoSistemasVO = grupoSistemasVO;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = Boolean.parseBoolean(activo);
		if (activo.equals("on"))
			this.activo = true;
	}
}
