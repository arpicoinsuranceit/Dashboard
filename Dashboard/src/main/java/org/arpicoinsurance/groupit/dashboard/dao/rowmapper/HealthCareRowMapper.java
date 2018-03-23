package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.HelthCare;
import org.springframework.jdbc.core.RowMapper;

public class HealthCareRowMapper implements RowMapper<HelthCare>{

	@Override
	public HelthCare mapRow(ResultSet rs, int rowNum) throws SQLException {
		HelthCare care = new HelthCare();
		
		care.setCadsdt(rs.getString("cadsdt"));
		care.setRemark(rs.getString("remark"));
		
		return care;
	}

}
