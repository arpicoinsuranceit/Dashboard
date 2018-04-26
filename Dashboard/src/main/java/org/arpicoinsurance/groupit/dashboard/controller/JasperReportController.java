package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.Base64;
import java.util.Base64.Decoder;

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

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(advisor);
		advisor = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(status);
		status = new String(decodedByte);

		try {
			if (branch.equalsIgnoreCase("ALL")) {
				branch = "%";
			} else if (branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (advisor.equalsIgnoreCase("ALL")) {
				advisor = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equalsIgnoreCase("Y")) {
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

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(region);
		region = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(unl);
		unl = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);

		decodedByte = decoder.decode(status);
		status = new String(decodedByte);

		try {
			if (unl.equalsIgnoreCase("ALL") || unl.equalsIgnoreCase("undefined")) {
				unl = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
				zone = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equalsIgnoreCase("Y")) {
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

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(region);
		region = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(advisor);
		advisor = new String(decodedByte);

		decodedByte = decoder.decode(status);
		status = new String(decodedByte);

		try {
			if (advisor.equalsIgnoreCase("ALL") || advisor.equalsIgnoreCase("undefined")) {
				advisor = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
				zone = "%";
			}

			jwtDecorder = new JwtDecoder();

			if (status.equalsIgnoreCase("Y")) {
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

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(region);
		region = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(unl);
		unl = new String(decodedByte);

		try {
			if (unl.equalsIgnoreCase("ALL") || unl.equalsIgnoreCase("undefined")) {
				unl = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(date);
		date = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(region);
		region = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(code);
		code = new String(decodedByte);

		try {
			if (code.equalsIgnoreCase("ALL") || code.equalsIgnoreCase("undefined")) {
				code = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(date);
		date = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(region);
		region = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		try {

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(region);
		region = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(ul);
		ul = new String(decodedByte);

		decodedByte = decoder.decode(ic);
		ic = new String(decodedByte);

		decodedByte = decoder.decode(sp);
		sp = new String(decodedByte);
		try {
			if (ic.equalsIgnoreCase("ALL")) {
				ic = "%";
			} else if (ic.equalsIgnoreCase("undefined")) {
				ic = "%";
			}

			if (ul.equalsIgnoreCase("ALL")) {
				ul = "%";
			} else if (ul.equalsIgnoreCase("undefined")) {
				ul = "%";
			}

			if (branch.equalsIgnoreCase("ALL")) {
				branch = "%";
			} else if (branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL")) {
				region = "%";
			} else if (region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
				zone = "%";
			} else if (zone.equalsIgnoreCase("undefined")) {
				zone = "%";
			}

			if (sp.equalsIgnoreCase("ALL")) {
				sp = "%";
			} else if (sp.equalsIgnoreCase("undefined")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(asAtDate);
		asAtDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(regionCode);
		regionCode = new String(decodedByte);

		decodedByte = decoder.decode(branchCode);
		branchCode = new String(decodedByte);

		decodedByte = decoder.decode(code);
		code = new String(decodedByte);

		
		try {
			if (code.equalsIgnoreCase("ALL") || code.equalsIgnoreCase("undefined")) {
				code = "%";
			}

			if (branchCode.equalsIgnoreCase("ALL") || branchCode.equalsIgnoreCase("undefined")) {
				branchCode = "%";
			}

			if (regionCode.equalsIgnoreCase("ALL") || regionCode.equalsIgnoreCase("undefined")) {
				regionCode = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(asAtDate);
		asAtDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);

		decodedByte = decoder.decode(regionCode);
		regionCode = new String(decodedByte);

		decodedByte = decoder.decode(branchCode);
		branchCode = new String(decodedByte);

		decodedByte = decoder.decode(code);
		code = new String(decodedByte);
		
		decodedByte = decoder.decode(status);
		status = new String(decodedByte);
		
		try {
			if (code.equalsIgnoreCase("ALL") || code.equalsIgnoreCase("undefined")) {
				code = "%";
			}

			if (branchCode.equalsIgnoreCase("ALL") || branchCode.equalsIgnoreCase("undefined")) {
				branchCode = "%";
			}

			if (regionCode.equalsIgnoreCase("ALL") || regionCode.equalsIgnoreCase("undefined")) {
				regionCode = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
				zone = "%";
			}

			if (status.equalsIgnoreCase("Y")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(year);
		year = new String(decodedByte);

		decodedByte = decoder.decode(month);
		month = new String(decodedByte);

		decodedByte = decoder.decode(code);
		code = new String(decodedByte);
		
		decodedByte = decoder.decode(status);
		status = new String(decodedByte);
		
		try {

			if (month.equalsIgnoreCase("ALL") || month.equalsIgnoreCase("undefined")) {
				month = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (code.equalsIgnoreCase("ALL") || code.equalsIgnoreCase("undefined")) {
				code = "%";
			}

			if (status.equalsIgnoreCase("ALL") || status.equalsIgnoreCase("undefined")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);
		
		decodedByte = decoder.decode(region);
		region = new String(decodedByte);
		
		try {

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(month);
		month = new String(decodedByte);

		decodedByte = decoder.decode(year);
		year = new String(decodedByte);
		
		try {

			System.out.println("before" + month);
			if (month.equalsIgnoreCase("ALL") || month.equalsIgnoreCase("undefined")) {
				month = "%";
				System.out.println("after" + month);

			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		
		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);
		
		decodedByte = decoder.decode(region);
		region = new String(decodedByte);
		
		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);

		
		try {

			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";
			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		
		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);
		
		decodedByte = decoder.decode(region);
		region = new String(decodedByte);
		
		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);
		
		decodedByte = decoder.decode(so);
		so = new String(decodedByte);
		
		try {
			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";

			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";

			}

			if (so.equalsIgnoreCase("ALL") || so.equalsIgnoreCase("undefined")) {
				so = "%";

			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";

			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";

			}

			if (zone.equalsIgnoreCase("ALL")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);
		
		decodedByte = decoder.decode(region);
		region = new String(decodedByte);
		
		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);
		
		decodedByte = decoder.decode(status);
		status = new String(decodedByte);
		
		decodedByte = decoder.decode(unl);
		unl = new String(decodedByte);
		
		decodedByte = decoder.decode(type);
		type = new String(decodedByte);
		
		
		try {
			if (unl.equalsIgnoreCase("ALL") || unl.equalsIgnoreCase("undefined")) {
				unl = "%";
			}

			if (type.equalsIgnoreCase("ALL") || type.equalsIgnoreCase("undefined")) {
				type = "%";
			}

			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";
			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);
		
		decodedByte = decoder.decode(region);
		region = new String(decodedByte);
		
		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);
		
		decodedByte = decoder.decode(status);
		status = new String(decodedByte);
		
		decodedByte = decoder.decode(code);
		code = new String(decodedByte);
		
		
		try {
			if (code.equalsIgnoreCase("ALL") || code.equalsIgnoreCase("undefined")) {
				code = "%";
			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";
			}

			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(zone);
		zone = new String(decodedByte);
		
		decodedByte = decoder.decode(region);
		region = new String(decodedByte);
		
		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);
		
		decodedByte = decoder.decode(status);
		status = new String(decodedByte);
		
		decodedByte = decoder.decode(unl);
		unl = new String(decodedByte);
		
		decodedByte = decoder.decode(type);
		type = new String(decodedByte);
		
		try {
			if (unl.equalsIgnoreCase("ALL") || unl.equalsIgnoreCase("undefined")) {
				unl = "%";
			}

			if (type.equalsIgnoreCase("ALL") || type.equalsIgnoreCase("undefined")) {
				type = "%";
			}

			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";
			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (region.equalsIgnoreCase("ALL") || region.equalsIgnoreCase("undefined")) {
				region = "%";
			}

			if (zone.equalsIgnoreCase("ALL")) {
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
