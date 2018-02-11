package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.TargetCommitmentActual;

public interface TargetCommitmentActualDao {
	
	List<TargetCommitmentActual> getCurrentYearNOP(DashboardPara para) throws Exception;

}
