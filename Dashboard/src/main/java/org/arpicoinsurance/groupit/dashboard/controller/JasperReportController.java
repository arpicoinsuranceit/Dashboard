package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.helper.JwtDecoder;
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
	
	
	private JwtDecoder jwtDecorder;
	
		
	@RequestMapping(value = "/mcfpReport/{fromDate}/{toDate}/{advisor}/{branch}/{status}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  mcfpReport(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String advisor,@PathVariable String branch,@PathVariable String status) {
		System.out.println(fromDate);
		try {
			if(branch.equals("ALL")) {
				branch="%";
			}else if(branch.equals("undefined")) {
				branch="%";
			}
			
			if(advisor.equals("ALL")) {
				advisor="%";
			}
			
			jwtDecorder=new JwtDecoder();
			
			if(status.equals("Y")) {
				advisor=jwtDecorder.generate(advisor);
			}
			System.out.println(advisor+" , "+branch);
			return jasperReportService.mcfpReport(fromDate,toDate,advisor,branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    } 

	
	@RequestMapping(value = "/proposalRegister/{fromDate}/{toDate}/{zone}/{region}/{branch}/{unl}/{frequency}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  proposalRegister(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String unl,@PathVariable String frequency) {
		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch+","+unl+","+frequency);
		try {
			return jasperReportService.proposalRegister(fromDate, toDate, zone, region, branch, unl, frequency);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

	
	@RequestMapping(value = "/pendingRequirements/{advisor}/{branch}/{region}/{zone}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  pendingRequirements(@PathVariable String advisor,@PathVariable String branch,@PathVariable String region,@PathVariable String zone) {
		System.out.println(advisor+","+branch+","+region+","+zone);
		try {
			return jasperReportService.pendingRequirements(advisor, branch, region, zone);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }


	@RequestMapping(value = "/retentionUnit/{toDate}/{zone}/{region}/{branch}/{unl}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  retentionUnit(@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String unl) {
		System.out.println(toDate+","+zone+","+region+","+branch+","+unl);
		try {
			return jasperReportService.retentionUnit(toDate, zone, region, branch, unl);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }

	@RequestMapping(value = "/retentionCode/{date}/{zone}/{region}/{branch}/{code}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  retentionCode(@PathVariable String date,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String code) {
		System.out.println(date+","+zone+","+region+","+branch+","+code);
		try {
			return jasperReportService.retentionCode(date, zone, region, branch, code);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }


	@RequestMapping(value = "/retentionBranch/{date}/{zone}/{region}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  retentionBranch(@PathVariable String date,@PathVariable String zone,@PathVariable String region,@PathVariable String branch) {
		System.out.println(date+","+zone+","+region+","+branch);
		try {
			if(zone.equals("ALL")) {
				zone="%";
			}else if(zone.equals("undefined")) {
				zone="%";
			}
			
			if(region.equals("ALL")) {
				region="%";
			}else if(region.equals("undefined")) {
				region="%";
			}
			
			if(branch.equals("ALL")) {
				branch="%";
			}else if(branch.equals("undefined")) {
				branch="%";
			}
			
			
			System.out.println(region+" , "+branch);
			return jasperReportService.retentionBranch(date, zone, region, branch);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }


	@RequestMapping(value = "/detailsOfPolicies/{fromDate}/{toDate}/{ic}/{ul}/{branch}/{region}/{zone}/{sp}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  detailsOfPolicies(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String ic,@PathVariable String ul,@PathVariable String branch,@PathVariable String region,@PathVariable String zone,@PathVariable String sp) {
		System.out.println(fromDate+","+toDate+","+ic+","+ul+","+branch+","+region+","+zone+","+sp);
		try {
			if(ic.equals("ALL")) {
				ic="%";
			}else if(ic.equals("undefined")) {
				ic="%";
			}
			
			if(ul.equals("ALL")) {
				ul="%";
			}else if(ul.equals("undefined")) {
				ul="%";
			}
			
			if(branch.equals("ALL")) {
				branch="%";
			}else if(branch.equals("undefined")) {
				branch="%";
			}
			
			if(region.equals("ALL")) {
				region="%";
			}else if(region.equals("undefined")) {
				region="%";
			}
			
			if(zone.equals("ALL")) {
				zone="%";
			}else if(zone.equals("undefined")) {
				zone="%";
			}
			
			if(sp.equals("ALL")) {
				sp="%";
			}else if(sp.equals("undefined")) {
				sp="%";
			}
			
			
			System.out.println(region+" , "+branch);
			return jasperReportService.detailsOfPolicies(fromDate, toDate, ic, ul, branch, region, zone, sp);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		

        return null;
    }
	
	@RequestMapping(value = "/premiumDueReportLive/{asAtDate}/{code}/{branchCode}/{regionCode}/{zone}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  premiumDueReportLive(@PathVariable String asAtDate  ,@PathVariable String code,@PathVariable String branchCode,@PathVariable String regionCode,@PathVariable String zone) {
		System.out.println(asAtDate+","+code+","+branchCode+","+regionCode+","+zone);
		try {
			return jasperReportService.premiumDueReportLive(asAtDate, code, branchCode, regionCode, zone);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	@RequestMapping(value = "/premiumDueReport/{asAtDate}/{code}/{branchCode}/{regionCode}/{zone}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  premiumDueReport(@PathVariable String asAtDate  ,@PathVariable String code,@PathVariable String branchCode,@PathVariable String regionCode,@PathVariable String zone) {
		System.out.println(asAtDate+","+code+","+branchCode+","+regionCode+","+zone);
		try {
			return jasperReportService.premiumDueReport(asAtDate, code, branchCode, regionCode, zone);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	
	@RequestMapping(value = "/grantStmtBranch/{branch}/{year}/{month}/{code}/{status}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  grantStmtBranch(@PathVariable String branch  ,@PathVariable String year,@PathVariable String month,@PathVariable String code,@PathVariable String status) {
		System.out.println(branch+","+year+","+month+","+code+","+status);
		try {
			return jasperReportService.grantStmtBranch(branch, year, month, code, status);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	@RequestMapping(value = "/firstPremiumLapSummary/{fromDate}/{toDate}/{zone}/{region}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  firstPremiumLapSummary(@PathVariable String fromDate  ,@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch) {
		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch);
		try {
			if(zone.equals("ALL")) {
				zone="%";
			}else if(zone.equals("undefined")) {
				zone="%";
			}
			
			if(region.equals("ALL")) {
				region="%";
			}else if(region.equals("undefined")) {
				region="%";
			}
			
			if(branch.equals("ALL")) {
				branch="%";
			}else if(branch.equals("undefined")) {
				branch="%";
			}
			
			
			return jasperReportService.firstPremiumLapSummary(fromDate, toDate, zone, region, branch);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	@RequestMapping(value = "/policyAcknowledgement/{branch}/{year}/{month}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  policyAcknowledgement(@PathVariable String branch  ,@PathVariable String year,@PathVariable String month) {
		System.out.println(branch+","+year+","+month);
		try {
			if(branch.equals("ALL")) {
				branch="%";
			}else if(branch.equals("undefined")) {
				branch="%";
			}
			
			
			return jasperReportService.policyAcknowledgement(branch, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	@RequestMapping(value = "/salesPerfSummaryCode/{fromDate}/{toDate}/{zone}/{region}/{branch}/{frequency}/{product}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  salesPerfSummaryCode(@PathVariable String fromDate  ,@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String frequency,@PathVariable String product) {
		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch+","+frequency+","+product);
		try {
			return jasperReportService.salesPerfSummaryCode(fromDate, toDate, zone, region, branch, frequency, product);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	
	@RequestMapping(value = "/salesPerfSummary/{fromDate}/{toDate}/{zone}/{region}/{branch}/{frequency}/{product}/{so}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  salesPerfSummary(@PathVariable String fromDate  ,@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String frequency,@PathVariable String product,@PathVariable String so) {
		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch+","+frequency+","+product+","+so);
		try {
			return jasperReportService.salesPerfSummary(fromDate, toDate, zone, region, branch, frequency, product, so);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	
	@RequestMapping(value = "/unitIsPerfSummary/{fromDate}/{toDate}/{zone}/{region}/{branch}/{unl}/{type}/{frequency}/{product}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  unitIsPerfSummary(@PathVariable String fromDate  ,@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String unl,@PathVariable String type,@PathVariable String frequency,@PathVariable String product) {
		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch+","+unl+","+type+","+frequency+","+product);
		try {
			return jasperReportService.unitIsPerfSummary(fromDate, toDate, zone, region, branch, unl, type, frequency, product);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	
	@RequestMapping(value = "/salesPerfDetail/{fromDate}/{toDate}/{code}/{zone}/{region}/{branch}/{product}/{frequency}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  salesPerfDetail(@PathVariable String fromDate  ,@PathVariable String toDate,@PathVariable String code,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String product,@PathVariable String frequency) {
		System.out.println(fromDate+","+toDate+","+code+","+zone+","+region+","+branch+","+product+","+frequency);
		try {
			return jasperReportService.salesPerfDetail(fromDate, toDate, code, zone, region, branch, product, frequency);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
	
	

	@RequestMapping(value = "/unitIsPerfDetails/{fromDate}/{toDate}/{zone}/{region}/{branch}/{unl}/{type}/{frequency}/{product}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[]  unitIsPerfDetails(@PathVariable String fromDate  ,@PathVariable String toDate,@PathVariable String zone,@PathVariable String region,@PathVariable String branch,@PathVariable String unl,@PathVariable String type,@PathVariable String frequency,@PathVariable String product) {
		System.out.println(fromDate+","+toDate+","+zone+","+region+","+branch+","+unl+","+type+","+frequency+","+product);
		try {
			return jasperReportService.unitIsPerfDetails(fromDate, toDate, zone, region, branch, unl, type, frequency, product);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
	



}
