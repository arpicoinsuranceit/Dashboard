package org.arpicoinsurance.groupit.dashboard.dto;

public class DevPolicies {
	private String branch;
	private String polNum;
	private String custName;
	private Double premium;
	private Integer arias;
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPolNum() {
		return polNum;
	}
	public void setPolNum(String polNum) {
		this.polNum = polNum;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Double getPremium() {
		return premium;
	}
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	public Integer getArias() {
		return arias;
	}
	public void setArias(Integer arias) {
		this.arias = arias;
	}
}
