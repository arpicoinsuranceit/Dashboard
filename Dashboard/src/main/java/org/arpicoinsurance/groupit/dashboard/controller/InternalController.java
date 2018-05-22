package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.dto.InvpSavePersonalInfo;
import org.arpicoinsurance.groupit.dashboard.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class InternalController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/testABC", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCustomer(@RequestBody InvpSavePersonalInfo invpSavePersonalInfo) {
		String custCode = null;
		
		try {
			custCode = customerService.save(invpSavePersonalInfo);
			return new ResponseEntity<Object>(custCode, HttpStatus.OK);
			
//			System.out.println(custCode);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
