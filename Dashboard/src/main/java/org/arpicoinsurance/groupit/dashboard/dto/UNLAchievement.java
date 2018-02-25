package org.arpicoinsurance.groupit.dashboard.dto;

public class UNLAchievement {
	
	private Double trgamt;
	private Double trgach;
	private Double trgtcfa;
	private Integer month;
	private Integer year;
	
	public UNLAchievement() {
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

	public Double getTrgtcfa() {
		return trgtcfa;
	}

	public void setTrgtcfa(Double trgtcfa) {
		this.trgtcfa = trgtcfa;
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
