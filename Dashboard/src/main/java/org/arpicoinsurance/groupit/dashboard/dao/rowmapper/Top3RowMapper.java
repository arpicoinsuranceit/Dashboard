package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.Top3;
import org.springframework.jdbc.core.RowMapper;

public class Top3RowMapper implements RowMapper<Top3>{

	@Override
	public Top3 mapRow(ResultSet rs, int rowNum) throws SQLException {
		Top3 top3 = new Top3();
		top3.setCode(rs.getString("t3code"));
		top3.setName(rs.getString("t3name"));
		return top3;
	}

}
