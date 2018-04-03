package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.BranchTargetSummaryDto;
import org.springframework.jdbc.core.RowMapper;

public class BranchTargetSummaryRowMapper implements RowMapper<BranchTargetSummaryDto>{

	@Override
	public BranchTargetSummaryDto mapRow(ResultSet rst, int row) throws SQLException {
		
		BranchTargetSummaryDto target=new BranchTargetSummaryDto();
		
		if(rst!=null) {
			target.setYear(rst.getString("year"));
			target.setLocationCode(rst.getString("loccod"));
			target.setRegionCode(rst.getString("rgncod"));
			target.setZoneCode(rst.getString("zoncod"));
			target.setPara(rst.getString("para"));
			target.setLocationName(rst.getString("loc_name"));
			target.setJanCommitment(rst.getDouble("jan"));
			target.setFebCommitment(rst.getDouble("feb"));
			target.setMarCommitment(rst.getDouble("mar"));
			target.setAprCommitment(rst.getDouble("apr"));
			target.setMayCommitment(rst.getDouble("may"));
			target.setJunCommitment(rst.getDouble("jun"));
			target.setJulCommitment(rst.getDouble("jul"));
			target.setAugCommitment(rst.getDouble("aug"));
			target.setSepCommitment(rst.getDouble("sep"));
			target.setOctCommitment(rst.getDouble("oct"));
			target.setNovCommitment(rst.getDouble("nov"));
			target.setDecCommitment(rst.getDouble("dec"));
			
		}else {
			System.out.println("nullllllllllllllllllllllllllll");
		}
		
		return target;
	}

}
