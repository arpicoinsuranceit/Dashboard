package org.arpicoinsurance.groupit.dashboard.dto;

public class DataTableResponse {
	
	private Object[] data;
	private Integer draw;
	private Integer recordsFiltered;
	private Integer recordsTotal;

	public Object[] getData() {
		return data;
	}
	public void setData(Object[] data) {
		this.data = data;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
	
}
