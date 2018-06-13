package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;

public class PreviousPolicyRowMapper implements RowMapper<HashMap< String , Object>>{

	@Override
	public HashMap< String , Object> mapRow(ResultSet rs, int row) throws SQLException {
		HashMap< String , Object> detailsMap = new HashMap<>();
		
		detailsMap.put("sumAtRisk", rs.getInt("sumAtRisk"));
		detailsMap.put("custCode", rs.getString("custCode"));
		
		//Object[] details = new Object [2];
		//details[0]= rs.getInt("sumAtRisk");
		//details[1]= rs.getString("custCode");
		return detailsMap;
	}

}
