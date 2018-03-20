package org.arpicoinsurance.groupit.dashboard.report.service;


public interface JasperReportService {
	public byte[]  mcfpReport(String fromDate,String toDate,String advisor,String branch)throws Exception;
}
