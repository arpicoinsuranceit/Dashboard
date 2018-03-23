package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.PolicyDispatch;
import org.springframework.jdbc.core.RowMapper;

public class PilicyDispatchRowmapper implements RowMapper<PolicyDispatch>{

	@Override
	public PolicyDispatch mapRow(ResultSet rs, int rowNum) throws SQLException {
		PolicyDispatch policyDispatch = new PolicyDispatch();
		
		policyDispatch.setAckdat(rs.getString("dspdat"));
		policyDispatch.setAgncod(rs.getString("agncod"));
		policyDispatch.setAgnnam(rs.getString("agnnam"));
		policyDispatch.setCusdat(rs.getString("ackdat"));
		policyDispatch.setDspdat(rs.getString("cusdat"));
		
		return policyDispatch;
	}

}
