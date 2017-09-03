package com.ptmind.datadeck.model.datasource;

public class Field {

	String id;
	String name;
	String description;
	String dataType;
	
	public Field(String id, String name, String description, String dataType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dataType = dataType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
