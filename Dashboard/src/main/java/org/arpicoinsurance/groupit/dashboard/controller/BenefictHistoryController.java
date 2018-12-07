package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.BenefictHistory;
import org.arpicoinsurance.groupit.dashboard.service.BenefictHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BenefictHistoryController {

	@Autowired
	private BenefictHistoryService benefictHistoryService;
	
	@PostMapping(value = "/getbeneficthistory")
	public List<BenefictHistory> getHistory (@RequestParam String nic) throws Exception {
		
		System.out.println("nic : " + nic);
		
		return benefictHistoryService.getHistory(nic);
	}
	
}
