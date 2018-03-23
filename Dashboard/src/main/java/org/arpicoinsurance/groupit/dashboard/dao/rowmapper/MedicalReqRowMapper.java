package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.MedicalReqDto;
import org.springframework.jdbc.core.RowMapper;

public class MedicalReqRowMapper implements RowMapper<MedicalReqDto>{

	@Override
	public MedicalReqDto mapRow(ResultSet rs, int row) throws SQLException {
		
		MedicalReqDto medicalReqDto = new MedicalReqDto();

		medicalReqDto.setTestCode(rs.getString("testCode"));
		medicalReqDto.setTestName(rs.getString("testName"));
		medicalReqDto.setOrigin(rs.getString("origin"));
		medicalReqDto.setRecived(rs.getString("recived"));
		medicalReqDto.setHospital(rs.getString("hospital"));
		medicalReqDto.setTestDate(rs.getString("testDate"));
		medicalReqDto.setPayAmount(rs.getDouble("amount"));
		medicalReqDto.setPayStatus(rs.getString("payStatus"));
		medicalReqDto.setAdditionalNotes(rs.getString("additionalNote"));
		medicalReqDto.setType(rs.getString("type"));

		return medicalReqDto;
	}

}
