package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.AgentAchievement;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;

public interface TargetActualDao {
	
	List<AgentAchievement> getAgentAchievements(DashboardPara para)  throws Exception;
	
	AgentAchievement getAgentAchievement(DashboardPara para)  throws Exception;
	
	AgentAchievement getCurrentMonthICAchievement(DashboardPara para) throws Exception;
	
	AgentAchievement getCurrentMonthUNLAchievement(DashboardPara para) throws Exception;

}
