package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.TargetCommitmentActual;

public interface TargetCommitmentActualDao {
	
	List<TargetCommitmentActual> getCurrentYearGWP(DashboardPara para) throws Exception;
	
	List<TargetCommitmentActual> getCurrentYearNOP(DashboardPara para) throws Exception;
	
	List<TargetCommitmentActual> getCurrentYearMCFP(DashboardPara para) throws Exception;
	
	List<TargetCommitmentActual> getCurrentYearFYPAch(DashboardPara para) throws Exception;
	
	List<TargetCommitmentActual> getCurrentYearRTNY1(DashboardPara para) throws Exception;

}
