package org.arpicoinsurance.groupit.dashboard.service;

import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;

public interface DashboardService {
	MainRespDto getDashboard(String id) throws Exception;
}
