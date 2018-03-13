package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.dto.MonthlyTarget;
import org.arpicoinsurance.groupit.dashboard.dto.NameSeriasPair;
import org.arpicoinsurance.groupit.dashboard.dto.NameValuePair;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.Top3;
import org.arpicoinsurance.groupit.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/getdashboardpara", method = RequestMethod.POST)
	public DashboardPara getDashboardData(@RequestBody String userid) {
		//System.out.println(userid);
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
	public List<NameValuePair> getPolicySummery(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<NameValuePair> nameValuePairs = null;
		try {
			nameValuePairs = dashboardService.getPolicySummery(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameValuePairs;
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
		List<Top3> top3Branch = null;
		try {
			top3Branch = dashboardService.getTopBranch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top3Branch;
	}
	
	@RequestMapping(value = "/getTopRegion", method = RequestMethod.GET)
	public List<Top3> getTopRegion() {
		List<Top3> top3Region = null;
		try {
			top3Region = dashboardService.getTopRegion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top3Region;
	}
	
	@RequestMapping(value = "/getTopZone", method = RequestMethod.GET)
	public List<Top3> getTopZone() {
		
		return null;
	}
	
	@RequestMapping(value = "/getGWPAndGWPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<Object> getGWPAndGWPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<Object> GWPAndGWPC = null;
		try {
			GWPAndGWPC = dashboardService.getGWPAndGWPC(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return GWPAndGWPC;
	}
	
	@RequestMapping(value = "/getMCFPAndMCFPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<Object> getMCFPAndMCFPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<Object> MCFPAndMCFPC = null;
		try {
			MCFPAndMCFPC = dashboardService.getMCFPAndMCFPC(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MCFPAndMCFPC;
	}
	
	@RequestMapping(value = "/getFYPAndFYPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<Object> getFYPAndFYPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<Object> FYPAndFYPC = null;
		try {
			FYPAndFYPC = dashboardService.getFYPAndFYPC(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FYPAndFYPC;
	}
	
	@RequestMapping(value = "/getNOPAndNOPC/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<Object> getNOPAndNOPC(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<Object> NOPAndNOPC = null;
		try {
			NOPAndNOPC = dashboardService.getNOPAndNOPC(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NOPAndNOPC;
	}
	
	@RequestMapping(value = "/getRINY/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public List<NameSeriasPair> getRINY(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		List<NameSeriasPair> RINY = null;
		try {
			RINY = dashboardService.getRINY(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return RINY;
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
	
	
	@RequestMapping(value = "/getCurrentMonthNOPCount/{userid}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public Integer getCurrentMonthNOPCount(@PathVariable String userid, @PathVariable String dashpara, @PathVariable String usertype) {
		Integer nopCount = null;
		try {
			nopCount = dashboardService.getCurrentMonthNOPCount(userid, dashpara, usertype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nopCount;
	}
	
	

}
