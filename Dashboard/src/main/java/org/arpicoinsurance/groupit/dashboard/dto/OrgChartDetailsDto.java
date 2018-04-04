package org.arpicoinsurance.groupit.dashboard.dto;

public class OrgChartDetailsDto {
	private String agncod;
	private String shrtnm;
	private String loc_name;
	private String subdes;
	private String subdcd;
	private String agncls;
	private String loc_code;
	private String rgncod;
	private String zoncod;
	private String unlcod;
	private Integer active = 1;
	
	public String getAgncod() {
		return agncod;
	}
	public void setAgncod(String agncod) {
		this.agncod = agncod;
	}
	
	public String getShrtnm() {
		return shrtnm;
	}
	
	public void setShrtnm(String shrtnm) {
		this.shrtnm = shrtnm;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getSubdes() {
		return subdes;
	}
	public void setSubdes(String subdes) {
		this.subdes = subdes;
	}
	public String getSubdcd() {
		return subdcd;
	}
	public void setSubdcd(String subdcd) {
		this.subdcd = subdcd;
	}
	public String getAgncls() {
		return agncls;
	}
	public void setAgncls(String agncls) {
		this.agncls = agncls;
	}
	public String getLoc_code() {
		return loc_code;
	}
	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
	}
	public String getRgncod() {
		return rgncod;
	}
	public void setRgncod(String rgncod) {
		this.rgncod = rgncod;
	}
	public String getZoncod() {
		return zoncod;
	}
	public void setZoncod(String zoncod) {
		this.zoncod = zoncod;
	}
	public String getUnlcod() {
		return unlcod;
	}
	public void setUnlcod(String unlcod) {
		this.unlcod = unlcod;
	}
	
	
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "OrgChartDetailsDto [shrtnm=" + shrtnm + ", loc_name=" + loc_name + ", subdes=" + subdes + ", subdcd="
				+ subdcd + ", agncls=" + agncls + ", loc_code=" + loc_code + ", rgncod=" + rgncod + ", zoncod=" + zoncod
				+ ", unlcod=" + unlcod + "]";
	}
	
}
