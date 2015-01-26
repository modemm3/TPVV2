package com.edu.tpv.busines.builder;

import com.edu.tpv.busines.builder.base.BuilderIFZ;

public class BuilderTest implements BuilderIFZ{

	Object dto;
	Object entity;
	public void setDTO(Object dto){
		this.dto=dto;
	}
	public void setEntity(Object entity){
		this.entity=entity;
	}
	@Override
	public Object toDTO() {
		TestDTO dto=new TestDTO();
		TestEntity entity=(TestEntity) this.entity;
		dto.setId(entity.getId());
		dto.setUserName(entity.getName());
		dto.setDescription(entity.getDescription());
		return dto;
	}

	@Override
	public Object toEntity() {
		return entity;
	}
	
	
}
