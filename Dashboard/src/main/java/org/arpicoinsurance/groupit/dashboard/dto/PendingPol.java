package org.arpicoinsurance.groupit.dashboard.dto;

public class PendingPol {
	private String branch;
	private String propNo;
	private String agentCode;
	private String custName;
	private Double proposal;
	private String req;
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPropNo() {
		return propNo;
	}
	public void setPropNo(String propNo) {
		this.propNo = propNo;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Double getProposal() {
		return proposal;
	}
	public void setProposal(Double proposal) {
		this.proposal = proposal;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
}
