package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;

public class CommisionRateRowMapper implements RowMapper<HashMap< String , Double>> {

	@Override
	public HashMap<String, Double> mapRow(ResultSet rs, int rowNum) throws SQLException {
		HashMap< String , Double> commisionRateMap = new HashMap<>();
		
		commisionRateMap.put("comper", rs.getDouble("comper"));
		commisionRateMap.put("comsin", rs.getDouble("comsin"));
		commisionRateMap.put("combrk", rs.getDouble("combrk"));
		commisionRateMap.put("combrs", rs.getDouble("combrs"));
		return commisionRateMap;
	}

}
