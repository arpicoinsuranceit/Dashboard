package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.InproposalsDao;
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
import org.arpicoinsurance.groupit.dashboard.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InquiryServiceImpl implements InquiryService {

	@Autowired
	private InproposalsDao inproposalDao;

	@Override
	public List<InquiryLoad> inquiryLoad(String userType, String dashpara, String advCode, int offset, int limit)
			throws Exception {

		switch (userType) {
		case "IC":
			return inproposalDao.getInquiriesIC(advCode, offset, limit, "");
		case "UNL":
			return inproposalDao.getInquiriesUNL(advCode, dashpara, offset, limit, "");
		case "BRANCH":
			return inproposalDao.getInquiriesBranch(dashpara, offset, limit, "");
		case "REGION":
			return inproposalDao.getInquiriesRegion(dashpara, offset, limit, "");
		case "ZONE":
			return inproposalDao.getInquiriesZone(dashpara, offset, limit, "");
		case "HO":
			return inproposalDao.getInquiriesHo(dashpara, offset, limit, "");
		default:
			break;
		}

		return null;
	}

	@Override
	public List<InquiryLoad> inquiryLoadFilterd(String userType, String dashpara, String advCode, int offset, int limit,
			String column, String data, String equality) throws Exception {

		String sqlData = "";

		if (equality.equals("equal")) {
			sqlData = "and p." + column + " = " + data;
		} else if (equality.equals("with")) {
			sqlData = "and p." + column + " LIKE '%" + data + "%'";
		}

		switch (userType) {
		case "IC":
			return inproposalDao.getInquiriesIC(advCode, offset, limit, sqlData);
		case "UNL":
			return inproposalDao.getInquiriesUNL(advCode, dashpara, offset, limit, sqlData);
		case "BRANCH":
			return inproposalDao.getInquiriesBranch(dashpara, offset, limit, sqlData);
		case "REGION":
			return inproposalDao.getInquiriesRegion(dashpara, offset, limit, sqlData);
		case "ZONE":
			return inproposalDao.getInquiriesZone(dashpara, offset, limit, sqlData);
		case "HO":
			return inproposalDao.getInquiriesHo(dashpara, offset, limit, sqlData);
		default:
			break;
		}

		return null;
	}

	@Override
	public Integer inquiryLoadCount(String userType, String dashpara, String advCode) throws Exception {
		switch (userType) {
		case "IC":
			return inproposalDao.getInquiryICCount(advCode, "");
		case "UNL":
			return inproposalDao.getInquiryUNLCount(advCode, dashpara, "");
		case "BRANCH":
			return inproposalDao.getInquiryBranchCount(dashpara, "");
		case "REGION":
			return inproposalDao.getInquiryRegionCount(dashpara, "");
		case "ZONE":
			return inproposalDao.getInquiryZoneCount(dashpara, "");
		case "HO":
			return inproposalDao.getInquiriesHoCount(dashpara, "");
		default:
			break;
		}

		return null;
	}

	@Override
	public Integer inquiryLoadCountFilterd(String userType, String dashpara, String advCode, String column, String data,
			String equality) throws Exception {
		String sqlData = "";

		if (equality.equals("equal")) {
			sqlData = "and p." + column + " = " + data;
		} else if (equality.equals("with")) {
			sqlData = "and p." + column + " LIKE '%" + data + "%'";
		}

		switch (userType) {
		case "IC":
			return inproposalDao.getInquiryICCount(advCode, sqlData);
		case "UNL":
			return inproposalDao.getInquiryUNLCount(advCode, dashpara, sqlData);
		case "BRANCH":
			return inproposalDao.getInquiryBranchCount(dashpara, sqlData);
		case "REGION":
			return inproposalDao.getInquiryRegionCount(dashpara, sqlData);
		case "ZONE":
			return inproposalDao.getInquiryZoneCount(dashpara, sqlData);
		case "HO":
			return inproposalDao.getInquiriesHoCount(dashpara, sqlData);
		default:
			break;
		}

		return null;
	}

	@Override
	public ProposalGeneralDto getProposalGeneralInfo(String proposalNo) throws Exception {

		return inproposalDao.getProposalGeneralDetails(proposalNo);
	}

	@Override
	public List<ChildDto> getChildrenList(String proposalNo, String branchCode, String seqNo) throws Exception {

		return inproposalDao.getChildrenDetails(proposalNo, branchCode, Integer.parseInt(seqNo));
	}

	@Override
	public List<NomineeInquiryDao> getNomineeList(String proposalNo, String branchCode, String seqNo) throws Exception {
		return inproposalDao.getNomineeDetails(proposalNo, branchCode, Integer.parseInt(seqNo));
	}

	@Override
	public List<BenefictInquiryDto> getBenefictList(String proposalNo, String branchCode, String seqNo)
			throws Exception {

		return inproposalDao.getBenefictDetails(proposalNo, branchCode, Integer.parseInt(seqNo));
	}

	@Override
	public List<MedicalReqDto> getMedicalReqList(String proposalNo, String branchCode, String seqNo) throws Exception {

		return inproposalDao.getMedicalReqDetails(proposalNo, branchCode, Integer.parseInt(seqNo));
	}

	@Override
	public List<TransferHistoryDto> getTransferHistoryList(String proposalNo) throws Exception {
		return inproposalDao.getTransferHistoryDetails(proposalNo);
	}

	@Override
	public List<SettlementDto> getSettlementList(String proposalNo) throws Exception {
		return inproposalDao.getSettlementDetails(proposalNo);
	}

	@Override
	public List<PaymentHistoryDto> getPaymentHistory(String policyNo, String branchCode) throws Exception {

		return inproposalDao.getPaymentHistoryDetails(policyNo, branchCode);
	}

	@Override
	public PolicyDispatchAcknowDto getPolicyDispatch(String policyNo) throws Exception {

		return inproposalDao.getPolicyDispatch(policyNo);
	}

}
