package com.edu.tpv.busines.builder.base;

public class Build {
	static Build build;
	
	private Build() {
		super();
	}
	public static synchronized Build getInstance(){
		if(build==null)
			build= new Build();
		
		return build;
	}
	public Object convertToDTO(BuilderIFZ entity){
		return entity.toDTO();
	}
	public Object convertToEntity(BuilderIFZ dto){
		return dto.toDTO();
	}
	
}
