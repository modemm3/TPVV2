package com.edu.tpv.busines.builder;

import com.edu.tpv.login.entity.EntityBase;

public class TestEntity  extends EntityBase{
	private int id;
	private String name;
	private String description;
	public TestEntity(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public TestEntity() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestEntity [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	

}
