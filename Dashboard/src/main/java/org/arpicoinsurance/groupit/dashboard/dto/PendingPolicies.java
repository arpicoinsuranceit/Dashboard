package org.arpicoinsurance.groupit.dashboard.dto;

public class PendingPolicies {
	private String branch;
	private Integer proposalNo;
	private Integer agentCode;
	private String custName;
	private Double premium;
	private String requirment;
	
	public PendingPolicies() {
		super();
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Integer getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(Integer proposalNo) {
		this.proposalNo = proposalNo;
	}

	public Integer getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(Integer agentCode) {
		this.agentCode = agentCode;
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

	public String getRequirment() {
		return requirment;
	}

	public void setRequirment(String requirment) {
		this.requirment = requirment;
	}
	
}
