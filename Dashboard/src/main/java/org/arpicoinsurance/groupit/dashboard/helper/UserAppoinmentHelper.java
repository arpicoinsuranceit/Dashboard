package org.arpicoinsurance.groupit.dashboard.helper;

import java.util.Date;

public class UserAppoinmentHelper {

	private String shortName;
	private String address1;
	private String address2;
	private String agtCod;
	private String agentName;
	private String middleName;
	private String lastName;
	private String agentNic;
	private String agentTitle;
	private Double agentAllowance;

	private Date appoinmentDate;
	private String desCategory;
	private String designation;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAgtCod() {
		return agtCod;
	}

	public void setAgtCod(String agtCod) {
		this.agtCod = agtCod;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAgentNic() {
		return agentNic;
	}

	public void setAgentNic(String agentNic) {
		this.agentNic = agentNic;
	}

	public Date getAppoinmentDate() {
		return appoinmentDate;
	}

	public void setAppoinmentDate(Date appoinmentDate) {
		this.appoinmentDate = appoinmentDate;
	}

	public String getDesCategory() {
		return desCategory;
	}

	public void setDesCategory(String desCategory) {
		this.desCategory = desCategory;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAgentTitle() {
		return agentTitle;
	}

	public void setAgentTitle(String agentTitle) {
		this.agentTitle = agentTitle;
	}

	public Double getAgentAllowance() {
		return agentAllowance;
	}

	public void setAgentAllowance(Double agentAllowance) {
		this.agentAllowance = agentAllowance;
	}

	@Override
	public String toString() {
		return "UserAppoinmentHelper [shortName=" + shortName + ", address1=" + address1 + ", address2=" + address2
				+ ", agtCod=" + agtCod + ", agentName=" + agentName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", agentNic=" + agentNic + ", agentTitle=" + agentTitle + ", agentAllowance="
				+ agentAllowance + ", appoinmentDate=" + appoinmentDate + ", desCategory=" + desCategory
				+ ", designation=" + designation + "]";
	}

}
