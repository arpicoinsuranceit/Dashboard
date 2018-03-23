package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.SettlementDto;
import org.springframework.jdbc.core.RowMapper;

public class SettlementRowMapper implements RowMapper<SettlementDto>{

	@Override
	public SettlementDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		SettlementDto dto = new SettlementDto();
		
		dto.setBranch(rs.getString("loccod"));
		dto.setChqRel(rs.getString("chqrel"));
		dto.setDocCode(rs.getString("doccod"));
		dto.setDocnum(rs.getString("docnum"));
		dto.setName(rs.getString("ppdnam"));
		dto.setPayMode(rs.getString("paymod"));
		dto.setTotPremium(rs.getDouble("totprm"));
		
		return dto;
	}

}
