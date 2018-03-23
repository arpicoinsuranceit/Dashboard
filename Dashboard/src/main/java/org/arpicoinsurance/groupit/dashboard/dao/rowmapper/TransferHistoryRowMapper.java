package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.TransferHistoryDto;
import org.springframework.jdbc.core.RowMapper;

public class TransferHistoryRowMapper implements RowMapper<TransferHistoryDto>{

	@Override
	public TransferHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TransferHistoryDto transferHistoryDto = new TransferHistoryDto();
		
		transferHistoryDto.setAgentCode(rs.getString("advcod"));
		transferHistoryDto.setName(rs.getString("shrtnm"));
		transferHistoryDto.setAgentClass(rs.getString("agncls"));
		transferHistoryDto.setFromDate(rs.getString("frmdat"));
		transferHistoryDto.setToDate(rs.getString("todate"));
		
		return transferHistoryDto;
	}
	
	

}
