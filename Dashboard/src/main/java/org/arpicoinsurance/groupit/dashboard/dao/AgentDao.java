package org.arpicoinsurance.groupit.dashboard.dao;

import org.arpicoinsurance.groupit.dashboard.dto.AgentDto;

public interface AgentDao {

	AgentDto getAgent(String agentCode) throws Exception;

}
