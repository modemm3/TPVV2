package com.edu.tpv.busines.builder;

import com.edu.tpv.busines.builder.base.Build;

public class TestExample {

	public static void main(String[] args) {
		BuilderTest buil= new BuilderTest();
		TestDTO testDTO= new TestDTO();
		TestEntity testEntity= new TestEntity();
		testEntity.setName("test de entity to dto");
		Build b= Build.getInstance();
		buil.setEntity(testEntity);
		testDTO=(TestDTO) b.convertToDTO(buil);
//		testDTO=(TestDTO) buil.toDTO(testEntity);
		System.out.println(testDTO.getUserName());
		System.out.println(testDTO.getId());
	}
}
