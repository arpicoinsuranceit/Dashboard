package org.arpicoinsurance.groupit.dashboard.helper;

import java.util.TreeMap;

public class NOP {
	private Integer allPolicyCount;
	private TreeMap<Integer, Double> policiesCurYear;
	
	public Integer getAllPolicyCount() {
		return allPolicyCount;
	}
	public void setAllPolicyCount(Integer allPolicyCount) {
		this.allPolicyCount = allPolicyCount;
	}
	public TreeMap<Integer, Double> getPolicies() {
		return policiesCurYear;
	}
	public void setPolicies(TreeMap<Integer, Double> policies) {
		this.policiesCurYear = policies;
	}
	
	
}
