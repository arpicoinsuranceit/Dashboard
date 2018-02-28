package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.PolicySummary;
import org.springframework.jdbc.core.RowMapper;

public class PolicySummaryRowMapper implements RowMapper<PolicySummary> {

	@Override
	public PolicySummary mapRow(ResultSet rs, int rowNum) throws SQLException {
		PolicySummary policySummary = new PolicySummary();
		policySummary.setPlisuCount(rs.getInt("plisucount"));
		policySummary.setPlisuAmount(rs.getDouble("plisuamount"));
		policySummary.setPlapsCount(rs.getInt("plapscount"));
		policySummary.setPlapsAmount(rs.getDouble("plapsamount"));
		policySummary.setPlappCount(rs.getInt("plappcount"));
		policySummary.setPlappAmount(rs.getDouble("plappamount"));
		return policySummary;
	}

}
