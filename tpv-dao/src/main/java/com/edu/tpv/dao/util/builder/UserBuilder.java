package com.edu.tpv.dao.util.builder;

public class UserBuilder extends BuilderIfz<UserEntity,UserDTO>{
	
	public UserDTO entityToDTO(UserEntity value) {
		UserDTO dto=new UserDTO();
		dto.setId(value.getId());
		dto.setName(value.getName());
		return dto;
	}

	public UserEntity dtoToEntity(UserDTO value) {
		UserEntity userEntity= new UserEntity();
		userEntity.setId(value.getId());
		userEntity.setName(value.getName());
		return userEntity;
	}

}
