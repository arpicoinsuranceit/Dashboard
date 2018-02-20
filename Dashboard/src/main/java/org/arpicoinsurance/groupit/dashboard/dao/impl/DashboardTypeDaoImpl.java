package org.arpicoinsurance.groupit.dashboard.dao.impl;

import org.arpicoinsurance.groupit.dashboard.dao.DashboardTypeDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.AgentAchievementRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.DashboardParaRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardTypeDaoImpl implements DashboardTypeDao {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Override
	public DashboardPara getDashboardPara(String userCode) throws Exception {
		return jdbcTemplate.queryForObject("SELECT  " +
				"    CASE " +
				"        WHEN ac.alsnam = 'agncod' THEN 'IC' " +
				"        WHEN ac.alsnam = 'unlcod' THEN 'UNL' " +
				"        WHEN ac.alsnam = 'loccod' THEN 'BRANCH' " +
				"        WHEN ac.alsnam = 'rgncod' THEN 'REGION' " +
				"        WHEN ac.alsnam = 'zoncod' THEN 'ZONE' " +
				"    END usertype, " +
				"    IF(ac.frmval = 'AAA' AND tovalu = 'ZZZ', " +
				"        ac.vlsta, " +
				"        ac.frmval) dashpara, " +
				"    IF(ac.alsnam IN('agncod','unlcod'),(select IF(ag.appdat < DATE_ADD(now(), INTERVAL -1 YEAR) AND ag.agncls='UNL','DB2','DB1') from inagentmast ag where ag.sbucod=ac.sbucod and ag.agncod=ac.frmval),'DB2') dashtype, "+
				"    YEAR(curdate()) dashyear, MONTH(curdate()) dashmonth " +
				"FROM " +
				"    smaccesscontrol ac inner join rms_users u on ac.sbucod=u.SBU_CODE and ac.userid=u.USER_ID " +
				"WHERE " +
				"    ac.sbucod = ? AND ac.userid = ? ", new Object[] { "450",userCode}, new DashboardParaRowMapper());
	}

}
