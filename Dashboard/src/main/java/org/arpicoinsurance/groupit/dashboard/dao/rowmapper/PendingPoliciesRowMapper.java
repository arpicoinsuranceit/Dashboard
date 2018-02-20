package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.springframework.jdbc.core.RowMapper;

public class PendingPoliciesRowMapper implements RowMapper<PendingPolicies> {

	@Override
	public PendingPolicies mapRow(ResultSet rs, int rowNum) throws SQLException {
		PendingPolicies pendingPolicies = new PendingPolicies();
		pendingPolicies.setBranch(rs.getString("loccod"));
		pendingPolicies.setProposalNo(rs.getInt("pprnum"));
		pendingPolicies.setAgentCode(rs.getInt("agncod"));
		pendingPolicies.setCustName(rs.getString("ppdini"));
		pendingPolicies.setPremium(rs.getDouble("totprm"));
		pendingPolicies.setRequirment(rs.getString("requirment"));
		return pendingPolicies;
	}

}
