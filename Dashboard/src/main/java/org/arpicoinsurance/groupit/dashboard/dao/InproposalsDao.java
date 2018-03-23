package org.arpicoinsurance.groupit.dashboard.dao;

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

public interface InproposalsDao {

	//////////////// IC ///////////////////////
	List<InquiryLoad> getInquiriesIC(String advCode, Integer offset, Integer limit, String data) throws Exception;

	Integer getInquiryICCount(String advCode, String data) throws Exception;

	//////////////// UNL ///////////////////////
	List<InquiryLoad> getInquiriesUNL(String advCode, String unlCod, Integer offset, Integer limit, String data)
			throws Exception;

	Integer getInquiryUNLCount(String advCode, String unlCod, String data) throws Exception;

	//////////////// Branch ///////////////////////
	List<InquiryLoad> getInquiriesBranch(String branchCode, Integer offset, Integer limit, String data)
			throws Exception;

	Integer getInquiryBranchCount(String branchCode, String data) throws Exception;

	//////////////// Region ///////////////////////
	List<InquiryLoad> getInquiriesRegion(String regionCode, Integer offset, Integer limit, String data)
			throws Exception;

	Integer getInquiryRegionCount(String regionCode, String data) throws Exception;

	//////////////// Zone ///////////////////////
	List<InquiryLoad> getInquiriesZone(String zoneCode, Integer offset, Integer limit, String data) throws Exception;

	Integer getInquiryZoneCount(String zoneCode, String data) throws Exception;

	ProposalGeneralDto getProposalGeneralDetails(String proposalNo) throws Exception;

	List<ChildDto> getChildrenDetails(String proposalNo, String branchCode, Integer seqNo) throws Exception;

	List<NomineeInquiryDao> getNomineeDetails(String proposalNo, String branchCode, Integer seqNo) throws Exception;

	List<BenefictInquiryDto> getBenefictDetails(String proposalNo, String branchCode, Integer seqNo) throws Exception;

	List<MedicalReqDto> getMedicalReqDetails(String proposalNo, String branchCode, Integer seqNo) throws Exception;
	
	List<TransferHistoryDto> getTransferHistoryDetails(String proposalNo) throws Exception;
	
	List<SettlementDto> getSettlementDetails(String proposalNo) throws Exception;
	
	List<PaymentHistoryDto> getPaymentHistoryDetails(String proposalNo, String branchCode) throws Exception;

	PolicyDispatchAcknowDto getPolicyDispatch (String policyNo) throws Exception;
}
