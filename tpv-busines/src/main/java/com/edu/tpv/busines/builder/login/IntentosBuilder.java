package com.edu.tpv.busines.builder.login;

import com.edu.tpv.busines.builder.base.BuilderIFZ;
import com.edu.tpv.login.entity.AsigPermisos;
import com.edu.tpv.login.to.AsigPermisosVO;

public class IntentosBuilder implements BuilderIFZ{

	Object dto;
	Object entity;
	public void setDTO(Object dto){
		this.dto=dto;;
	}
	public void setEntity(Object entity){
		this.entity=entity;
	}
	@Override
	public Object toDTO() {
		AsigPermisosVO dto=new AsigPermisosVO();
		AsigPermisos entity=(AsigPermisos) this.entity;
		dto.setId(entity.getId());
//		dto.setUserName(entity.getName());
//		dto.setDescription(entity.getDescription());
		return dto;
	}

	@Override
	public Object toEntity() {
		return null;
	}
	
	
}
