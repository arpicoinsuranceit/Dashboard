package org.arpicoinsurance.groupit.dashboard.service;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;

public interface DashboardService {
	MainRespDto getDashboard(Integer id) throws Exception;
	
	DashboardPara getDashboardPara(String userid) throws Exception;
}
