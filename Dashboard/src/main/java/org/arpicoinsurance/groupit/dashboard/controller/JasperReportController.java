package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.report.service.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class JasperReportController {
	
	@Autowired
	private JasperReportService jasperReportService;
	
		
	@RequestMapping(value = "/mcfpReport/{fromDate}/{toDate}/{advisor}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  mcfpReport(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String advisor,@PathVariable String branch) {
		System.out.println(fromDate+","+toDate+","+advisor+","+branch);
		try {
			return jasperReportService.mcfpReport(fromDate, toDate, advisor, branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    } 

	@RequestMapping(value = "/proposalRegister/{fromDate}/{toDate}/{zone}/{region}/{branch}/{unl}/{frequency}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  proposalRegister(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String unl,@PathVariable String frequency) {
		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch+","+unl+","+frequency);
		

        return null;
    }

	
	@RequestMapping(value = "/pendingRequirements/{advisor}/{branch}/{region}/{zone}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  pendingRequirements(@PathVariable String advisor,@PathVariable String branch,@PathVariable String region,@PathVariable String zone) {
		System.out.println(advisor+","+branch+","+region+","+zone);
		

        return null;
    }


	@RequestMapping(value = "/retentionUnit/{toDate}/{zone}/{region}/{branch}/{unl}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  retentionUnit(@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String unl) {
		System.out.println(toDate+","+zone+","+region+","+branch+","+unl);
		

        return null;
    }

	@RequestMapping(value = "/retentionCode/{date}/{zone}/{region}/{branch}/{code}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  retentionCode(@PathVariable String date,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String code) {
		System.out.println(date+","+zone+","+region+","+branch+","+code);
		

        return null;
    }



}
