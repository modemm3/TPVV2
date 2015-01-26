package com.edu.tpv.busines.builder.base;

import com.edu.tpv.login.entity.EntityBase;

public class BuilderImpl <E,V> {
	BuilderIFZ build;
	Build builder;
	E entity;
	V dto;

	public BuilderImpl(Class<?> _class,Object o) throws Exception {
		Class<?> c=Class.forName(_class.getName());
		build=(BuilderIFZ) c.newInstance();
		builder=Build.getInstance();
		if(o instanceof EntityBase){
			build.setEntity(o);
			dto=(V) builder.convertToDTO(build);
		}
		else{
			build.setDTO(o);
			entity=(E) builder.convertToEntity(build);
		}
	}
	public E getEntity(){
		return entity;
	}
	public V getDTO(){
		return dto;
	}
}
