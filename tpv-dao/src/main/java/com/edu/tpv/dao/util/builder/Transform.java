package com.edu.tpv.dao.util.builder;

public class Transform <E,D> {
	private D dto;
	private E entity;
	public E dtoToEntity(BuilderIfz <E,D> builder){
		return builder.dtoToEntity(dto);
	}
	public D  entityToDTO(BuilderIfz <E,D>builder){
		return builder.entityToDTO(entity);
	}
}