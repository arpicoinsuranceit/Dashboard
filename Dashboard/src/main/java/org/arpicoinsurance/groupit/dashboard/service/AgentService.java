package org.arpicoinsurance.groupit.dashboard.service;

import org.arpicoinsurance.groupit.dashboard.dto.AgentDto;

public interface AgentService {
	AgentDto getAgent (String agentCode) throws Exception;
}
