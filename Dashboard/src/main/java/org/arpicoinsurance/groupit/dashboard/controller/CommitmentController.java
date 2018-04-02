package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;
import org.arpicoinsurance.groupit.dashboard.dto.BranchTargetSummaryDto;
import org.arpicoinsurance.groupit.dashboard.service.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CommitmentController {

	@Autowired
	private CommitmentService commitmentService;

	@RequestMapping(method = RequestMethod.GET, value = "/targetsummaryz/{year}/{zoneCode}")
	public List<BranchTargetSummaryDto> getTargetSummaryZonalM(@PathVariable String year,
			@PathVariable String zoneCode) {
		
		System.out.println(year + " ---- " + zoneCode);
		try {
			return commitmentService.getBranchTargetSummaryZonalM(year, zoneCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/targetsummaryb/{year}/{locCode}")
	public List<BranchTargetSummaryDto> getTargetSummaryBranchM(@PathVariable String year,@PathVariable String locCode) {
		
		System.out.println(year + " ---- " + locCode);
		try {
			return commitmentService.getBranchTargetSummaryBranchM(year, locCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/zonecode/{loccode}")
	public String getZoneCode(@PathVariable String loccode) {
		
		System.out.println(loccode + " ---- ");
		try {
			return commitmentService.getZoneCode(loccode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updatecommitment")
	public Integer updateCommitment(@RequestBody BranchTargetSummaryDto targetSummary) {
		
		System.out.println(targetSummary.getJanCommitment() + " ---- ");
		try {
			return commitmentService.updateCommitment(targetSummary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}

	
}
