package org.arpicoinsurance.groupit.dashboard.controller;

import javax.servlet.http.HttpServletResponse;

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
	public byte[] mcfpReport(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String advisor,
			@PathVariable String branch, @PathVariable String status, HttpServletResponse response) {
		System.out.println(fromDate);
		response.setHeader("Content-Disposition", "inline; filename=mcfpr.pdf");
		response.setContentType("application/pdf");
		System.out.println(advisor + "," + branch);
		try {
			if (branch.equals("ALL")) {
				branch = "%";
			} else if (branch.equals("undefined")) {
				branch = "%";
			}

			if (advisor.equals("ALL")) {
				advisor = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equals("Y")) {
				advisor = jwtDecorder.generate(advisor);
			}
			return jasperReportService.mcfpReport(fromDate, toDate, advisor, branch);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/proposalRegister/{fromDate}/{toDate}/{zone}/{region}/{branch}/{unl}/{frequency}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] proposalRegister(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String zone, @PathVariable String region, @PathVariable String branch,
			@PathVariable String unl, @PathVariable String frequency, @PathVariable String status) {
		System.out.println(
				fromDate + "," + toDate + "," + zone + "," + region + "," + branch + "," + unl + "," + frequency);
		try {
			if (unl.equals("ALL") || unl.equals("undefined")) {
				unl = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equals("Y")) {
				unl = jwtDecorder.generate(unl);
			}

			return jasperReportService.proposalRegister(fromDate, toDate, zone, region, branch, unl, frequency);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/pendingRequirements/{advisor}/{branch}/{region}/{zone}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] pendingRequirements(@PathVariable String advisor, @PathVariable String branch,
			@PathVariable String region, @PathVariable String zone, @PathVariable String status) {
		System.out.println(advisor + "," + branch + "," + region + "," + zone);
		try {
			if (advisor.equals("ALL") || advisor.equals("undefined")) {
				advisor = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equals("Y")) {
				advisor = jwtDecorder.generate(advisor);
			}
			return jasperReportService.pendingRequirements(advisor, branch, region, zone);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/retentionUnit/{toDate}/{zone}/{region}/{branch}/{unl}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] retentionUnit(@PathVariable String toDate, @PathVariable String zone, @PathVariable String region,
			@PathVariable String branch, @PathVariable String unl) {
		System.out.println(toDate + "," + zone + "," + region + "," + branch + "," + unl);
		try {
			if (unl.equals("ALL") || unl.equals("undefined")) {
				unl = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			return jasperReportService.retentionUnit(toDate, zone, region, branch, unl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/retentionCode/{date}/{zone}/{region}/{branch}/{code}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] retentionCode(@PathVariable String date, @PathVariable String zone, @PathVariable String region,
			@PathVariable String branch, @PathVariable String code) {
		System.out.println(date + "," + zone + "," + region + "," + branch + "," + code);
		try {
			if (code.equals("ALL") || code.equals("undefined")) {
				code = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}
			return jasperReportService.retentionCode(date, zone, region, branch, code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/retentionBranch/{date}/{zone}/{region}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] retentionBranch(@PathVariable String date, @PathVariable String zone, @PathVariable String region,
			@PathVariable String branch) {
		System.out.println(date + "," + zone + "," + region + "," + branch);
		try {

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			return jasperReportService.retentionBranch(date, zone, region, branch);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/detailsOfPolicies/{fromDate}/{toDate}/{ic}/{ul}/{branch}/{region}/{zone}/{sp}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] detailsOfPolicies(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String ic,
			@PathVariable String ul, @PathVariable String branch, @PathVariable String region,
			@PathVariable String zone, @PathVariable String sp) {
		System.out.println(
				fromDate + "," + toDate + "," + ic + "," + ul + "," + branch + "," + region + "," + zone + "," + sp);
		try {
			if (ic.equals("ALL")) {
				ic = "%";
			} else if (ic.equals("undefined")) {
				ic = "%";
			}

			if (ul.equals("ALL")) {
				ul = "%";
			} else if (ul.equals("undefined")) {
				ul = "%";
			}

			if (branch.equals("ALL")) {
				branch = "%";
			} else if (branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL")) {
				region = "%";
			} else if (region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			} else if (zone.equals("undefined")) {
				zone = "%";
			}

			if (sp.equals("ALL")) {
				sp = "%";
			} else if (sp.equals("undefined")) {
				sp = "%";
			}

			System.out.println(region + " , " + branch);
			return jasperReportService.detailsOfPolicies(fromDate, toDate, ic, ul, branch, region, zone, sp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/premiumDueReportLive/{asAtDate}/{code}/{branchCode}/{regionCode}/{zone}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] premiumDueReportLive(@PathVariable String asAtDate, @PathVariable String code,
			@PathVariable String branchCode, @PathVariable String regionCode, @PathVariable String zone) {
		System.out.println(asAtDate + "," + code + "," + branchCode + "," + regionCode + "," + zone);
		try {
			if (code.equals("ALL") || code.equals("undefined")) {
				code = "%";
			}

			if (branchCode.equals("ALL") || branchCode.equals("undefined")) {
				branchCode = "%";
			}

			if (regionCode.equals("ALL") || regionCode.equals("undefined")) {
				regionCode = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}
			return jasperReportService.premiumDueReportLive(asAtDate, code, branchCode, regionCode, zone);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/premiumDueReport/{asAtDate}/{code}/{branchCode}/{regionCode}/{zone}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] premiumDueReport(@PathVariable String asAtDate, @PathVariable String code,
			@PathVariable String branchCode, @PathVariable String regionCode, @PathVariable String zone,
			@PathVariable String status) {
		System.out.println(asAtDate + "," + code + "," + branchCode + "," + regionCode + "," + zone);
		try {
			if (code.equals("ALL") || code.equals("undefined")) {
				code = "%";
			}

			if (branchCode.equals("ALL") || branchCode.equals("undefined")) {
				branchCode = "%";
			}

			if (regionCode.equals("ALL") || regionCode.equals("undefined")) {
				regionCode = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			if (status.equals("Y")) {
				code = jwtDecorder.generate(code);
			}
			return jasperReportService.premiumDueReport(asAtDate, code, branchCode, regionCode, zone);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/grantStmtBranch/{branch}/{year}/{month}/{code}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] grantStmtBranch(@PathVariable String branch, @PathVariable String year, @PathVariable String month,
			@PathVariable String code, @PathVariable String status) {
		System.out.println(branch + "," + year + "," + month + "," + code + "," + status);
		try {

			if (month.equals("ALL") || month.equals("undefined")) {
				month = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (code.equals("ALL") || code.equals("undefined")) {
				code = "%";
			}

			if (status.equals("ALL") || status.equals("undefined")) {
				status = "%";
			}

			return jasperReportService.grantStmtBranch(branch, year, month, code, status);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/firstPremiumLapSummary/{fromDate}/{toDate}/{zone}/{region}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] firstPremiumLapSummary(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String zone, @PathVariable String region, @PathVariable String branch) {
		System.out.println(fromDate + "," + toDate + "," + zone + "," + region + "," + branch);
		try {

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			return jasperReportService.firstPremiumLapSummary(fromDate, toDate, zone, region, branch);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/policyAcknowledgement/{branch}/{year}/{month}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] policyAcknowledgement(@PathVariable String branch, @PathVariable String year,
			@PathVariable String month) {
		System.out.println(branch + "," + year + "," + month);
		try {

			System.out.println("before" + month);
			if (month.equals("ALL") || month.equals("undefined")) {
				month = "%";
				System.out.println("after" + month);

			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			return jasperReportService.policyAcknowledgement(branch, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/salesPerfSummaryCode/{fromDate}/{toDate}/{zone}/{region}/{branch}/{frequency}/{product}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] salesPerfSummaryCode(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String zone, @PathVariable String region, @PathVariable String branch,
			@PathVariable String frequency, @PathVariable String product) {
		System.out.println(
				fromDate + "," + toDate + "," + zone + "," + region + "," + branch + "," + frequency + "," + product);
		try {

			if (frequency.equals("ALL") || frequency.equals("undefined")) {
				frequency = "%";
			}

			if (product.equals("ALL") || product.equals("undefined")) {
				product = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}
			return jasperReportService.salesPerfSummaryCode(fromDate, toDate, zone, region, branch, frequency, product);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/salesPerfSummary/{fromDate}/{toDate}/{zone}/{region}/{branch}/{frequency}/{product}/{so}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] salesPerfSummary(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String zone, @PathVariable String region, @PathVariable String branch,
			@PathVariable String frequency, @PathVariable String product, @PathVariable String so) {
		System.out.println(fromDate + "," + toDate + "," + zone + "," + region + "," + branch + "," + frequency + ","
				+ product + "," + so);
		try {
			if (frequency == ("ALL") || frequency.equals("undefined")) {
				frequency = "%";

			}

			if (product == ("ALL") || product.equals("undefined")) {
				product = "%";

			}

			if (so == ("ALL") || so.equals("undefined")) {
				so = "%";

			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			return jasperReportService.salesPerfSummary(fromDate, toDate, zone, region, branch, frequency, product, so);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/unitIsPerfSummary/{fromDate}/{toDate}/{zone}/{region}/{branch}/{unl}/{type}/{frequency}/{product}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] unitIsPerfSummary(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String zone, @PathVariable String region, @PathVariable String branch,
			@PathVariable String unl, @PathVariable String type, @PathVariable String frequency,
			@PathVariable String product, @PathVariable String status) {
		System.out.println(fromDate + "," + toDate + "," + zone + "," + region + "," + branch + "," + unl + "," + type
				+ "," + frequency + "," + product);
		try {
			if (unl.equals("ALL") || unl.equals("undefined")) {
				unl = "%";
			}

			if (type.equals("ALL") || type.equals("undefined")) {
				type = "%";
			}

			if (frequency.equals("ALL") || frequency.equals("undefined")) {
				frequency = "%";
			}

			if (product.equals("ALL") || product.equals("undefined")) {
				product = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equals("Y")) {
				unl = jwtDecorder.generate(unl);
			}

			return jasperReportService.unitIsPerfSummary(fromDate, toDate, zone, region, branch, unl, type, frequency,
					product);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/salesPerfDetail/{fromDate}/{toDate}/{code}/{zone}/{region}/{branch}/{product}/{frequency}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] salesPerfDetail(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String code,
			@PathVariable String zone, @PathVariable String region, @PathVariable String branch,
			@PathVariable String product, @PathVariable String frequency, @PathVariable String status) {
		System.out.println(fromDate + "," + toDate + "," + code + "," + zone + "," + region + "," + branch + ","
				+ product + "," + frequency);
		try {
			if (code.equals("ALL") || code.equals("undefined")) {
				code = "%";
			}

			if (product.equals("ALL") || product.equals("undefined")) {
				product = "%";
			}

			if (frequency.equals("ALL") || frequency.equals("undefined")) {
				frequency = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equals("Y")) {
				code = jwtDecorder.generate(code);
			}
			return jasperReportService.salesPerfDetail(fromDate, toDate, code, zone, region, branch, product,
					frequency);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/unitIsPerfDetails/{fromDate}/{toDate}/{zone}/{region}/{branch}/{unl}/{type}/{frequency}/{product}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] unitIsPerfDetails(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String zone, @PathVariable String region, @PathVariable String branch,
			@PathVariable String unl, @PathVariable String type, @PathVariable String frequency,
			@PathVariable String product, @PathVariable String status) {
		System.out.println(fromDate + "," + toDate + "," + zone + "," + region + "," + branch + "," + unl + "," + type
				+ "," + frequency + "," + product);
		try {
			if (unl.equals("ALL") || unl.equals("undefined")) {
				unl = "%";
			}

			if (type.equals("ALL") || type.equals("undefined")) {
				type = "%";
			}

			if (frequency.equals("ALL") || frequency.equals("undefined")) {
				frequency = "%";
			}

			if (product.equals("ALL") || product.equals("undefined")) {
				product = "%";
			}

			if (branch.equals("ALL") || branch.equals("undefined")) {
				branch = "%";
			}

			if (region.equals("ALL") || region.equals("undefined")) {
				region = "%";
			}

			if (zone.equals("ALL")) {
				zone = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equals("Y")) {
				unl = jwtDecorder.generate(unl);
			}
			return jasperReportService.unitIsPerfDetails(fromDate, toDate, zone, region, branch, unl, type, frequency,
					product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
