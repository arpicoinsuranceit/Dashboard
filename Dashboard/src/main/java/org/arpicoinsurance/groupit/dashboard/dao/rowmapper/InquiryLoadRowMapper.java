package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.InquiryLoad;
import org.springframework.jdbc.core.RowMapper;

public class InquiryLoadRowMapper implements RowMapper<InquiryLoad>{

	@Override
	public InquiryLoad mapRow(ResultSet rs, int rownum) throws SQLException {
		InquiryLoad inquiryLoad = new InquiryLoad();
		
		inquiryLoad.setProposalNo(rs.getString("pprnum"));
		inquiryLoad.setPolicyNo(rs.getString("polnum"));
		inquiryLoad.setMainLifeName(rs.getString("ppdnam"));
		inquiryLoad.setNic(rs.getString("ppdnic"));
		inquiryLoad.setProduct(rs.getString("prdcod"));
		inquiryLoad.setProposalStatus(rs.getString("pprsta"));
		inquiryLoad.setAdvisorCode(rs.getString("advcod"));
		
		return inquiryLoad;
	}

}
