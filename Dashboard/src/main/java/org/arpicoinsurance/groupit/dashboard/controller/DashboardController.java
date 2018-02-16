package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.ArrayList;
import java.util.TreeMap;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
	public MainRespDto loadDashboardData(@PathVariable String id) {
		MainRespDto mainRespDto = null;
		try {
			mainRespDto = dashboardService.getDashboard(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mainRespDto;
	}
	
	@RequestMapping(value = "/getdashboardpara/{userid}", method = RequestMethod.GET)
	public DashboardPara getDashboardData(@PathVariable String userid) {
		DashboardPara dashboardPara = null;
		try {
			dashboardPara = dashboardService.getDashboardPara(userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dashboardPara;
	}

}
