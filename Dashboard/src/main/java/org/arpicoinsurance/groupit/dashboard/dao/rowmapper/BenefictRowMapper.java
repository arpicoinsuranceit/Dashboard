package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.BenefictInquiryDto;
import org.springframework.jdbc.core.RowMapper;

public class BenefictRowMapper implements RowMapper<BenefictInquiryDto> {

	@Override
	public BenefictInquiryDto mapRow(ResultSet rs, int row) throws SQLException {
		BenefictInquiryDto benefictInquiryDto = new BenefictInquiryDto();
		
		benefictInquiryDto.setRiderCode(rs.getString("riderCode"));
		benefictInquiryDto.setRiderName(rs.getString("riderName"));
		benefictInquiryDto.setTerm(rs.getInt("term"));
		benefictInquiryDto.setSumAssured(rs.getDouble("sumAssured"));
		benefictInquiryDto.setPremium(rs.getDouble("premium"));
		benefictInquiryDto.setType(rs.getString("benType"));
		
		return benefictInquiryDto;
	}

}
