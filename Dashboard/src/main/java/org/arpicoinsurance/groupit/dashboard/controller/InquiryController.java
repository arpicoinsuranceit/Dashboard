package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.BenefictInquiryDto;
import org.arpicoinsurance.groupit.dashboard.dto.ChildDto;
import org.arpicoinsurance.groupit.dashboard.dto.DataTableResponse;
import org.arpicoinsurance.groupit.dashboard.dto.MedicalReqDto;
import org.arpicoinsurance.groupit.dashboard.dto.NomineeInquiryDao;
import org.arpicoinsurance.groupit.dashboard.dto.PaymentHistoryDto;
import org.arpicoinsurance.groupit.dashboard.dto.PolicyDispatchAcknowDto;
import org.arpicoinsurance.groupit.dashboard.dto.ProposalGeneralDto;
import org.arpicoinsurance.groupit.dashboard.dto.SettlementDto;
import org.arpicoinsurance.groupit.dashboard.dto.TransferHistoryDto;
import org.arpicoinsurance.groupit.dashboard.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public DataTableResponse getAllInquiriesByUser(@PathVariable String userType, @PathVariable String dashpara,
			@PathVariable String advcod, @PathVariable Integer offset, @PathVariable Integer limit) {
		
		
		DataTableResponse dataTableResponse = new DataTableResponse();
		try {
			System.out.println(userType);
			System.out.println(dashpara);
			System.out.println(advcod);
			System.out.println(offset);
			System.out.println(limit);
			dataTableResponse.setData(inquiryService.inquiryLoad(userType, dashpara, advcod, offset, limit).toArray());
			dataTableResponse.setRecordsFiltered(limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataTableResponse;
	}

	@RequestMapping(value = "/getallinquiries/{userType}/{dashpara}/{advcod}/{offset}/{limit}/{equality}/{column}/{data}", method = RequestMethod.GET)
	public DataTableResponse getAllInquiriesByUserFilterd(@PathVariable String userType, @PathVariable String dashpara,
			@PathVariable String advcod, @PathVariable Integer offset, @PathVariable Integer limit,
			@PathVariable String equality, @PathVariable String column, @PathVariable String data) {

		data =data.trim();
		
		DataTableResponse dataTableResponse = new DataTableResponse();
		try {
			dataTableResponse.setData(inquiryService.inquiryLoadFilterd(userType, dashpara, advcod, offset, limit, column, data, equality).toArray());
			dataTableResponse.setRecordsFiltered(limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataTableResponse;

	}

	@RequestMapping(value = "/getCount/{userType}/{dashpara}/{advcod}/{offset}/{limit}/{equality}/{column}", method = RequestMethod.GET)
	public Integer getAllCountByUser(@PathVariable String userType, @PathVariable String dashpara,
			@PathVariable String advcod, @PathVariable Integer offset, @PathVariable Integer limit) {

		try {
			Integer pageCount = inquiryService.inquiryLoadCount(userType, dashpara, "3857");


			return pageCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@RequestMapping(value = "/getCount/{userType}/{dashpara}/{advcod}/{offset}/{limit}/{equality}/{column}/{data}", method = RequestMethod.GET)
	public Integer getAllCountByUserFilterd(@PathVariable String userType, @PathVariable String dashpara,
			@PathVariable String advcod, @PathVariable Integer offset, @PathVariable Integer limit,
			@PathVariable String equality, @PathVariable String column, @PathVariable String data) {

		data =data.trim();
		
		try {
			return inquiryService.inquiryLoadCountFilterd(userType, dashpara, advcod, column, data, equality);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}
	
	@RequestMapping(value = "/getGeneral", method = RequestMethod.POST)
	public ProposalGeneralDto getProposalGeneralInfo(@RequestBody String proposalNo) {

		try {
			return inquiryService.getProposalGeneralInfo(proposalNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getChildren", method = RequestMethod.POST)
	public List<ChildDto> getChildInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");
		
		try {
			return inquiryService.getChildrenList(dataArr[0], dataArr[1], dataArr[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getNominee", method = RequestMethod.POST)
	public List<NomineeInquiryDao> getNomineeInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");
		
		for (String string : dataArr) {
			System.out.println(string);
		}
		
		try {
			return inquiryService.getNomineeList(dataArr[0], dataArr[1], dataArr[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getBenefict", method = RequestMethod.POST)
	public List<BenefictInquiryDto> getBenefictInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");
		
		try {
			return inquiryService.getBenefictList(dataArr[0], dataArr[1], dataArr[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getMedQry", method = RequestMethod.POST)
	public List<MedicalReqDto> getMedicalReqInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");
		
		try {
			return inquiryService.getMedicalReqList(dataArr[0], dataArr[1], dataArr[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getTransferDetails", method = RequestMethod.POST)
	public List<TransferHistoryDto> getTransferHistoryInfo(@RequestBody String proposalNo) {

		try {
			return inquiryService.getTransferHistoryList(proposalNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getSettlementDetails", method = RequestMethod.POST)
	public List<SettlementDto> getSettlementInfo(@RequestBody String proposalNo) {

		try {
			return inquiryService.getSettlementList(proposalNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getPaymentHistory", method = RequestMethod.POST)
	public List<PaymentHistoryDto> getPaymentInfo(@RequestBody String data) {

		String dataArr[] = data.split("&");
		try {
			return inquiryService.getPaymentHistory(dataArr[0], dataArr[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/getPolicyDisAch", method = RequestMethod.POST)
	public PolicyDispatchAcknowDto getPolicyAch(@RequestBody String policyNo) {

		try {
			return inquiryService.getPolicyDispatch(policyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
