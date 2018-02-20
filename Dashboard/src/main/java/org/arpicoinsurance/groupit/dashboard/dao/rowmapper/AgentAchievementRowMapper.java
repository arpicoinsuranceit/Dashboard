package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.AgentAchievement;
import org.springframework.jdbc.core.RowMapper;

public class AgentAchievementRowMapper implements RowMapper<AgentAchievement> {

	@Override
	public AgentAchievement mapRow(ResultSet rs, int rownum) throws SQLException {
		AgentAchievement agentAchievement = new AgentAchievement();
		agentAchievement.setTrgamt(rs.getDouble("trgamt"));
		agentAchievement.setTrgach(rs.getDouble("trgaca"));
		agentAchievement.setMonth(rs.getInt("mononl"));
		agentAchievement.setYear(rs.getInt("yeronl"));
		return agentAchievement;
	}

}
