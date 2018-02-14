package org.arpicoinsurance.groupit.dashboard.dto;

public class MonthlyTarget {
	private Integer actual = 0;
	private Integer target;
	private Integer targetExpand;
	
	public Integer getActual() {
		return actual;
	}
	public void setActual(Integer actual) {
		this.actual = actual;
	}
	public Integer getTarget() {
		return target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	public Integer getTargetExpand() {
		return targetExpand;
	}
	public void setTargetExpand(Integer targetExpand) {
		this.targetExpand = targetExpand;
	}
	
}
