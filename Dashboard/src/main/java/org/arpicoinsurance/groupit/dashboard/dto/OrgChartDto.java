package org.arpicoinsurance.groupit.dashboard.dto;

import java.util.ArrayList;
import java.util.List;

public class OrgChartDto {

	private String id;
	private String name;
	private String Location;
	private String designation;
	private String desCod;
	private List<OrgChartDto> childs = new ArrayList<>();
	
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
	public String getDesCod() {
		return desCod;
	}
	public void setDesCod(String desCod) {
		this.desCod = desCod;
	}
	public List<OrgChartDto> getChilds() {
		return childs;
	}
	public void setChilds(List<OrgChartDto> childs) {
		this.childs = childs;
	}
	@Override
	public String toString() {
		return "OrgChartDto [name=" + name + ", Location=" + Location + ", designation=" + designation + ", childs=" + childs
				+ "]";
	} 
}
