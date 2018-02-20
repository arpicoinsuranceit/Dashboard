package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;

public interface PolicyDataDao {
	
	List<DuePolicies> getDuePolicies(DashboardPara dashboardPara) throws Exception;
	
	Integer getCurrentMonthNOP(DashboardPara dashboardPara) throws Exception;
	
	List<PendingPolicies> getPendingPolicies(DashboardPara dashboardPara) throws Exception;
	
}
