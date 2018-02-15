package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.dto.AgentDto;
import org.arpicoinsurance.groupit.dashboard.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AgentController {

	@Autowired
	private AgentService agentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getagent/{agentCode}")
	public AgentDto getAgent(@PathVariable String agentCode) {
		System.out.println(agentCode);
		try {
			return agentService.getAgent(agentCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
