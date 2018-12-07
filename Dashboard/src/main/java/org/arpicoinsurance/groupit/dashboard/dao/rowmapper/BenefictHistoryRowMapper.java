package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.BenefictHistory;
import org.springframework.jdbc.core.RowMapper;

public class BenefictHistoryRowMapper implements RowMapper<BenefictHistory>{

	@Override
	public BenefictHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		BenefictHistory benefictHistory = new BenefictHistory();
		
		benefictHistory.setPrdcod(rs.getString("prdcod"));
		benefictHistory.setPprnum(rs.getString("pprnum"));
		benefictHistory.setTotPremium(rs.getDouble("totprm"));
		benefictHistory.setRiderCode(rs.getString("ridcod"));
		benefictHistory.setSumAssuredTot(rs.getDouble("sumasu"));
		benefictHistory.setPremiumTot(rs.getDouble("rdrprm"));
		benefictHistory.setType(rs.getString("type"));
		benefictHistory.setFrequance(rs.getString("frequance"));
		return benefictHistory;
	}

}
