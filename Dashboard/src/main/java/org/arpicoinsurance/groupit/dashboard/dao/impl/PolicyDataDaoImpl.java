package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.PolicyDataDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.DuePoliciesRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.PendingPoliciesRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyDataDaoImpl implements PolicyDataDao {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Override
	public List<DuePolicies> getDuePolicies(DashboardPara para) throws Exception {
		
		if (para.getUsertype().equalsIgnoreCase("IC") || para.getUsertype().equalsIgnoreCase("UNL")) {
			
			return jdbcTemplate.query("select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
		            + "(select a.sbucod,p.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,b.amount,b.txnyer,b.txnmth  "
		            + "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
		            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
		            + "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
		            + "where b.sbucod=? and (a.agncod=? or a.unlcod=?) and p.pprsta in ('PLISU','PLAPS')  "
		            + "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
		            + "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ", new Object[] { "450",Integer.parseInt(para.getDashpara()),Integer.parseInt(para.getDashpara())}, new DuePoliciesRowMapper());
		
		} else if (para.getUsertype().equalsIgnoreCase("BRANCH")) {
			
			return jdbcTemplate.query("select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
            + "(select p.sbucod,a.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,b.amount,b.txnyer,b.txnmth  "
            + "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
            + "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
            + "where b.sbucod=? and a.loccod=? and p.pprsta in ('PLISU','PLAPS')  "
            + "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
            + "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ", new Object[] { "450",para.getDashpara()}, new DuePoliciesRowMapper());
		
		} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
			
			return jdbcTemplate.query("select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
		            + "(select p.sbucod,a.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,b.amount,b.txnyer,b.txnmth  "
		            + "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
		            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
		            + "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
		            + "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
		            + "where b.sbucod=? and l.rgncod=? and p.pprsta in ('PLISU','PLAPS')  "
		            + "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
		            + "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ", new Object[] { "450",para.getDashpara()}, new DuePoliciesRowMapper());
			
		} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			return jdbcTemplate.query("select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
		            + "(select p.sbucod,a.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,b.amount,b.txnyer,b.txnmth  "
		            + "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
		            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
		            + "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
		            + "inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod "
		            + "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
		            + "where b.sbucod=? and r.zoncod=? and p.pprsta in ('PLISU','PLAPS')  "
		            + "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
		            + "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ", new Object[] { "450",para.getDashpara()}, new DuePoliciesRowMapper());
			
		}
		
		return null;
	}

	@Override
	public Integer getCurrentMonthNOP(DashboardPara para) throws Exception {
		
		if (para.getUsertype().equalsIgnoreCase("IC") || para.getUsertype().equalsIgnoreCase("UNL")) {
			
			return jdbcTemplate.queryForObject("select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and (a.agncod=? or a.unlcod=?) ", new Object[] { "450",Integer.parseInt(para.getDashpara()),Integer.parseInt(para.getDashpara())},Integer.class);
			
		} else if (para.getUsertype().equalsIgnoreCase("BRANCH")) {
			
			return jdbcTemplate.queryForObject("select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
		            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and a.loccod=? ", new Object[] { "450",para.getDashpara()},Integer.class);
			
		} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
			
			return jdbcTemplate.queryForObject("select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
					+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
		            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and l.rgncod=? ", new Object[] { "450",para.getDashpara()},Integer.class);
			
		} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			return jdbcTemplate.queryForObject("select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
					+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
					+ "inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod "
		            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and r.zoncod=? ", new Object[] { "450",para.getDashpara()},Integer.class);
			
		}
		
		return null;
	}

	@Override
	public List<PendingPolicies> getPendingPolicies(DashboardPara para) throws Exception {
		//System.out.println(para.getDashpara()+" - "+para.getDashtype()+" - "+para.getUsertype());
		if (para.getUsertype().equalsIgnoreCase("IC") || para.getUsertype().equalsIgnoreCase("UNL")) {
			
			return jdbcTemplate.query("select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
            + "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat is null and (a.agncod=? or a.unlcod=?) "
            + "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ", new Object[] { "450",Integer.parseInt(para.getDashpara()),Integer.parseInt(para.getDashpara())}, new PendingPoliciesRowMapper());
		
		} else if (para.getUsertype().equalsIgnoreCase("BRANCH")) {
			
			return jdbcTemplate.query("select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
		            + "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
		            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
		            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat is null and a.loccod=? "
		            + "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ", new Object[] { "450",para.getDashpara()}, new PendingPoliciesRowMapper());
			
		} else if (para.getDashtype().equalsIgnoreCase("REGION")) {
			
			return jdbcTemplate.query("select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
		            + "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
		            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
		            + "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
		            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat is null and l.rgncod=? "
		            + "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ", new Object[] { "450",para.getDashpara()}, new PendingPoliciesRowMapper());
			
		} else if (para.getDashtype().equalsIgnoreCase("ZONE")) {
			
			return jdbcTemplate.query("select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
		            + "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
		            + "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
		            + "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
		            + "inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod "
		            + "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat is null and r.zoncod=? "
		            + "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ", new Object[] { "450",para.getDashpara()}, new PendingPoliciesRowMapper());
			
		}

		return null;
	}

}
