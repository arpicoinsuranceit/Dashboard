package org.arpicoinsurance.groupit.dashboard.dto;

public class DuePolicies {
	private String branch;
	private Integer polnum;
	private String pprsta;
	private String custName;
	private String custMobile;
	private Double premium;
	private Integer nofarias;
	private Double totarias;
	
	public DuePolicies() {
		super();
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Integer getPolnum() {
		return polnum;
	}

	public void setPolnum(Integer polnum) {
		this.polnum = polnum;
	}

	public String getPprsta() {
		return pprsta;
	}

	public void setPprsta(String pprsta) {
		this.pprsta = pprsta;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Integer getNofarias() {
		return nofarias;
	}

	public void setNofarias(Integer nofarias) {
		this.nofarias = nofarias;
	}

	public Double getTotarias() {
		return totarias;
	}

	public void setTotarias(Double totarias) {
		this.totarias = totarias;
	}
	
	
}
