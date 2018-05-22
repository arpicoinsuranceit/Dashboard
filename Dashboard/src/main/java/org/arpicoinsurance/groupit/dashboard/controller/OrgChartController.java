package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.service.OrgChartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OrgChartController {

	@Autowired
	private OrgChartDetailsService chartDetailsService;
	
	@RequestMapping(value = "/loadOrg/{userCode}/{userType}/{location}", method = RequestMethod.GET)
	public ResponseEntity<Object>  load(@PathVariable String userCode,@PathVariable String userType,@PathVariable String location) {
	
//		System.out.println(userCode);
//		System.out.println(userType);
//		System.out.println(location);
		
		try {
			return new ResponseEntity<Object>(chartDetailsService.load(userCode,userType,location), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
