package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;
import org.arpicoinsurance.groupit.dashboard.dao.TargetActualDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.AgentAchievementRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.UNLAchievementRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.AgentAchievement;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.UNLAchievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TargetActualDaoImpl implements TargetActualDao {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public List<AgentAchievement> getAgentAchievements(DashboardPara para) throws Exception {
		//System.out.println(para.getDashpara()+" - "+para.getDashtype()+" - "+para.getUsertype());
		if(para.getUsertype().equalsIgnoreCase("IC")){
			//System.out.println(para.getDashpara());
			return jdbcTemplate.query("select  "
		            + "	   case  "
		            + "           when x.appdat < DATE_ADD(now(), INTERVAL -1 YEAR) then 10000 "
		            + "           when DATE_FORMAT(x.appdat ,'%Y%m')  <= concat(txnyer,if(length(txnmth)=1,concat('0',txnmth),txnmth))  then 7500 "
		            + "           ELSE 0 "
		            + "	   end trgamt, "
		            + "       sum(if(fstprm='True',((x.grsprm/x.paytrm)* "
		            + "       (select trgval from inproductperformance p "
		            + "        where p.sbucod=x.sbucod and p.prdcod=x.prdcod and stadat<=x.icpdat and (enddat is null or enddat>=x.icpdat)    and p.frmrng <= abs((x.grsprm)/x.paytrm) and p.torang >= abs((x.grsprm)/x.paytrm) "
		            + "        and if(p.sinprm='1',p.sinprm,'0') = if(x.sinprm='1',x.sinprm,'0')))/100,0)) trgaca, "
		            + "        txnmth mononl,txnyer yeronl "
		            + "from "
		            + "(select "
		            + "       a.sbucod, "
		            + "       max(a.advcod) advcod, "
		            + "       a.txnyer,a.txnmth, "
		            + "       max(a.prdcod) prdcod, "
		            + "       if(sum(if(a.amount<>0,a.amount,0))<>0,max(a.grsprm),0) grsprm, "
		            + "       case when a.paytrm = 1 and (sinprm is null or sinprm='0')  then 12 "
		            + "            when a.paytrm = 12 and (sinprm is null or sinprm='0') then 1 "
		            + "            when a.paytrm = 4 and (sinprm is null or sinprm='0') then 3 "
		            + "            when a.paytrm = 2 and (sinprm is null or sinprm='0') then 6 "
		            + "            when sinprm='1' then '1' end paytrm, "
		            + "       max(sinprm) sinprm, "
		            + "       max(if(a.icpyer=a.txnyer and a.icpmon=a.txnmth,'True','False')) fstprm, "
		            + "       min(b.icpdat) icpdat, "
		            + "       g.appdat "
		            + "from inbillingtransactions a "
		            + "inner join inproposals b on a.sbucod=b.sbucod and a.pprnum=b.pprnum and b.pprsta not in ('INAC') "
		            + "inner join inagentmast g  on b.sbucod=g.sbucod and b.advcod=g.agncod "
		            + "where a.sbucod=? and g.agncod= ? and date(a.txndat) between concat(?,'-01-01') and concat(?,'-12-31') and a.doccod<>'PRMI' and a.amount<>0 "
		            + "group by a.sbucod,a.pprnum,a.txnyer,a.txnmth,a.advcod "
		            + "having sum(if(a.amount<>0,a.amount*-1,0))<>0 "
		            + ") x where txnyer = YEAR(now()) "
		            + "group by txnyer,txnmth,advcod", new Object[] { "450",Integer.parseInt(para.getDashpara()), para.getDashyear(), para.getDashyear()}, new AgentAchievementRowMapper());
		} 
		return null;
	}

	@Override
	public AgentAchievement getAgentAchievement(DashboardPara para) throws Exception {
		//System.out.println(para.getDashpara());
		if (para.getUsertype().equalsIgnoreCase("UNL")) {
			return jdbcTemplate.queryForObject("SELECT "
            + "    0.0 trgamt, "
            + "    SUM((SELECT  "
            + "            IF(bil.sinprm = 1, orsntc, ortgcr) trgval "
            + "        FROM "
            + "            inproducts pr "
            + "        WHERE "
            + "            pr.sbucod = bil.sbucod "
            + "                AND pr.prdcod = bil.prdcod) / 100 * bil.grsprm) trgaca, "
            + "    bil.txnmth mononl,bil.txnyer yeronl "
            + "FROM "
            + "    (SELECT  "
            + "        a.sbucod, "
            + "            MAX(b.loccod) loccod, "
            + "            MAX(a.advcod) advcod, "
            + "            a.pprnum, "
            + "            a.txnbno, "
            + "            MAX(a.polnum) polnum, "
            + "            MAX(a.prdcod) prdcod, "
            + "            SUM(amount) prm_due, "
            + "            MAX(p.sinprm) sinprm, "
            + "            IF(SUM(IF(amount <> 0, amount, 0)) <> 0, MAX(a.grsprm), 0) grsprm, "
            + "            SUM(IF(amount <> 0, amount, 0)) * - 1 premum, "
            + "            DATE_FORMAT(a.txndat, '%Y') txnyer, "
            + "            DATE_FORMAT(a.txndat, '%m') txnmth, "
            + "            MAX(IF(a.agncls = 'UNL', a.advcod, a.unlcod)) unlcod "
            + "    FROM "
            + "        inbillingtransactions a "
            + "    INNER JOIN inagentmast b ON a.sbucod = b.sbucod "
            + "        AND a.advcod = b.agncod "
            + "    INNER JOIN inproposals p ON p.sbucod = a.sbucod "
            + "        AND p.polnum = a.polnum "
            + "        AND p.pprsta IN ('PLISU' , 'PCAN', 'LAMD', 'PLAPS', 'PLAPP') "
            + "    WHERE "
            + "        a.sbucod = ? "
            + "            AND date(a.txndat) BETWEEN DATE_FORMAT(NOW(), '%Y-%m-01') AND LAST_DAY(NOW()) "
            + "            AND a.amount <> 0 "
            + "            AND PERIOD_DIFF(DATE_FORMAT(CONCAT(a.txnyer, '-', a.txnmth, '-', '01'), '%Y%m'), DATE_FORMAT(p.icpdat, '%Y%m')) < 12 "
            + "            AND a.doccod NOT IN ('PRMI') "
            + "    GROUP BY a.sbucod , pprnum , txnyer , txnmth , a.txnbno "
            + "    HAVING SUM(amount) <> 0) bil "
            + "        INNER JOIN "
            + "    inagentmast unl ON unl.sbucod = bil.sbucod "
            + "        AND unl.agncod = bil.unlcod "
            + "WHERE "
            + "	unl.agncod=? "
            + "GROUP BY bil.unlcod , bil.txnyer , bil.txnmth ", new Object[] { "450",Integer.parseInt(para.getDashpara())}, new AgentAchievementRowMapper());
		}
		return null;
		
	}

	@Override
	public AgentAchievement getCurrentMonthICAchievement(DashboardPara para) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgentAchievement getCurrentMonthUNLAchievement(DashboardPara para) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UNLAchievement> getUNLAchievements(DashboardPara para) throws Exception {
		if (para.getUsertype().equalsIgnoreCase("UNL")) {
			return jdbcTemplate.query("SELECT trgamt,trgaca,trgtcfa,mononl, yeronl FROM inagentachievements where sbucod=? and agncod=? and yeronl=? order by mononl ", new Object[] { "450",Integer.parseInt(para.getDashpara()), para.getDashyear()}, new UNLAchievementRowMapper());
		} 
		return null;
	}

	

}
