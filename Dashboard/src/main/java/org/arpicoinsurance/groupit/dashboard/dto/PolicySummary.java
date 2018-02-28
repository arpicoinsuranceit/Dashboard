package org.arpicoinsurance.groupit.dashboard.dto;

public class PolicySummary {

	private Integer plisuCount;
	private Double plisuAmount;
	private Integer plapsCount;
	private Double plapsAmount;
	private Integer plappCount;
	private Double plappAmount;
	
	public PolicySummary() {
		super();
	}

	public Integer getPlisuCount() {
		return plisuCount;
	}

	public void setPlisuCount(Integer plisuCount) {
		this.plisuCount = plisuCount;
	}

	public Double getPlisuAmount() {
		return plisuAmount;
	}

	public void setPlisuAmount(Double plisuAmount) {
		this.plisuAmount = plisuAmount;
	}

	public Integer getPlapsCount() {
		return plapsCount;
	}

	public void setPlapsCount(Integer plapsCount) {
		this.plapsCount = plapsCount;
	}

	public Double getPlapsAmount() {
		return plapsAmount;
	}

	public void setPlapsAmount(Double plapsAmount) {
		this.plapsAmount = plapsAmount;
	}

	public Integer getPlappCount() {
		return plappCount;
	}

	public void setPlappCount(Integer plappCount) {
		this.plappCount = plappCount;
	}

	public Double getPlappAmount() {
		return plappAmount;
	}

	public void setPlappAmount(Double plappAmount) {
		this.plappAmount = plappAmount;
	}
	
}
