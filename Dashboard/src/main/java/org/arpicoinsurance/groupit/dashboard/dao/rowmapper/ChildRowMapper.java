package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.ChildDto;
import org.springframework.jdbc.core.RowMapper;

public class ChildRowMapper implements RowMapper<ChildDto>{

	@Override
	public ChildDto mapRow(ResultSet rs, int row) throws SQLException {
		
		ChildDto childDto = new ChildDto();
		
		childDto.setName(rs.getString("name"));
		childDto.setRelation(rs.getString("relation"));
		childDto.setDob(rs.getString("dob"));
		childDto.setAge(rs.getInt("age"));	
		childDto.setSex(rs.getString("sex"));
		childDto.setCibc(rs.getString("cibc"));
		childDto.setHbc(rs.getString("hbc"));
		childDto.setHrbc(rs.getString("hrbc"));
		childDto.setSuhrbc(rs.getString("suhrbc"));
		
		return childDto;
	}

}
