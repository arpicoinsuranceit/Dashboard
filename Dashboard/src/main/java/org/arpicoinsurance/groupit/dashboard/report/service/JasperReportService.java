package org.arpicoinsurance.groupit.dashboard.report.service;


public interface JasperReportService {
	public byte[]  mcfpReport(String fromDate,String toDate,String advisor,String branch)throws Exception;

	public byte[]  proposalRegister(String fromDate,String toDate,String zone,String region, String branch, String unl, String frequency)throws Exception;

	public byte[]  pendingRequirements(String advisor,String branch,String region,String zone)throws Exception;

	public byte[]  retentionUnit(String toDate,String zone,String region,String branch, String unl)throws Exception;

	public byte[]  retentionCode(String date,String zone,String region,String branch, String code)throws Exception;

	public byte[]  retentionBranch(String date,String zone,String region,String branch)throws Exception;

	public byte[]  detailsOfPolicies(String fromDate,String toDate,String ic,String ul,String branch,String region, String zone,String sp)throws Exception;

	public byte[]  premiumDueReportLive(String asAtDate,String code,String branchCode,String regionCode,String zone)throws Exception;

	public byte[]  premiumDueReport(String asAtDate,String code,String branchCode,String regionCode,String zone)throws Exception;

	public byte[]  grantStmtBranch(String branch,String year,String month,String code,String status)throws Exception;

	public byte[]  firstPremiumLapSummary(String fromDate,String toDate,String zone,String region,String branch)throws Exception;

	public byte[]  salesPerfSummaryCode(String fromDate,String toDate,String zone,String region,String branch,String frequency, String product)throws Exception;

	public byte[]  salesPerfSummary(String fromDate,String toDate,String zone,String region,String branch,String frequency, String product,String so)throws Exception;

	public byte[]  unitIsPerfSummary(String fromDate,String toDate,String zone,String region,String branch,String unl, String type,String frequency,String product)throws Exception;

	public byte[]  salesPerfDetail(String fromDate,String toDate,String code,String zone,String region,String branch,String product, String frequency)throws Exception;

	public byte[]  unitIsPerfDetails(String fromDate,String toDate,String zone,String region,String branch,String unl, String type,String frequency,String product)throws Exception;

	
	
}
