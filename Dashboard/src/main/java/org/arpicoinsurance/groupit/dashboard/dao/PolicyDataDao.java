package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.InquiryLoad;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.PolicySummary;

public interface PolicyDataDao {
	
	List<DuePolicies> getDuePolicies(DashboardPara dashboardPara) throws Exception;
	
	Integer getCurrentMonthNOP(DashboardPara dashboardPara) throws Exception;
	
	List<PendingPolicies> getPendingPolicies(DashboardPara dashboardPara) throws Exception;
	
	PolicySummary getPolicySummary(DashboardPara dashboardPara) throws Exception;
	
	List<InquiryLoad> getPolicyListByAdvCod(String advCod) throws Exception;
	
}
