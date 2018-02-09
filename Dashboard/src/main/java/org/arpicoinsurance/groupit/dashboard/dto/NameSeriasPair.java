package org.arpicoinsurance.groupit.dashboard.dto;

import java.util.ArrayList;

public class NameSeriasPair {

	private String name;
	private ArrayList<NameValuePair> series;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<NameValuePair> getSeries() {
		return series;
	}
	public void setSeries(ArrayList<NameValuePair> series) {
		this.series = series;
	}
}
