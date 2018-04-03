package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDto;
import org.arpicoinsurance.groupit.dashboard.service.OrgChartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OrgChartController {

	@Autowired
	private OrgChartDetailsService chartDetailsService;
	
	@RequestMapping(value = "/loadOrg", method = RequestMethod.GET)
	public List<OrgChartDto> load() {
	
		try {
			return chartDetailsService.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
