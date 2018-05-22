package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.service.PreviousPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*")
public class PreviousPolicyController {
	
	@Autowired
	private PreviousPolicyService previousPolicyService;
	
	@RequestMapping(value = "/previousSumAtRisk")
	public ResponseEntity<Object> getPreviousPolicies (@RequestBody String nic) {
		
//		System.out.println(nic);
		
		if(nic.length()> 10) {
			nic = nic.substring(2);
		}
		HashMap<String, Object> details;
		try {
			details = previousPolicyService.getSumAtRisk(nic);
			return new ResponseEntity<Object>(details, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	

}
