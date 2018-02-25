package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.UNLAchievement;
import org.springframework.jdbc.core.RowMapper;

public class UNLAchievementRowMapper implements RowMapper<UNLAchievement> {

	@Override
	public UNLAchievement mapRow(ResultSet rs, int rowNum) throws SQLException {
		UNLAchievement unlAchievement = new UNLAchievement();
		unlAchievement.setTrgamt(rs.getDouble("trgamt"));
		unlAchievement.setTrgach(rs.getDouble("trgaca"));
		unlAchievement.setTrgtcfa(rs.getDouble("trgtcfa"));
		unlAchievement.setMonth(rs.getInt("mononl"));
		unlAchievement.setYear(rs.getInt("yeronl"));
		return unlAchievement;
	}

}
