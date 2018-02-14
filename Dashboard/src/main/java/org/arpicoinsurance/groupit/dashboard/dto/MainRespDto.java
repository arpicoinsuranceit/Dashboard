package org.arpicoinsurance.groupit.dashboard.dto;

import java.util.ArrayList;

public class MainRespDto {
	private MonthlyTarget monthlyTarget = new MonthlyTarget(); // dashbard ic
	private ArrayList<NameSeriasPair> yearlyTarget;// dashbard ic
	private ArrayList<NameSeriasPair> YearlyTargetCF;// dashbard ic
	
	private MonthlyTarget monthlyTargetGWP;// dashbard ul
	private MonthlyTarget monthlyTargetMCFP;// dashbard ul
	private MonthlyTarget monthlyTargetFYP;// dashbard ul
	private MonthlyTarget monthlyTargetNOP;// dashbard ul
	
	
	private ArrayList<NameSeriasPair> extra; // still not decided what data will put this array..... Dashboard IC 
	
	
	private ArrayList<NameSeriasPair> nop; // dashbard ul
	private ArrayList<NameSeriasPair> mcfp; // dashbard ul
	private ArrayList<NameSeriasPair> fyp; // dashbard ul
	private ArrayList<NameSeriasPair> gwp; // dashbard ul
	private ArrayList<NameSeriasPair> riny1; // dashbard ul

	private ArrayList<NameSeriasPair> nopC; // dashbard ul
	private ArrayList<NameSeriasPair> mcfpC; // dashbard ul
	private ArrayList<NameSeriasPair> fypC; // dashbard ul
	private ArrayList<NameSeriasPair> gwpC; // dashbard ul
	
	private ArrayList<Top3> ic; //  all
	private ArrayList<Top3> ul; //  all
	private ArrayList<Top3> branch; //  all
	private ArrayList<Top3> region; //  all
	private ArrayList<Top3> zone; //  all
	
	private ArrayList<NameValuePair> policySummery; 
	
	private ArrayList<PendingPol> pendingPolList; //   all
	private ArrayList<DevPolicies> devPolicieList; //   all
	
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

	public ArrayList<NameSeriasPair> getNopC() {
		return nopC;
	}

	public void setNopC(ArrayList<NameSeriasPair> nopC) {
		this.nopC = nopC;
	}

	public ArrayList<NameSeriasPair> getMcfpC() {
		return mcfpC;
	}

	public void setMcfpC(ArrayList<NameSeriasPair> mcfpC) {
		this.mcfpC = mcfpC;
	}

	public ArrayList<NameSeriasPair> getFypC() {
		return fypC;
	}

	public void setFypC(ArrayList<NameSeriasPair> fypC) {
		this.fypC = fypC;
	}

	public ArrayList<NameSeriasPair> getGwpC() {
		return gwpC;
	}

	public void setGwpC(ArrayList<NameSeriasPair> gwpC) {
		this.gwpC = gwpC;
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

	public ArrayList<NameValuePair> getPolicySummery() {
		return policySummery;
	}

	public void setPolicySummery(ArrayList<NameValuePair> policySummery) {
		this.policySummery = policySummery;
	}

	public ArrayList<NameSeriasPair> getYearlyTargetCF() {
		return YearlyTargetCF;
	}

	public void setYearlyTargetCF(ArrayList<NameSeriasPair> yearlyTargetCF) {
		YearlyTargetCF = yearlyTargetCF;
	}

	public ArrayList<NameSeriasPair> getExtra() {
		return extra;
	}

	public void setExtra(ArrayList<NameSeriasPair> extra) {
		this.extra = extra;
	}

	public MonthlyTarget getMonthlyTargetGWP() {
		return monthlyTargetGWP;
	}

	public void setMonthlyTargetGWP(MonthlyTarget monthlyTargetGWP) {
		this.monthlyTargetGWP = monthlyTargetGWP;
	}

	public MonthlyTarget getMonthlyTargetMCFP() {
		return monthlyTargetMCFP;
	}

	public void setMonthlyTargetMCFP(MonthlyTarget monthlyTargetMCFP) {
		this.monthlyTargetMCFP = monthlyTargetMCFP;
	}

	public MonthlyTarget getMonthlyTargetFYP() {
		return monthlyTargetFYP;
	}

	public void setMonthlyTargetFYP(MonthlyTarget monthlyTargetFYP) {
		this.monthlyTargetFYP = monthlyTargetFYP;
	}

	public MonthlyTarget getMonthlyTargetNOP() {
		return monthlyTargetNOP;
	}

	public void setMonthlyTargetNOP(MonthlyTarget monthlyTargetNOP) {
		this.monthlyTargetNOP = monthlyTargetNOP;
	}

}
