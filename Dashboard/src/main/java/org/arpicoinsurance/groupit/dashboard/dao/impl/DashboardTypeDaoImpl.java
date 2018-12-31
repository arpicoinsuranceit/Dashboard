package org.arpicoinsurance.groupit.dashboard.dao.impl;

import org.arpicoinsurance.groupit.dashboard.dao.DashboardTypeDao;
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
		//System.out.println(userCode);
		return jdbcTemplate.queryForObject("SELECT  " +
				"    CASE " +
				"        WHEN ac.alsnam = 'agncod' THEN (select agncls from inagentmast where agncod=ac.frmval) " +
				"        WHEN ac.alsnam = 'unlcod' THEN (select agncls from inagentmast where agncod=ac.frmval) " +
				"        WHEN ac.alsnam = 'loccod' THEN 'BRANCH' " +
				"        WHEN ac.alsnam = 'rgncod' THEN 'REGION' " +
				"        WHEN ac.alsnam = 'zoncod' THEN 'ZONE' " +
				"    END usertype, " +
				"    IF(ac.frmval = 'AAA' AND tovalu = 'ZZZ', " +
				"        ac.vlsta, " +
				"        ac.frmval) dashpara, " +
				//"    IF(ac.alsnam IN('agncod','unlcod'),(select IF(ag.appdat < DATE_ADD(now(), INTERVAL -1 YEAR) AND ag.agncls='UNL','DB2','DB1') from inagentmast ag where ag.sbucod=ac.sbucod and ag.agncod=ac.frmval),'DB2') dashtype, "+
				"    YEAR(curdate()) dashyear, MONTH(curdate()) dashmonth " +
				"FROM " +
				"    smaccesscontrol ac inner join rms_users u on ac.sbucod=u.SBU_CODE and ac.userid=u.USER_ID " +
				"WHERE " +
				"    ac.sbucod = ? AND ac.userid = ? and u.active='1' order by CRE_DATE DESC limit 1 ", new Object[] { "450",userCode}, new DashboardParaRowMapper());
	}

	@Override
	public String isHo(String userid) {
		
		System.out.println(userid); 
		
		String ho = jdbcTemplate.queryForObject("select r.LOC_CODE from rms_users r where SBU_CODE = '450' and USER_ID = '"+ userid +"' "
				+ "and active = 1 order by CRE_DATE desc limit 1", String.class);
		return ho;
	}

}
