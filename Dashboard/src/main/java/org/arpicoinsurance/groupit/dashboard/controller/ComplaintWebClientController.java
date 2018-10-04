package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.service.ComplaintWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ComplaintWebClientController {
	
	@Autowired
	private ComplaintWebClientService complaintWebClientService;
	
	@RequestMapping(value="/loadPolicyNumbers", method = RequestMethod.POST)
	public List<String> loadPolicyNumbers(@RequestParam("nic") String nic) throws Exception{
		System.out.println(nic);
		
		List<String> nicList = complaintWebClientService.loadProposalNumbers(nic);
		
		System.out.println(nicList.size());
		
		nicList.forEach(System.out::println);
		
		return nicList;
		
	}

}
