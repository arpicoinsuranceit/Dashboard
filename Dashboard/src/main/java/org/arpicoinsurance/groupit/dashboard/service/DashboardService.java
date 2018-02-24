package org.arpicoinsurance.groupit.dashboard.service;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.Top3;

public interface DashboardService {
	MainRespDto getDashboard(String id) throws Exception;
	
	DashboardPara getDashboardPara(String userid) throws Exception;
	
	List<Object> getCurrentMonthYearlyTarget(String userid, String dashpara, String usertype) throws Exception;
	
	List<Object> getCurrentMonthYearlyTargetUNL(String userid, String dashpara, String usertype) throws Exception;
	
	List<DuePolicies> getDuePolicies(String userid, String dashpara, String usertype) throws Exception;
	
	List<PendingPolicies> getPendingPolicies(String userid, String dashpara, String usertype) throws Exception;
	
	List<Top3> getTopIC() throws Exception;
	
	List<Top3> getTopIS() throws Exception;
	
	List<Top3> getTopUL() throws Exception;
	
	List<Top3> getTopBranch() throws Exception;
	
	List<Top3> getTopRegion() throws Exception;
	
	List<Top3> getTopZone() throws Exception;
	
}
