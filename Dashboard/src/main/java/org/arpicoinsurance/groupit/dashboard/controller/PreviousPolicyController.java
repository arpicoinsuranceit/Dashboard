package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.service.PreviousPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*")
public class PreviousPolicyController {
	
	@Autowired
	private PreviousPolicyService previousPolicyService;
	
	@RequestMapping(value = "/previousSumAtRisk")
	public HashMap< String , Object> getPreviousPolicies (@RequestBody String nic) {
		
		System.out.println(nic);
		
		HashMap< String , Object> details = previousPolicyService.getSumAtRisk(nic);
		
		return details;
		
	}
	

}
