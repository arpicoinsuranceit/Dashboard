package org.arpicoinsurance.groupit.dashboard.service;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDto;

public interface OrgChartDetailsService {
	
	List<OrgChartDto> load() throws Exception;

}
