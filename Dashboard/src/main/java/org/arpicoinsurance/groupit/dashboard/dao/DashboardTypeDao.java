package org.arpicoinsurance.groupit.dashboard.dao;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;

public interface DashboardTypeDao {
	
	DashboardPara getDashboardPara(String userCode) throws Exception;

	String isHo(String userid);
	
}
