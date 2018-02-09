package org.arpicoinsurance.groupit.dashboard.dto;

import java.util.ArrayList;

public class MainRespDto {
	private MonthlyTarget monthlyTarget;
	private ArrayList<NameSeriasPair> yearlyTarget;
	private ArrayList<NameSeriasPair> nop;
	private ArrayList<NameSeriasPair> mcfp;
	private ArrayList<NameSeriasPair> fyp;
	private ArrayList<NameSeriasPair> gwp;
	private ArrayList<NameSeriasPair> riny1;

	private ArrayList<Top3> ic;
	private ArrayList<Top3> ul;
	private ArrayList<Top3> branch;
	private ArrayList<Top3> region;
	private ArrayList<Top3> zone;
	
	private ArrayList<PendingPol> pendingPolList;
	private ArrayList<DevPolicies> devPolicieList;
	
	public MonthlyTarget getMonthlyTarget() {
		return monthlyTarget;
	}

	public void setMonthlyTarget(MonthlyTarget monthlyTarget) {
		this.monthlyTarget = monthlyTarget;
	}

	public ArrayList<NameSeriasPair> getYearlyTarget() {
		return yearlyTarget;
	}

	public void setYearlyTarget(ArrayList<NameSeriasPair> yearlyTarget) {
		this.yearlyTarget = yearlyTarget;
	}

	public ArrayList<NameSeriasPair> getNop() {
		return nop;
	}

	public void setNop(ArrayList<NameSeriasPair> nop) {
		this.nop = nop;
	}

	public ArrayList<NameSeriasPair> getMcfp() {
		return mcfp;
	}

	public void setMcfp(ArrayList<NameSeriasPair> mcfp) {
		this.mcfp = mcfp;
	}

	public ArrayList<NameSeriasPair> getFyp() {
		return fyp;
	}

	public void setFyp(ArrayList<NameSeriasPair> fyp) {
		this.fyp = fyp;
	}

	public ArrayList<NameSeriasPair> getGwp() {
		return gwp;
	}

	public void setGwp(ArrayList<NameSeriasPair> gwp) {
		this.gwp = gwp;
	}

	public ArrayList<NameSeriasPair> getRiny1() {
		return riny1;
	}

	public void setRiny1(ArrayList<NameSeriasPair> riny1) {
		this.riny1 = riny1;
	}

	public ArrayList<Top3> getIc() {
		return ic;
	}

	public void setIc(ArrayList<Top3> ic) {
		this.ic = ic;
	}

	public ArrayList<Top3> getUl() {
		return ul;
	}

	public void setUl(ArrayList<Top3> ul) {
		this.ul = ul;
	}

	public ArrayList<Top3> getBranch() {
		return branch;
	}

	public void setBranch(ArrayList<Top3> branch) {
		this.branch = branch;
	}

	public ArrayList<Top3> getRegion() {
		return region;
	}

	public void setRegion(ArrayList<Top3> region) {
		this.region = region;
	}

	public ArrayList<Top3> getZone() {
		return zone;
	}

	public void setZone(ArrayList<Top3> zone) {
		this.zone = zone;
	}

	public ArrayList<PendingPol> getPendingPolList() {
		return pendingPolList;
	}

	public void setPendingPolList(ArrayList<PendingPol> pendingPolList) {
		this.pendingPolList = pendingPolList;
	}

	public ArrayList<DevPolicies> getDevPolicieList() {
		return devPolicieList;
	}

	public void setDevPolicieList(ArrayList<DevPolicies> devPolicieList) {
		this.devPolicieList = devPolicieList;
	}

}
