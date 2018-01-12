package org.arpicoinsurance.groupit.dashboard.helper;

import java.util.ArrayList;

public class DashboardData {
	private Target target;
	private NOP nop;
	private ArrayList<Top3> unitLeaders;
	private ArrayList<Top3> branch;
	private ArrayList<Top3> region;
	private ArrayList<Top3> zone;
	private ArrayList<CurPolicy> curMonthPolicies;
	private ArrayList<CurProposal> curMonthProposals;
	private ArrayList<CurQuo> curMonthQuos;
	
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	public NOP getNop() {
		return nop;
	}
	public void setNop(NOP nop) {
		this.nop = nop;
	}
	public ArrayList<Top3> getUnitLeaders() {
		return unitLeaders;
	}
	public void setUnitLeaders(ArrayList<Top3> unitLeaders) {
		this.unitLeaders = unitLeaders;
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
	public ArrayList<CurPolicy> getCurMonthPolicies() {
		return curMonthPolicies;
	}
	public void setCurMonthPolicies(ArrayList<CurPolicy> curMonthPolicies) {
		this.curMonthPolicies = curMonthPolicies;
	}
	public ArrayList<CurProposal> getCurMonthProposals() {
		return curMonthProposals;
	}
	public void setCurMonthProposals(ArrayList<CurProposal> curMonthProposals) {
		this.curMonthProposals = curMonthProposals;
	}
	public ArrayList<CurQuo> getCurMonthQuos() {
		return curMonthQuos;
	}
	public void setCurMonthQuos(ArrayList<CurQuo> curMonthQuos) {
		this.curMonthQuos = curMonthQuos;
	}
	
	
}
