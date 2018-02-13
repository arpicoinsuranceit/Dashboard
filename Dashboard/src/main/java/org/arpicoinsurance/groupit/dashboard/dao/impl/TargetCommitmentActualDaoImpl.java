package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.TargetCommitmentActualDao;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.TargetCommitmentActual;
import org.arpicoinsurance.groupit.dashboard.dto.rowmapper.TargetCommitmentActualRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TargetCommitmentActualDaoImpl implements TargetCommitmentActualDao {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TargetCommitmentActual> getCurrentYearNOP(DashboardPara para) throws Exception {
		if(para.getDashtype().equalsIgnoreCase("IC")){
			
		} else if (para.getDashtype().equalsIgnoreCase("UNL")) {
			
		} else if (para.getDashtype().equalsIgnoreCase("BRANCH") || para.getDashtype().equalsIgnoreCase("REGION") || para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			String colpara = "loccod";
			if(para.getDashtype().equalsIgnoreCase("BRANCH")){
				colpara = "loccod";
			} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
				colpara = "rgncod";
			} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
				colpara = "zoncod"; 
			}
			
			String sql = "SELECT  sum(x.target) target, sum(x.commitment) commitment, sum(x.actual) actual, x.month, x.year FROM ( "
                    + "SELECT max(if(type='Target',jan,0.0)) target,max(if(type='Commitment',jan,0.0)) commitment,max(if(type='Actual',jan,0.0)) actual,1 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb),0.0)) target,max(if(type='Commitment',(jan+feb),0.0)) commitment,max(if(type='Actual',(jan+feb),0.0)) actual,2 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar),0.0)) target,max(if(type='Commitment',(jan+feb+mar),0.0)) commitment,max(if(type='Actual',(jan+feb+mar),0.0)) actual,3 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr),0.0)) actual,4 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may),0.0)) actual,5 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun),0.0)) actual,6 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul),0.0)) actual,7 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) actual,8 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) actual,9 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) actual,10 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) actual,11 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) actual,12 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='NOP' group by loccod "
                    + ") x group by year,month order by year,month ";
			
			return jdbcTemplate.query(sql, new TargetCommitmentActualRowMapper());
			
		}	
			
		return null;
	}

	@Override
	public List<TargetCommitmentActual> getCurrentYearGWP(DashboardPara para) throws Exception {
		if(para.getDashtype().equalsIgnoreCase("IC")){
			
		} else if (para.getDashtype().equalsIgnoreCase("UNL")) {
			
		} else if (para.getDashtype().equalsIgnoreCase("BRANCH") || para.getDashtype().equalsIgnoreCase("REGION") || para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			String colpara = "loccod";
			if(para.getDashtype().equalsIgnoreCase("BRANCH")){
				colpara = "loccod";
			} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
				colpara = "rgncod";
			} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
				colpara = "zoncod"; 
			}
			
			String sql = "SELECT  sum(x.target) target, sum(x.commitment) commitment, sum(x.actual) actual, x.month, x.year FROM ( "
                    + "SELECT max(if(type='Target',jan,0.0)) target,max(if(type='Commitment',jan,0.0)) commitment,max(if(type='Actual',jan,0.0)) actual,1 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb),0.0)) target,max(if(type='Commitment',(jan+feb),0.0)) commitment,max(if(type='Actual',(jan+feb),0.0)) actual,2 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar),0.0)) target,max(if(type='Commitment',(jan+feb+mar),0.0)) commitment,max(if(type='Actual',(jan+feb+mar),0.0)) actual,3 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr),0.0)) actual,4 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may),0.0)) actual,5 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun),0.0)) actual,6 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul),0.0)) actual,7 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) actual,8 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) actual,9 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) actual,10 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) actual,11 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) actual,12 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='GWP' group by loccod "
                    + ") x group by year,month order by year,month ";
			
			return jdbcTemplate.query(sql, new TargetCommitmentActualRowMapper());
			
		} 
			
		return null;
	}

	@Override
	public List<TargetCommitmentActual> getCurrentYearMCFP(DashboardPara para) throws Exception {
		if(para.getDashtype().equalsIgnoreCase("IC")){
			
		} else if (para.getDashtype().equalsIgnoreCase("UNL")) {
			
		} else if (para.getDashtype().equalsIgnoreCase("BRANCH") || para.getDashtype().equalsIgnoreCase("REGION") || para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			String colpara = "loccod";
			if(para.getDashtype().equalsIgnoreCase("BRANCH")){
				colpara = "loccod";
			} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
				colpara = "rgncod";
			} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
				colpara = "zoncod"; 
			}
			
			String sql = "SELECT  sum(x.target) target, sum(x.commitment) commitment, sum(x.actual) actual, x.month, x.year FROM ( "
                    + "SELECT max(if(type='Target',jan,0.0)) target,max(if(type='Commitment',jan,0.0)) commitment,max(if(type='Actual',jan,0.0)) actual,1 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb),0.0)) target,max(if(type='Commitment',(jan+feb),0.0)) commitment,max(if(type='Actual',(jan+feb),0.0)) actual,2 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar),0.0)) target,max(if(type='Commitment',(jan+feb+mar),0.0)) commitment,max(if(type='Actual',(jan+feb+mar),0.0)) actual,3 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr),0.0)) actual,4 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may),0.0)) actual,5 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun),0.0)) actual,6 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul),0.0)) actual,7 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) actual,8 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) actual,9 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) actual,10 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) actual,11 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) actual,12 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='MCFP' group by loccod "
                    + ") x group by year,month order by year,month ";
			
			return jdbcTemplate.query(sql, new TargetCommitmentActualRowMapper());
			
		} 
			
		return null;
	}

	@Override
	public List<TargetCommitmentActual> getCurrentYearFYPAch(DashboardPara para) throws Exception {
		if(para.getDashtype().equalsIgnoreCase("IC")){
			
		} else if (para.getDashtype().equalsIgnoreCase("UNL")) {
			
		} else if (para.getDashtype().equalsIgnoreCase("BRANCH") || para.getDashtype().equalsIgnoreCase("REGION") || para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			String colpara = "loccod";
			if(para.getDashtype().equalsIgnoreCase("BRANCH")){
				colpara = "loccod";
			} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
				colpara = "rgncod";
			} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
				colpara = "zoncod"; 
			}
			
			String sql = "SELECT  sum(x.target) target, sum(x.commitment) commitment, sum(x.actual) actual, x.month, x.year FROM ( "
                    + "SELECT max(if(type='Target',jan,0.0)) target,max(if(type='Commitment',jan,0.0)) commitment,max(if(type='Actual',jan,0.0)) actual,1 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb),0.0)) target,max(if(type='Commitment',(jan+feb),0.0)) commitment,max(if(type='Actual',(jan+feb),0.0)) actual,2 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar),0.0)) target,max(if(type='Commitment',(jan+feb+mar),0.0)) commitment,max(if(type='Actual',(jan+feb+mar),0.0)) actual,3 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr),0.0)) actual,4 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may),0.0)) actual,5 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun),0.0)) actual,6 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul),0.0)) actual,7 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) actual,8 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) actual,9 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) actual,10 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) actual,11 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) actual,12 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='FYP (Ach)' group by loccod "
                    + ") x group by year,month order by year,month ";
			
			return jdbcTemplate.query(sql, new TargetCommitmentActualRowMapper());
			
		} 	
			
		return null;
	}

	@Override
	public List<TargetCommitmentActual> getCurrentYearRTNY1(DashboardPara para) throws Exception {
		if(para.getDashtype().equalsIgnoreCase("IC")){
			
		} else if (para.getDashtype().equalsIgnoreCase("UNL")) {
			
		} else if (para.getDashtype().equalsIgnoreCase("BRANCH") || para.getDashtype().equalsIgnoreCase("REGION") || para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			String colpara = "loccod";
			if(para.getDashtype().equalsIgnoreCase("BRANCH")){
				colpara = "loccod";
			} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
				colpara = "rgncod";
			} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
				colpara = "zoncod"; 
			}
			
			String sql = "SELECT  sum(x.target) target, sum(x.commitment) commitment, sum(x.actual) actual, x.month, x.year FROM ( "
                    + "SELECT max(if(type='Target',jan,0.0)) target,max(if(type='Commitment',jan,0.0)) commitment,max(if(type='Actual',jan,0.0)) actual,1 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb),0.0)) target,max(if(type='Commitment',(jan+feb),0.0)) commitment,max(if(type='Actual',(jan+feb),0.0)) actual,2 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar),0.0)) target,max(if(type='Commitment',(jan+feb+mar),0.0)) commitment,max(if(type='Actual',(jan+feb+mar),0.0)) actual,3 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr),0.0)) actual,4 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may),0.0)) actual,5 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun),0.0)) actual,6 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul),0.0)) actual,7 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug),0.0)) actual,8 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep),0.0)) actual,9 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct),0.0)) actual,10 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov),0.0)) actual,11 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + "UNION ALL "
                    + "SELECT max(if(type='Target',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) target,max(if(type='Commitment',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) commitment,max(if(type='Actual',(jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+`dec`),0.0)) actual,12 month,year FROM inbranchtargetsummary where sbucod='450' and "+colpara+"='"+para.getDashpara()+"' and year="+para.getDashyear()+" and para='RTNY1' group by loccod "
                    + ") x group by year,month order by year,month ";
			
			return jdbcTemplate.query(sql, new TargetCommitmentActualRowMapper());
			
		} 
			
		return null;
	}

}
