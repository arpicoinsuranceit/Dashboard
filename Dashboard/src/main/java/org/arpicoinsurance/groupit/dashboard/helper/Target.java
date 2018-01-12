package org.arpicoinsurance.groupit.dashboard.helper;

import java.util.TreeMap;

public class Target {
	private Double monthlyTotalTarget;
	private Double completedMonthlyTarget;
	private TreeMap<Integer, Double> calenderYearTarget;
	private TreeMap<Integer, Double> calenderYearActualTarget;
	public Double getMonthlyTotalTarget() {
		return monthlyTotalTarget;
	}
	public void setMonthlyTotalTarget(Double monthlyTotalTarget) {
		this.monthlyTotalTarget = monthlyTotalTarget;
	}
	public Double getCompletedMonthlyTarget() {
		return completedMonthlyTarget;
	}
	public void setCompletedMonthlyTarget(Double completedMonthlyTarget) {
		this.completedMonthlyTarget = completedMonthlyTarget;
	}
	public TreeMap<Integer, Double> getCalenderYearTarget() {
		return calenderYearTarget;
	}
	public void setCalenderYearTarget(TreeMap<Integer, Double> calenderYearTarget) {
		this.calenderYearTarget = calenderYearTarget;
	}
	public TreeMap<Integer, Double> getCalenderYearActualTarget() {
		return calenderYearActualTarget;
	}
	public void setCalenderYearActualTarget(TreeMap<Integer, Double> calenderYearActualTarget) {
		this.calenderYearActualTarget = calenderYearActualTarget;
	}
	
	
}
