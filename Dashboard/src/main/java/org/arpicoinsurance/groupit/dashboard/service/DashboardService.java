package org.arpicoinsurance.groupit.dashboard.service;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.dto.NameSeriasPair;
import org.arpicoinsurance.groupit.dashboard.dto.NameValuePair;
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
	
	List<Object> getGWPAndGWPC(String userid, String dashpara, String usertype) throws Exception;
	
	List<Object> getMCFPAndMCFPC(String userid, String dashpara, String usertype) throws Exception;
	
	List<Object> getFYPAndFYPC(String userid, String dashpara, String usertype) throws Exception;
	
	List<Object> getNOPAndNOPC(String userid, String dashpara, String usertype) throws Exception;
	
	List<NameSeriasPair> getRINY(String userid, String dashpara, String usertype) throws Exception; 
	
	List<NameValuePair> getPolicySummery(String userid, String dashpara, String usertype) throws Exception;
	
}
