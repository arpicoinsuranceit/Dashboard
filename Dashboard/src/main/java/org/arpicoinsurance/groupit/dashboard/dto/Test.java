package org.arpicoinsurance.groupit.dashboard.dto;

import java.util.List;

public class Test {

	private String id;
	private String name;
	private String Location;
	private String designation;
	private List<Object> childs;
	
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
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public List<Object> getChilds() {
		return childs;
	}
	public void setChilds(List<Object> childs) {
		this.childs = childs;
	}
	@Override
	public String toString() {
		return "Test [name=" + name + ", Location=" + Location + ", designation=" + designation + ", childs=" + childs
				+ "]";
	} 
}
