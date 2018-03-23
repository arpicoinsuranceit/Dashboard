package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.NomineeInquiryDao;
import org.springframework.jdbc.core.RowMapper;

public class NomineeRowMapper implements RowMapper<NomineeInquiryDao>{

	@Override
	public NomineeInquiryDao mapRow(ResultSet rs, int row) throws SQLException {
		NomineeInquiryDao nomineeInquiryDao = new NomineeInquiryDao(); 
		
		nomineeInquiryDao.setName(rs.getString("name"));
		nomineeInquiryDao.setRelation(rs.getString("relation"));
		nomineeInquiryDao.setNic(rs.getString("nic"));
		nomineeInquiryDao.setDob(rs.getString("dob"));
		nomineeInquiryDao.setShared(rs.getString("shared"));
		nomineeInquiryDao.setgName(rs.getString("gname"));
		nomineeInquiryDao.setgNic(rs.getString("gnic"));
		nomineeInquiryDao.setgDob(rs.getString("gdob"));
		nomineeInquiryDao.setgRelation(rs.getString("grelation"));
		
		return nomineeInquiryDao;
	}

}
