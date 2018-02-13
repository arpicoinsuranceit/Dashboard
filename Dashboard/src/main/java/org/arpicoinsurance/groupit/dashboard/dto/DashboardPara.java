package org.arpicoinsurance.groupit.dashboard.dto;

public class DashboardPara {
	
	private Integer dashyear;
	private String dashtype;
	private String dashpara;
	
	public DashboardPara() {
		super();
	}

	public String getDashtype() {
		return dashtype;
	}

	public void setDashtype(String dashtype) {
		this.dashtype = dashtype;
	}

	public String getDashpara() {
		return dashpara;
	}

	public void setDashpara(String dashpara) {
		this.dashpara = dashpara;
	}

	public Integer getDashyear() {
		return dashyear;
	}

	public void setDashyear(Integer dashyear) {
		this.dashyear = dashyear;
	}

	
}
