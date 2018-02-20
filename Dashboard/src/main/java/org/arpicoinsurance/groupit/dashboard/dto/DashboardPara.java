package org.arpicoinsurance.groupit.dashboard.dto;

public class DashboardPara {
	
	private Integer dashyear;
	private Integer dashmonth;
	private String dashtype;
	private String dashpara;
	private String usertype;
	
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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Integer getDashmonth() {
		return dashmonth;
	}

	public void setDashmonth(Integer dashmonth) {
		this.dashmonth = dashmonth;
	}
	
}
