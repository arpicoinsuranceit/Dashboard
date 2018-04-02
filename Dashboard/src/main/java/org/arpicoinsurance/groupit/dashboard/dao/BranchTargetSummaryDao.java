package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;
import org.arpicoinsurance.groupit.dashboard.dto.BranchTargetSummaryDto;

public interface BranchTargetSummaryDao {
	
	public List<BranchTargetSummaryDto> getBranchTargetSummaryZonalM(String year, String zoneCode) throws Exception;

	public List<BranchTargetSummaryDto> getBranchTargetSummaryBranchM(String year, String locCode) throws Exception;
	
	public String getZoneCode(String agnCode)throws Exception;
	
	public Integer updateCommitment(BranchTargetSummaryDto branchTargetSummaryDto)throws Exception;
}
