package com.edu.tpv.dao.util.builder;

public abstract class BuilderIfz <E,D> {

	public abstract D entityToDTO(E value);
	public abstract E dtoToEntity(D value);
	
}
