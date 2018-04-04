package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDetailsDto;
import org.springframework.jdbc.core.RowMapper;

public class OrgChartDetailsRowMapper implements RowMapper<OrgChartDetailsDto>{

	@Override
	public OrgChartDetailsDto mapRow(ResultSet rs, int row) throws SQLException {
		OrgChartDetailsDto chartDetailsDto = new OrgChartDetailsDto();
		
		chartDetailsDto.setAgncod(rs.getString("agncod"));
		chartDetailsDto.setAgncls(rs.getString("agncls"));
		chartDetailsDto.setLoc_code(rs.getString("loc_code"));
		chartDetailsDto.setLoc_name(rs.getString("loc_name"));
		chartDetailsDto.setRgncod(rs.getString("rgncod"));
		chartDetailsDto.setShrtnm(rs.getString("shrtnm"));
		chartDetailsDto.setSubdcd(rs.getString("subdcd"));
		chartDetailsDto.setSubdes(rs.getString("subdes"));
		chartDetailsDto.setUnlcod(rs.getString("unlcod"));
		chartDetailsDto.setZoncod(rs.getString("zoncod"));
		
		return chartDetailsDto;
	}

}
