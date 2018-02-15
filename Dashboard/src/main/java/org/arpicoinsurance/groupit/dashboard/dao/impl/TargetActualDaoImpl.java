package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;
import org.arpicoinsurance.groupit.dashboard.dao.TargetActualDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.AgentAchievementRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.AgentAchievement;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TargetActualDaoImpl implements TargetActualDao {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public List<AgentAchievement> getAgentAchievements(DashboardPara para, Integer year) throws Exception {
		if(para.getDashtype().equalsIgnoreCase("IC")){
			return jdbcTemplate.query("SELECT * FROM inagentachievements where sbucod=? and agncod=? and yeronl=? order by mononl ", new Object[] { "450",Integer.parseInt(para.getDashpara()), year}, new AgentAchievementRowMapper());
		} else if (para.getDashtype().equalsIgnoreCase("UNL")) {
			return jdbcTemplate.query("SELECT * FROM inagentachievements where sbucod=? and agncod=? and yeronl=? order by mononl ", new Object[] { "450",Integer.parseInt(para.getDashpara()), year}, new AgentAchievementRowMapper());
		} else if (para.getDashtype().equalsIgnoreCase("BRANCH")) {
			
		}
		return null;
	}

	@Override
	public AgentAchievement getAgentAchievement(DashboardPara para, Integer month, Integer year) throws Exception {
		if(para.getDashtype().equalsIgnoreCase("IC")){
			return jdbcTemplate.queryForObject("SELECT * FROM inagentachievements where sbucod=? and agncod=? and mononl=? and yeronl=? ", new Object[] { "450",Integer.parseInt(para.getDashpara()), month, year}, new AgentAchievementRowMapper());
		} else if (para.getDashtype().equalsIgnoreCase("UNL")) {
			return jdbcTemplate.queryForObject("SELECT * FROM inagentachievements where sbucod=? and agncod=? and mononl=? and yeronl=? ", new Object[] { "450",Integer.parseInt(para.getDashpara()), month, year}, new AgentAchievementRowMapper());
		}
		return null;
		
	}

	@Override
	public AgentAchievement getCurrentMonthICAchievement(DashboardPara para) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgentAchievement getCurrentMonthUNLAchievement(DashboardPara para) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
