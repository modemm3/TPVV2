package com.edu.tpv.busines.builder;

public class TestDTO {

	private int id;
	private String userName;
	private String description;
	public TestDTO() {
		super();
	}
	public TestDTO(int id, String userName, String description) {
		super();
		this.id = id;
		this.userName = userName;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		builder.append("TestDTO [id=");
		builder.append(id);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
}
