package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.dto.CommisionRatePara;
import org.arpicoinsurance.groupit.dashboard.service.CommisionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CommisionRateController {
	
	@Autowired
	private CommisionRateService commisionRateService;
	
	@RequestMapping(value = "/commisionRate")
	public ResponseEntity<Object> getPreviousPolicies (@RequestBody CommisionRatePara commisionRatePara) {
		//System.out.println(commisionRatePara.getPrdcod()+" "+commisionRatePara.getComyer()+" "+commisionRatePara.getToterm());
		HashMap<String, Double> commisionRate;
		try {
			commisionRate = commisionRateService.getCommisionRate(commisionRatePara);
			return new ResponseEntity<Object>(commisionRate, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
