package org.arpicoinsurance.groupit.dashboard.service.impl;

import org.arpicoinsurance.groupit.dashboard.dao.AgentDao;
import org.arpicoinsurance.groupit.dashboard.dto.AgentDto;
import org.arpicoinsurance.groupit.dashboard.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService{

	@Autowired
	private AgentDao agentDao;
	
	@Override
	public AgentDto getAgent(String agentCode) throws Exception {
		AgentDto agentDto=agentDao.getAgent(agentCode);
		agentDto.setAgentCode(agentCode);
		
		return agentDto;
	}

}
