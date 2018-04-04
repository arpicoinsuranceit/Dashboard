package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDto;
import org.arpicoinsurance.groupit.dashboard.service.OrgChartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<OrgChartDto> load(@PathVariable String userCode,@PathVariable String userType,@PathVariable String location) {
	
		System.out.println(userCode);
		System.out.println(userType);
		System.out.println(location);
		
		try {
			return chartDetailsService.load(userCode,userType,location);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
