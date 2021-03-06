package org.arpicoinsurance.groupit.dashboard.dto;

public class AgentAchievement {

	private Double trgamt;
	private Double trgach;
	private Integer month;
	private Integer year;
	
	public AgentAchievement() {
		super();
	}
	
	public AgentAchievement(Double trgamt, Double trgach, Integer month, Integer year) {
		super();
		this.trgamt = trgamt;
		this.trgach = trgach;
		this.month = month;
		this.year = year;
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

	public AgentAchievement(Double trgamt, Double trgach) {
		super();
		this.trgamt = trgamt;
		this.trgach = trgach;
	}
	
}
