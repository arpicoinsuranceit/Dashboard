package org.arpicoinsurance.groupit.dashboard.dto;

public class PolicyDispatchAcknowDto {

	private PolicyDispatch dispatch;
	private HelthCare care;
	
	public PolicyDispatch getDispatch() {
		return dispatch;
	}
	public void setDispatch(PolicyDispatch dispatch) {
		this.dispatch = dispatch;
	}
	public HelthCare getCare() {
		return care;
	}
	public void setCare(HelthCare care) {
		this.care = care;
	}
	@Override
	public String toString() {
		return "PolicyDispatchAcknowDto [dispatch=" + dispatch + ", care=" + care + "]";
	}
	
	
}
