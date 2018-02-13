package org.arpicoinsurance.groupit.dashboard.dto;

public class AgentAchievement {

	private Double trgamt;
	private Double trgach;
	private Integer month;
	private Integer year;
	
	public AgentAchievement() {
		super();
	}
	
	public Double getTrgamt() {
		return trgamt;
	}

	public void setTrgamt(Double trgamt) {
		this.trgamt = trgamt;
	}

	public Double getTrgach() {
		return trgach;
	}

	public void setTrgach(Double trgach) {
		this.trgach = trgach;
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
