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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "/dashboard/{id:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> loadDashboardData(@PathVariable String id) {
		MainRespDto mainRespDto = null;
		try {
			mainRespDto = dashboardService.getDashboard(id);
			return new ResponseEntity<Object>(mainRespDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getdashboardpara", method = RequestMethod.POST)
	public ResponseEntity<Object> getDashboardData(@RequestBody String userid) {
		//System.out.println(userid +"getDashboardData////");
		DashboardPara dashboardPara = null;
		try {
			dashboardPara = dashboardService.getDashboardPara(userid);
			//System.out.println(dashboardPara +"getDashboardData");
			return new ResponseEntity<Object>(dashboardPara, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getCurrentMonthTarget/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTarget(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {

		return null;
	}

	@RequestMapping(value = "/getCurrentMonthTargetGWP/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetGWP(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {

		return null;
	}

	@RequestMapping(value = "/getCurrentMonthTargetMCFP/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetMCFP(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {

		return null;
	}

	@RequestMapping(value = "/getCurrentMonthTargetFYP/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetFYP(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {

		return null;
	}

	@RequestMapping(value = "/getCurrentMonthTargetNOP/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public MonthlyTarget getCurrentMonthTargetNOP(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {

		return null;
	}

	@RequestMapping(value = "/getCurrentMonthYearlyTarget/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCurrentMonthYearlyTarget(@PathVariable String userid,
			@PathVariable String dashpara, @PathVariable String usertype) {
		// System.out.println(userid+" - "+dashpara+" - "+usertype);
		List<Object> currentMonthYearlyTarget = null;
		try {
			currentMonthYearlyTarget = dashboardService.getCurrentMonthYearlyTarget(userid, dashpara, usertype);
			return new ResponseEntity<Object>(currentMonthYearlyTarget, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getCurrentMonthYearlyTargetUNL/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCurrentMonthYearlyTargetUNL(@PathVariable String userid,
			@PathVariable String dashpara, @PathVariable String usertype) {
		// System.out.println(userid+" - "+dashpara+" - "+usertype);
		List<Object> currentMonthYearlyTarget = null;
		try {
			currentMonthYearlyTarget = dashboardService.getCurrentMonthYearlyTargetUNL(userid, dashpara, usertype);
			return new ResponseEntity<Object>(currentMonthYearlyTarget, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getPolicySummery/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPolicySummery(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<NameValuePair> nameValuePairs = null;
		try {
			nameValuePairs = dashboardService.getPolicySummery(userid, dashpara, usertype);
			return new ResponseEntity<Object>(nameValuePairs, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getTopIC", method = RequestMethod.GET)
	public ResponseEntity<Object> getTopIC() {
		List<Top3> top3IC = null;
		try {
			top3IC = dashboardService.getTopIC();
			return new ResponseEntity<Object>(top3IC, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@RequestMapping(value = "/getTopIS", method = RequestMethod.GET)
	public ResponseEntity<Object> getTopIS() {
		List<Top3> top3IS = null;
		try {
			top3IS = dashboardService.getTopIS();
			return new ResponseEntity<Object>(top3IS, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getTopUL", method = RequestMethod.GET)
	public ResponseEntity<Object> getTopUl() {
		List<Top3> top3UL = null;
		try {
			top3UL = dashboardService.getTopUL();
			return new ResponseEntity<Object>(top3UL, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getTopBranch", method = RequestMethod.GET)
	public ResponseEntity<Object> getTopBranch() {
		List<Top3> top3Branch = null;
		try {
			top3Branch = dashboardService.getTopBranch();
			return new ResponseEntity<Object>(top3Branch, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getTopRegion", method = RequestMethod.GET)
	public ResponseEntity<Object> getTopRegion() {

		return null;
	}

	@RequestMapping(value = "/getTopZone", method = RequestMethod.GET)
	public List<Top3> getTopZone() {

		return null;
	}

	@RequestMapping(value = "/getGWPAndGWPC/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getGWPAndGWPC(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<Object> GWPAndGWPC = null;
		try {
			GWPAndGWPC = dashboardService.getGWPAndGWPC(userid, dashpara, usertype);
			return new ResponseEntity<Object>(GWPAndGWPC, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getMCFPAndMCFPC/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMCFPAndMCFPC(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<Object> MCFPAndMCFPC = null;
		try {
			MCFPAndMCFPC = dashboardService.getMCFPAndMCFPC(userid, dashpara, usertype);
			return new ResponseEntity<Object>(MCFPAndMCFPC, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getFYPAndFYPC/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFYPAndFYPC(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<Object> FYPAndFYPC = null;
		try {
			FYPAndFYPC = dashboardService.getFYPAndFYPC(userid, dashpara, usertype);
			return new ResponseEntity<Object>(FYPAndFYPC, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getNOPAndNOPC/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getNOPAndNOPC(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<Object> NOPAndNOPC = null;
		try {
			NOPAndNOPC = dashboardService.getNOPAndNOPC(userid, dashpara, usertype);
			return new ResponseEntity<Object>(NOPAndNOPC, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getRINY/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getRINY(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<NameSeriasPair> RINY = null;
		try {
			RINY = dashboardService.getRINY(userid, dashpara, usertype);
			return new ResponseEntity<Object>(RINY, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getDuePolicies/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getDuePolicies(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<DuePolicies> duePolicies = null;
		try {
			duePolicies = dashboardService.getDuePolicies(userid, dashpara, usertype);
			return new ResponseEntity<Object>(duePolicies, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getPendingPolicies/{userid:.+}/{dashpara}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPendingPolicies(@PathVariable String userid, @PathVariable String dashpara,
			@PathVariable String usertype) {
		List<PendingPolicies> pendingPolicies = null;
		try {
			pendingPolicies = dashboardService.getPendingPolicies(userid, dashpara, usertype);
			return new ResponseEntity<Object>(pendingPolicies, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
