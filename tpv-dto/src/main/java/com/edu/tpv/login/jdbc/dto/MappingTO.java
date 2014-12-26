package com.edu.tpv.login.jdbc.dto;


public class MappingTO extends DTO
{
	private int Id;
	private String name;
	private String description; 
	private GroupSystemsTO groupSystems;
	public MappingTO() {
		super();
	}
	
	public MappingTO(int id, String name, String description) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
	}

	public MappingTO(int id, String name, String description,
			GroupSystemsTO groupSystems) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.groupSystems = groupSystems;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public GroupSystemsTO getGroupSystems() {
		return groupSystems;
	}

	public void setGroupSystems(GroupSystemsTO groupSystems) {
		this.groupSystems = groupSystems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MappingTO [Id=").append(Id).append(", name=")
				.append(name).append(", description=").append(description)
				.append(", groupSystems=").append(groupSystems).append("]");
		return builder.toString();
	}

	
}
