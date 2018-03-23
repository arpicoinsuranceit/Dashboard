package org.arpicoinsurance.groupit.dashboard.service;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.BenefictInquiryDto;
import org.arpicoinsurance.groupit.dashboard.dto.ChildDto;
import org.arpicoinsurance.groupit.dashboard.dto.InquiryLoad;
import org.arpicoinsurance.groupit.dashboard.dto.MedicalReqDto;
import org.arpicoinsurance.groupit.dashboard.dto.NomineeInquiryDao;
import org.arpicoinsurance.groupit.dashboard.dto.PaymentHistoryDto;
import org.arpicoinsurance.groupit.dashboard.dto.PolicyDispatchAcknowDto;
import org.arpicoinsurance.groupit.dashboard.dto.ProposalGeneralDto;
import org.arpicoinsurance.groupit.dashboard.dto.SettlementDto;
import org.arpicoinsurance.groupit.dashboard.dto.TransferHistoryDto;

public interface InquiryService {

	/////////////// load List/////////////////////////

	List<InquiryLoad> inquiryLoad(String userType, String dashpara, String advCode, int offset, int limit)
			throws Exception;

	List<InquiryLoad> inquiryLoadFilterd(String userType, String dashpara, String advCode, int offset, int limit,
			String column, String data, String equality) throws Exception;

	/////////////// load count/////////////////////////

	Integer inquiryLoadCount(String userType, String dashpara, String advCode) throws Exception;

	Integer inquiryLoadCountFilterd(String userType, String dashpara, String advCode, String column, String data,
			String equality) throws Exception;

	ProposalGeneralDto getProposalGeneralInfo(String proposalNo) throws Exception;
	
	List<ChildDto> getChildrenList (String proposalNo, String branchCode, String seqNo)  throws Exception;
	
	List<NomineeInquiryDao> getNomineeList (String proposalNo, String branchCode, String seqNo) throws Exception;
	
	List<BenefictInquiryDto> getBenefictList (String proposalNo, String branchCode, String seqNo) throws Exception;
	
	List<MedicalReqDto> getMedicalReqList (String proposalNo, String branchCode, String seqNo) throws Exception;
	
	List<TransferHistoryDto> getTransferHistoryList (String proposalNo) throws Exception;
	
	List<SettlementDto> getSettlementList (String proposalNo) throws Exception;
	
	List<PaymentHistoryDto> getPaymentHistory (String policyNo,  String branchCode) throws Exception;
	
	PolicyDispatchAcknowDto getPolicyDispatch(String policyNo) throws Exception;
}
