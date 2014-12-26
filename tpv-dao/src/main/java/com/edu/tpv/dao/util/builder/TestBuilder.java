package com.edu.tpv.dao.util.builder;

public class TestBuilder {

	public static void main(String[] args) {
		UserBuilder userBuilder= new UserBuilder();
		UserDTO userDTO= new  UserDTO();
		userDTO.setId(2);
		userDTO.setName("mi perita");
		UserEntity userEntity= userBuilder.dtoToEntity(userDTO);
		System.out.println(userEntity.getName());
	}
}
