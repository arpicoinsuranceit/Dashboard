package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.arpicoinsurance.groupit.dashboard.dao.BranchTargetSummaryDao;
import org.arpicoinsurance.groupit.dashboard.dto.BranchTargetSummaryDto;
import org.arpicoinsurance.groupit.dashboard.service.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommitmentServiceImpl implements CommitmentService{
	
	@Autowired
	private BranchTargetSummaryDao summaryDao;

	@Override
	public List<BranchTargetSummaryDto> getBranchTargetSummaryZonalM(String year, String zoneCode) throws Exception {
		return summaryDao.getBranchTargetSummaryZonalM(year, zoneCode);
	}

	@Override
	public List<BranchTargetSummaryDto> getBranchTargetSummaryBranchM(String year, String locCode) throws Exception {
		return summaryDao.getBranchTargetSummaryBranchM(year, locCode);
	}

	@Override
	public String getZoneCode(String agnCode) throws Exception {
		return summaryDao.getZoneCode(agnCode);
	}

	@Override
	public Integer updateCommitment(BranchTargetSummaryDto branchTargetSummaryDto) throws Exception {
		return summaryDao.updateCommitment(branchTargetSummaryDto);
	}

}
