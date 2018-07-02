package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.dto.BranchTargetSummaryDto;
import org.arpicoinsurance.groupit.dashboard.service.CommitmentService;
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
public class CommitmentController {

	@Autowired
	private CommitmentService commitmentService;

	@RequestMapping(method = RequestMethod.GET, value = "/targetsummaryz/{year}/{zoneCode}")
	public ResponseEntity<Object> getTargetSummaryZonalM(@PathVariable String year,
			@PathVariable String zoneCode) {
		
//		System.out.println(year + " ---- " + zoneCode);
		try {
			return new ResponseEntity<Object>(commitmentService.getBranchTargetSummaryZonalM(year, zoneCode) , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/targetsummaryb/{year}/{locCode}")
	public ResponseEntity<Object> getTargetSummaryBranchM(@PathVariable String year,@PathVariable String locCode) {
		
//		System.out.println(year + " ---- " + locCode);
		try {
			return new ResponseEntity<Object>( commitmentService.getBranchTargetSummaryBranchM(year, locCode), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/zonecode/{loccode}")
	public ResponseEntity<Object> getZoneCode(@PathVariable String loccode) {
		
//		System.out.println(loccode + " ---- ");
		try {
			return new ResponseEntity<Object> (commitmentService.getZoneCode(loccode) , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updatecommitment")
	public ResponseEntity<Object> updateCommitment(@RequestBody BranchTargetSummaryDto targetSummary) {
		
//		System.out.println(targetSummary.getJanCommitment() + " ---- ");
		try {
			return new ResponseEntity<Object>(commitmentService.updateCommitment(targetSummary), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllZone")
	public ResponseEntity<Object> getAllZone() {
		
		try {
			return new ResponseEntity<Object>(commitmentService.getAllZoneCode(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
