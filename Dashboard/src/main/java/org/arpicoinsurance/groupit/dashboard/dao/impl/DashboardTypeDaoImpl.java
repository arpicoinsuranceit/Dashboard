package org.arpicoinsurance.groupit.dashboard.dao.impl;

import org.arpicoinsurance.groupit.dashboard.dao.DashboardTypeDao;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.rowmapper.AgentAchievementRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.rowmapper.DashboardParaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardTypeDaoImpl implements DashboardTypeDao {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Override
	public DashboardPara getDashboardPara(String userCode) throws Exception {
		return jdbcTemplate.queryForObject("select case when alsnam='agncod' then 'IC' when alsnam='unlcod' then 'UNL' when alsnam='loccod' then 'BRANCH' when alsnam='rgncod' then 'REGION' when alsnam='zoncod' then 'ZONE' end dashtype,if(frmval='AAA' and tovalu='ZZZ',vlsta,frmval) dashpara from smaccesscontrol where sbucod=? and userid=? ", new Object[] { "450",userCode}, new DashboardParaRowMapper());
	}

}
