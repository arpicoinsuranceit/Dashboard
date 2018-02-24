package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.dto.MonthlyTarget;
import org.arpicoinsurance.groupit.dashboard.dto.NameSeriasPair;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.Top3;
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
	
	@RequestMapping(value = "/getCurrentMonthTarget/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTarget(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getCurrentMonthTargetGWP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetGWP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getCurrentMonthTargetMCFP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetMCFP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getCurrentMonthTargetFYP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetFYP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getCurrentMonthTargetNOP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetNOP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getCurrentMonthYearlyTarget/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<Object> getCurrentMonthYearlyTarget(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		//System.out.println(userid+" - "+dashpara+" - "+usertype);
		List<Object> currentMonthYearlyTarget = null;
		try {
			currentMonthYearlyTarget = dashboardService.getCurrentMonthYearlyTarget(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentMonthYearlyTarget;
	}
	
	@RequestMapping(value = "/getCurrentMonthYearlyTargetUNL/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<Object> getCurrentMonthYearlyTargetUNL(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		//System.out.println(userid+" - "+dashpara+" - "+usertype);
		List<Object> currentMonthYearlyTarget = null;
		try {
			currentMonthYearlyTarget = dashboardService.getCurrentMonthYearlyTargetUNL(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentMonthYearlyTarget;
	}
	
	@RequestMapping(value = "/getPolicySummery/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getPolicySummery(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getTopIC", method = RequestMethod.GET)
	public List<Top3> getTopIC() {
		List<Top3> top3IC = null;
		try {
			top3IC = dashboardService.getTopIC();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top3IC;
	}
	
	@RequestMapping(value = "/getTopIS", method = RequestMethod.GET)
	public List<Top3> getTopIS() {
		List<Top3> top3IS = null;
		try {
			top3IS = dashboardService.getTopIS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top3IS;
	}
	
	@RequestMapping(value = "/getTopUL", method = RequestMethod.GET)
	public List<Top3> getTopUl() {
		List<Top3> top3UL = null;
		try {
			top3UL = dashboardService.getTopUL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top3UL;
	}
	
	@RequestMapping(value = "/getTopBranch", method = RequestMethod.GET)
	public List<Top3> getTopBranch() {
		
		return null;
	}
	
	@RequestMapping(value = "/getTopRegion", method = RequestMethod.GET)
	public List<Top3> getTopRegion() {
		
		return null;
	}
	
	@RequestMapping(value = "/getTopZone", method = RequestMethod.GET)
	public List<Top3> getTopZone() {
		
		return null;
	}
	
	@RequestMapping(value = "/getGWP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getGWP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getGWPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getGWPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getMCFP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getMCFP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getMCFPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getMCFPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getFYP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getFYP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getFYPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getFYPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getNOP/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getNOP(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getNOPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getNOPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getRINY/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getRINY(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getCF/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getCF(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	@RequestMapping(value = "/getCFC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getCFC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		
		return null;
	}
	
	
	@RequestMapping(value = "/getDuePolicies/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<DuePolicies> getDuePolicies(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<DuePolicies> duePolicies = null;
		try {
			duePolicies = dashboardService.getDuePolicies(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return duePolicies;
	}
	
	
	@RequestMapping(value = "/getPendingPolicies/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<PendingPolicies> getPendingPolicies(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<PendingPolicies> pendingPolicies = null;
		try {
			pendingPolicies = dashboardService.getPendingPolicies(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pendingPolicies;
	}
	
	
	
	
	

}
