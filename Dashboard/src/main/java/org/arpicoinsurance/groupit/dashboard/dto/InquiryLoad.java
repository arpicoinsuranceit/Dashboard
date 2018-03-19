package org.arpicoinsurance.groupit.dashboard.dto;

public class InquiryLoad {

	private String proposalNo;
	private String policyNo;
	private String mainLifeName;
	private String nic;
	private String password;
	
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getMainLifeName() {
		return mainLifeName;
	}
	public void setMainLifeName(String mainLifeName) {
		this.mainLifeName = mainLifeName;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
