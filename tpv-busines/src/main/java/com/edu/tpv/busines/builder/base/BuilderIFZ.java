package com.edu.tpv.busines.builder.base;

public interface BuilderIFZ {
	public Object toDTO();
	public Object toEntity();
	public void setDTO(Object dto);
	public void setEntity(Object entity);
}
