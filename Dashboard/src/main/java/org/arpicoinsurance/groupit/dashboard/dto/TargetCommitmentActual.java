package org.arpicoinsurance.groupit.dashboard.dto;

public class TargetCommitmentActual {

	private Double target;
	private Double commitment;
	private Double actual;
	private Integer month;
	private Integer year;
	
	public TargetCommitmentActual() {
		super();
	}

	public Double getTarget() {
		return target;
	}

	public void setTarget(Double target) {
		this.target = target;
	}

	public Double getCommitment() {
		return commitment;
	}

	public void setCommitment(Double commitment) {
		this.commitment = commitment;
	}

	public Double getActual() {
		return actual;
	}

	public void setActual(Double actual) {
		this.actual = actual;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
}
