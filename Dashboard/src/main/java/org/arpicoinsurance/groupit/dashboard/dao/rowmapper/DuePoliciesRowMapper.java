package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.springframework.jdbc.core.RowMapper;

public class DuePoliciesRowMapper implements RowMapper<DuePolicies> {

	@Override
	public DuePolicies mapRow(ResultSet rs, int rowNum) throws SQLException {
		DuePolicies duePolicies = new DuePolicies();
		duePolicies.setBranch(rs.getString("loccod"));
		duePolicies.setPolnum(rs.getInt("polnum"));
		duePolicies.setPprsta(rs.getString("stadsc"));
		duePolicies.setCustName(rs.getString("ppdini"));
		duePolicies.setCustMobile(rs.getString("ppdmob"));
		duePolicies.setPremium(rs.getDouble("premium"));
		duePolicies.setNofarias(rs.getInt("nofarias"));
		duePolicies.setTotarias(rs.getDouble("totarias"));
		return duePolicies;
	}

}
