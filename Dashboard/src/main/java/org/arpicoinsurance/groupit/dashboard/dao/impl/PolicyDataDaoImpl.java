package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;
import org.arpicoinsurance.groupit.dashboard.common.CalculationUtils;
import org.arpicoinsurance.groupit.dashboard.dao.PolicyDataDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.DuePoliciesRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.PendingPoliciesRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.PolicySummaryRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.InquiryLoad;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.PolicySummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyDataDaoImpl implements PolicyDataDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<DuePolicies> getDuePolicies(DashboardPara para) throws Exception {
		CalculationUtils calculationUtils = new CalculationUtils();
//		 System.out.println("********DuePolicies "+calculationUtils.getPara(para.getDashpara()));
		if (para.getUsertype().equalsIgnoreCase("IC") || para.getUsertype().equalsIgnoreCase("UNL")) {

			return jdbcTemplate.query(
					"select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
							+ "(select a.sbucod,p.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,max(b.amount) amount,b.txnyer,b.txnmth  "
							+ "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
							+ "where b.sbucod='450' and (a.agncod='"+Integer.parseInt(para.getDashpara())+"' or a.unlcod='"+Integer.parseInt(para.getDashpara())+"') and p.pprsta in ('PLISU','PLAPS')  "
							+ "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
							+ "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ",
					new DuePoliciesRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("BRANCH")) {

			return jdbcTemplate.query(
					"select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
							+ "(select p.sbucod,a.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,max(b.amount) amount,b.txnyer,b.txnmth  "
							+ "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
							+ "where b.sbucod= '450' and a.loccod IN ("+calculationUtils.getPara(para.getDashpara())+") and p.pprsta in ('PLISU','PLAPS')  "
							+ "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
							+ "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ",
							new DuePoliciesRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("REGION")) {

			return jdbcTemplate.query(
					"select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
							+ "(select p.sbucod,a.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,max(b.amount) amount,b.txnyer,b.txnmth  "
							+ "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
							+ "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
							+ "where b.sbucod='450' and l.rgncod IN ("+calculationUtils.getPara(para.getDashpara())+") and p.pprsta in ('PLISU','PLAPS')  "
							+ "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
							+ "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ",
					 new DuePoliciesRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("ZONE")) {

			return jdbcTemplate.query(
					"select x.loccod,x.pprnum,x.polnum,x.stadsc,x.ppdini,x.ppdmob,x.amount premium,count(x.pprnum) nofarias,sum(x.amount) totarias from  "
							+ "(select p.sbucod,a.loccod,p.pprnum,p.polnum,s.stadsc,p.ppdini,if(p.ppdmob is null,p.ppdtel,p.ppdmob) ppdmob,max(b.amount) amount,b.txnyer,b.txnmth  "
							+ "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
							+ "inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod "
							+ "inner join smtrxnstatus s on p.sbucod=s.sbucod and p.pprsta=s.statid "
							+ "where b.sbucod='450' and r.zoncod IN ("+calculationUtils.getPara(para.getDashpara())+") and p.pprsta in ('PLISU','PLAPS')  "
							+ "and concat(b.txnyer,'-',if(length(b.txnmth)='1',concat('0',b.txnmth),b.txnmth),'-01') < curdate() "
							+ "group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) <> 0) x group by x.pprnum order by CAST(x.polnum AS SIGNED) ",
					 new DuePoliciesRowMapper());

		}

		return null;
	}

	@Override
	public Integer getCurrentMonthNOP(DashboardPara para) throws Exception {
		CalculationUtils calculationUtils = new CalculationUtils();
		// System.out.println("********CurrentMonthNOP
		// "+calculationUtils.getPara(para.getDashpara()));
		if (para.getUsertype().equalsIgnoreCase("IC") || para.getUsertype().equalsIgnoreCase("UNL")) {

			return jdbcTemplate.queryForObject(
					"select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and (a.agncod=? or a.unlcod=?) ",
					new Object[] { "450", Integer.parseInt(para.getDashpara()), Integer.parseInt(para.getDashpara()) },
					Integer.class);

		} else if (para.getUsertype().equalsIgnoreCase("BRANCH")) {

			return jdbcTemplate.queryForObject(
					"select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and a.loccod IN (?) ",
					new Object[] { "450", calculationUtils.getPara(para.getDashpara()) }, Integer.class);

		} else if (para.getUsertype().equalsIgnoreCase("REGION")) {

			return jdbcTemplate.queryForObject(
					"select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
							+ "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and l.rgncod IN (?) ",
					new Object[] { "450", calculationUtils.getPara(para.getDashpara()) }, Integer.class);

		} else if (para.getUsertype().equalsIgnoreCase("ZONE")) {

			return jdbcTemplate.queryForObject(
					"select count(p.polnum) nop from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
							+ "inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod "
							+ "where p.sbucod=? and p.pprsta <> 'INAC' and p.poldat between DATE_FORMAT(NOW() ,'%Y-%m-01') and now() and r.zoncod IN (?) ",
					new Object[] { "450", calculationUtils.getPara(para.getDashpara()) }, Integer.class);

		}

		return null;
	}

	@Override
	public List<PendingPolicies> getPendingPolicies(DashboardPara para) throws Exception {
		// System.out.println(para.getDashpara()+" - "+para.getDashtype()+" -
		// "+para.getUsertype());
		CalculationUtils calculationUtils = new CalculationUtils();
		// System.out.println("********PendingPolicies
		// "+calculationUtils.getPara(para.getDashpara()));
		if (para.getUsertype().equalsIgnoreCase("IC") || para.getUsertype().equalsIgnoreCase("UNL")) {

			return jdbcTemplate.query(
					"select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
							+ "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "where p.sbucod='450' and p.pprsta <> 'INAC' and p.poldat is null and (a.agncod='"+Integer.parseInt(para.getDashpara())+"' or a.unlcod='"+Integer.parseInt(para.getDashpara())+"') "
							+ "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ",
					new PendingPoliciesRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("BRANCH")) {

			return jdbcTemplate.query(
					"select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
							+ "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "where p.sbucod='450' and p.pprsta <> 'INAC' and p.poldat is null and a.loccod IN ("+calculationUtils.getPara(para.getDashpara())+") "
							+ "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ",
					new PendingPoliciesRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("REGION")) {

			return jdbcTemplate.query(
					"select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
							+ "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
							+ "where p.sbucod='450' and p.pprsta <> 'INAC' and p.poldat is null and l.rgncod IN ("+calculationUtils.getPara(para.getDashpara())+") "
							+ "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ",
					new PendingPoliciesRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("ZONE")) {

			return jdbcTemplate.query(
					"select a.loccod,p.pprnum,a.agncod,p.ppdini,p.totprm,group_concat(m.addnot) requirment from inproposals p  "
							+ "inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.loccod=m.loccod and p.pprnum=m.pprnum and p.prpseq=m.prpseq "
							+ "inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod "
							+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code "
							+ "inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod "
							+ "where p.sbucod='450' and p.pprsta <> 'INAC' and p.poldat is null and r.zoncod IN ("+calculationUtils.getPara(para.getDashpara())+") "
							+ "group by p.pprnum order by CAST(p.pprnum AS SIGNED) DESC ",
					new PendingPoliciesRowMapper());

		}

		return null;
	}

	@Override
	public PolicySummary getPolicySummary(DashboardPara para) throws Exception {
		CalculationUtils calculationUtils = new CalculationUtils();
		// System.out.println("********Summary
		// "+calculationUtils.getPara(para.getDashpara()));
		if (para.getUsertype().equalsIgnoreCase("BRANCH")) {

			return jdbcTemplate.queryForObject("select sum(x.plisucount) plisucount, sum(x.plisuamount) plisuamount, "
					+ "sum(x.plapscount) plapscount, sum(x.plapsamount) plapsamount, "
					+ "sum(x.plappcount) plappcount, sum(x.plappamount) plappamount from ( "
					+ "select if(p.pprsta = 'PLISU',1,0) plisucount,if(p.pprsta = 'PLISU' ,p.totprm,0.0) plisuamount, "
					+ "if(p.pprsta = 'PLAPS',1,0) plapscount,if(p.pprsta = 'PLAPS' ,p.totprm,0.0) plapsamount, "
					+ "if(p.pprsta = 'PLAPP',1,0) plappcount,if(p.pprsta = 'PLAPP' ,p.totprm,0.0) plappamount    "
					+ "from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod  "
					+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code  "
					+ "where p.sbucod='450' and l.loc_code IN ("+calculationUtils.getPara(para.getDashpara())+") and p.pprsta in ('PLISU','PLAPS','PLAPP') ) x ",
					 new PolicySummaryRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("REGION")) {

			return jdbcTemplate.queryForObject("select sum(x.plisucount) plisucount, sum(x.plisuamount) plisuamount, "
					+ "sum(x.plapscount) plapscount, sum(x.plapsamount) plapsamount, "
					+ "sum(x.plappcount) plappcount, sum(x.plappamount) plappamount from ( "
					+ "select if(p.pprsta = 'PLISU',1,0) plisucount,if(p.pprsta = 'PLISU' ,p.totprm,0.0) plisuamount, "
					+ "if(p.pprsta = 'PLAPS',1,0) plapscount,if(p.pprsta = 'PLAPS' ,p.totprm,0.0) plapsamount, "
					+ "if(p.pprsta = 'PLAPP',1,0) plappcount,if(p.pprsta = 'PLAPP' ,p.totprm,0.0) plappamount    "
					+ "from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod  "
					+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code  "
					+ "where p.sbucod='450' and l.rgncod IN ("+calculationUtils.getPara(para.getDashpara())+") and p.pprsta in ('PLISU','PLAPS','PLAPP') ) x ",
					new PolicySummaryRowMapper());

		} else if (para.getUsertype().equalsIgnoreCase("ZONE")) {

			return jdbcTemplate.queryForObject("select sum(x.plisucount) plisucount, sum(x.plisuamount) plisuamount, "
					+ "sum(x.plapscount) plapscount, sum(x.plapsamount) plapsamount, "
					+ "sum(x.plappcount) plappcount, sum(x.plappamount) plappamount from ( "
					+ "select if(p.pprsta = 'PLISU',1,0) plisucount,if(p.pprsta = 'PLISU' ,p.totprm,0.0) plisuamount, "
					+ "if(p.pprsta = 'PLAPS',1,0) plapscount,if(p.pprsta = 'PLAPS' ,p.totprm,0.0) plapsamount, "
					+ "if(p.pprsta = 'PLAPP',1,0) plappcount,if(p.pprsta = 'PLAPP' ,p.totprm,0.0) plappamount    "
					+ "from inproposals p inner join inagentmast a on p.sbucod=a.sbucod and p.advcod=a.agncod  "
					+ "inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code  "
					+ "inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod "
					+ "where p.sbucod='450' and r.zoncod IN ("+calculationUtils.getPara(para.getDashpara())+") and p.pprsta in ('PLISU','PLAPS','PLAPP') ) x ",
					 new PolicySummaryRowMapper());

		}

		return null;

	}

	@Override
	public List<InquiryLoad> getPolicyListByAdvCod(String advCod) throws Exception {
		return jdbcTemplate.queryForList("select pprnum, polnum, ppdini, ppdnic, "
				+ "prdcod from inproposals where sbucod='450' and pprsta <> 'INAC'", null, InquiryLoad.class);
	}

}
