package org.arpicoinsurance.groupit.dashboard.service;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDto;

public interface OrgChartDetailsService {
	
	List<OrgChartDto> load(String userCode, String userType, String location) throws Exception;

}
