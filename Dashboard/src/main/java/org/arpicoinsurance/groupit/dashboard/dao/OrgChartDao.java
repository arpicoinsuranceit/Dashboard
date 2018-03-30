package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDetailsDto;

public interface OrgChartDao {

	List<OrgChartDetailsDto> getOrgChartList() throws Exception;
	
}
