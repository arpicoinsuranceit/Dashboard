package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpicoinsurance.groupit.dashboard.common.CalculationUtils;
import org.arpicoinsurance.groupit.dashboard.dao.BranchTargetSummaryDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.BranchTargetSummaryRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.BranchTargetSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BranchTargetSummaryDaoImpl implements BranchTargetSummaryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<BranchTargetSummaryDto> getBranchTargetSummaryZonalM(String year, String zoneCode) throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(year);
		args.add(zoneCode);

		return jdbcTemplate.query("select i.*,l.loc_name from inbranchtargetsummary i " + 
				"inner join rms_locations l where i.sbucod='450' " + 
				"and i.year=? and i.zoncod=? and i.type='Commitment' and l.loc_code=i.loccod " + 
				"order by i.loccod,i.disord,i.disort",
				args.toArray(), new BranchTargetSummaryRowMapper());
	}

	@Override
	public String getZoneCode(String loccode) throws Exception {
		List<Object> args = new ArrayList<>();
		CalculationUtils calculationUtils = new CalculationUtils();
		args.add(calculationUtils.getPara(loccode));
		calculationUtils = null;
		
		String zoncod= jdbcTemplate.queryForObject("select r.zoncod from inagentmast a " + 
				"inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code " + 
				"inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod " + 
				"where a.sbucod='450' and a.loccod IN(?) and a.agnsta='ACT' and a.agncls='BRN' limit 1",
				String.class,args.toArray());
		
		//System.out.println(zoncod + " ----- ====");
		return zoncod;
		
		
	}

	@Override
	public Integer updateCommitment(BranchTargetSummaryDto branchTargetSummaryDto) throws Exception {
		
		List<Object> args = new ArrayList<>();
		args.add(branchTargetSummaryDto.getJanCommitment());
		args.add(branchTargetSummaryDto.getFebCommitment());
		args.add(branchTargetSummaryDto.getMarCommitment());
		args.add(branchTargetSummaryDto.getAprCommitment());
		args.add(branchTargetSummaryDto.getMayCommitment());
		args.add(branchTargetSummaryDto.getJunCommitment());
		args.add(branchTargetSummaryDto.getJulCommitment());
		args.add(branchTargetSummaryDto.getAugCommitment());
		args.add(branchTargetSummaryDto.getSepCommitment());
		args.add(branchTargetSummaryDto.getOctCommitment());
		args.add(branchTargetSummaryDto.getNovCommitment());
		args.add(branchTargetSummaryDto.getDecCommitment());
		
		args.add(branchTargetSummaryDto.getZoneCode());
		args.add(branchTargetSummaryDto.getRegionCode());
		args.add(branchTargetSummaryDto.getLocationCode());
		args.add(branchTargetSummaryDto.getYear());
		args.add(branchTargetSummaryDto.getPara());
		
		Integer result= jdbcTemplate.update("UPDATE inbranchtargetsummary SET"
				+ " jan=?, feb=?, mar=?, apr=?, may=?, jun=?," + 
				"jul=?, aug=?, sep=?, oct=?, nov=?, inbranchtargetsummary.dec=? " + 
				"WHERE zoncod=? and rgncod=? and loccod=? and year=? " + 
				"and para=? and type='Commitment' and sbucod='450'",
				branchTargetSummaryDto.getJanCommitment(),branchTargetSummaryDto.getFebCommitment(),branchTargetSummaryDto.getMarCommitment()
				,branchTargetSummaryDto.getAprCommitment(),branchTargetSummaryDto.getMayCommitment(),branchTargetSummaryDto.getJunCommitment()
				,branchTargetSummaryDto.getJulCommitment(),branchTargetSummaryDto.getAugCommitment()
				,branchTargetSummaryDto.getSepCommitment(),branchTargetSummaryDto.getOctCommitment(),branchTargetSummaryDto.getNovCommitment()
				,branchTargetSummaryDto.getDecCommitment(),branchTargetSummaryDto.getZoneCode(),branchTargetSummaryDto.getRegionCode(),
				branchTargetSummaryDto.getLocationCode(),branchTargetSummaryDto.getYear(),branchTargetSummaryDto.getPara());
		
		//System.out.println(result + " ----- ====");
		return result;
	}

	@Override
	public List<BranchTargetSummaryDto> getBranchTargetSummaryBranchM(String year, String locCode) throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(year);
		args.add(locCode);
		
		return jdbcTemplate.query("select i.*,l.loc_name from inbranchtargetsummary i " + 
				"inner join rms_locations l where i.sbucod='450' " + 
				"and i.year=? and i.loccod=? and i.type='Commitment' and l.loc_code=i.loccod " + 
				"order by i.loccod,i.disord,i.disort",
				args.toArray(), new BranchTargetSummaryRowMapper());
	}

}
