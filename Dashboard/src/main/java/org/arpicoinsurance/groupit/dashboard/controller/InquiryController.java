package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.dto.DataTableResponse;
import org.arpicoinsurance.groupit.dashboard.service.InquiryService;
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
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;

	@RequestMapping(value = "/getallinquiries/{userType}/{dashpara}/{advcod}/{offset}/{limit}/{equality}/{column}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllInquiriesByUser(@PathVariable String userType, @PathVariable String dashpara,
			@PathVariable String advcod, @PathVariable Integer offset, @PathVariable Integer limit) {

		DataTableResponse dataTableResponse = new DataTableResponse();
		try {
			// System.out.println(userType);
			// System.out.println(dashpara);
			// System.out.println(advcod);
			// System.out.println(offset);
			// System.out.println(limit);
			dataTableResponse.setData(inquiryService.inquiryLoad(userType, dashpara, advcod, offset, limit).toArray());
			dataTableResponse.setRecordsFiltered(limit);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(dataTableResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/getallinquiries/{userType}/{dashpara}/{advcod}/{offset}/{limit}/{equality}/{column}/{data}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllInquiriesByUserFilterd(@PathVariable String userType,
			@PathVariable String dashpara, @PathVariable String advcod, @PathVariable Integer offset,
			@PathVariable Integer limit, @PathVariable String equality, @PathVariable String column,
			@PathVariable String data) {

		data = data.trim();

		DataTableResponse dataTableResponse = new DataTableResponse();
		try {
			dataTableResponse.setData(inquiryService
					.inquiryLoadFilterd(userType, dashpara, advcod, offset, limit, column, data, equality).toArray());
			dataTableResponse.setRecordsFiltered(limit);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(dataTableResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/getCount/{userType}/{dashpara}/{advcod}/{offset}/{limit}/{equality}/{column}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCountByUser(@PathVariable String userType, @PathVariable String dashpara,
			@PathVariable String advcod, @PathVariable Integer offset, @PathVariable Integer limit) {

		try {
			Integer pageCount = inquiryService.inquiryLoadCount(userType, dashpara, "3857");

			return new ResponseEntity<Object>(pageCount, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getCount/{userType}/{dashpara}/{advcod}/{offset}/{limit}/{equality}/{column}/{data}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCountByUserFilterd(@PathVariable String userType, @PathVariable String dashpara,
			@PathVariable String advcod, @PathVariable Integer offset, @PathVariable Integer limit,
			@PathVariable String equality, @PathVariable String column, @PathVariable String data) {

		data = data.trim();

		try {
			return new ResponseEntity<Object>(
					inquiryService.inquiryLoadCountFilterd(userType, dashpara, advcod, column, data, equality),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getGeneral", method = RequestMethod.POST)
	public ResponseEntity<Object> getProposalGeneralInfo(@RequestBody String proposalNo) {

		try {
			return new ResponseEntity<Object>(inquiryService.getProposalGeneralInfo(proposalNo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getChildren", method = RequestMethod.POST)
	public ResponseEntity<Object> getChildInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");

		try {
			return new ResponseEntity<Object>(inquiryService.getChildrenList(dataArr[0], dataArr[1], dataArr[2]),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getNominee", method = RequestMethod.POST)
	public ResponseEntity<Object> getNomineeInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");

		// for (String string : dataArr) {
		// System.out.println(string);
		// }

		try {
			return new ResponseEntity<Object>(inquiryService.getNomineeList(dataArr[0], dataArr[1], dataArr[2]),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getBenefict", method = RequestMethod.POST)
	public ResponseEntity<Object> getBenefictInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");

		try {
			return new ResponseEntity<Object>(inquiryService.getBenefictList(dataArr[0], dataArr[1], dataArr[2]),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getMedQry", method = RequestMethod.POST)
	public ResponseEntity<Object> getMedicalReqInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");

		try {
			return new ResponseEntity<Object>(inquiryService.getMedicalReqList(dataArr[0], dataArr[1], dataArr[2]),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getTransferDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> getTransferHistoryInfo(@RequestBody String proposalNo) {

		try {
			return new ResponseEntity<Object>(inquiryService.getTransferHistoryList(proposalNo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getSettlementDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> getSettlementInfo(@RequestBody String proposalNo) {

		try {
			return new ResponseEntity<Object>(inquiryService.getSettlementList(proposalNo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getPaymentHistory", method = RequestMethod.POST)
	public ResponseEntity<Object> getPaymentInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");
		try {
			return new ResponseEntity<Object>(inquiryService.getPaymentHistory(dataArr[0], dataArr[1]), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getPolicyDisAch", method = RequestMethod.POST)
	public ResponseEntity<Object> getPolicyAch(@RequestBody String policyNo) {

		try {
			return new ResponseEntity<Object>(inquiryService.getPolicyDispatch(policyNo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
