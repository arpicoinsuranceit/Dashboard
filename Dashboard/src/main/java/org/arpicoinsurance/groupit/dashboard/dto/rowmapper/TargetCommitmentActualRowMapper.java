package org.arpicoinsurance.groupit.dashboard.dto.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.TargetCommitmentActual;
import org.springframework.jdbc.core.RowMapper;

public class TargetCommitmentActualRowMapper implements RowMapper<TargetCommitmentActual> {

	@Override
	public TargetCommitmentActual mapRow(ResultSet rs, int rowNum) throws SQLException {
		TargetCommitmentActual targetCommitmentActual = new TargetCommitmentActual();
		targetCommitmentActual.setTarget(rs.getDouble("target"));
		targetCommitmentActual.setCommitment(rs.getDouble("commitment"));
		targetCommitmentActual.setActual(rs.getDouble("actual"));
		targetCommitmentActual.setMonth(rs.getInt("month"));
		targetCommitmentActual.setYear(rs.getInt("year"));
		return targetCommitmentActual;
	}

}
