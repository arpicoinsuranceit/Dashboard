package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.Top3Dao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.DuePoliciesRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.Top3RowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.Top3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Top3DaoImpl implements Top3Dao{

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Top3> getTopIC() throws Exception {
		return jdbcTemplate.query("SELECT sum(mcfp) mcfp,count(polnum) polnum,advcod t3code,prnnam t3name FROM ( "
            + "SELECT  "
            + "    SUM(IF(fstprm = 'True', "
            + "        ((x.grsprm / x.paytrm) * (SELECT  "
            + "                trgval "
            + "            FROM "
            + "                inproductperformance p "
            + "            WHERE "
            + "                p.sbucod = x.sbucod "
            + "                    AND p.prdcod = x.prdcod "
            + "                    AND stadat <= x.icpdat "
            + "                    AND (enddat IS NULL OR enddat >= x.icpdat) "
            + "                    AND p.frmrng <= ABS((x.grsprm) / x.paytrm) "
            + "                    AND p.torang >= ABS((x.grsprm) / x.paytrm) "
            + "                    AND IF(p.sinprm = '1', p.sinprm, '0') = IF(x.sinprm = '1', x.sinprm, '0'))) / 100, "
            + "        0)) mcfp, "
            + "        polnum , advcod,prnnam, "
            + "    txnmth mononl, "
            + "    txnyer yeronl "
            + "FROM "
            + "    (SELECT  "
            + "        a.sbucod, "
            + "            MAX(a.advcod) advcod, "
            + "            g.prnnam, "
            + "            a.txnyer, "
            + "            a.txnmth, "
            + "            MAX(a.prdcod) prdcod, "
            + "            MAX(a.polnum) polnum, "
            + "            IF(SUM(IF(a.amount <> 0, a.amount, 0)) <> 0, MAX(a.grsprm), 0) grsprm, "
            + "            CASE "
            + "                WHEN "
            + "                    a.paytrm = 1 "
            + "                        AND (sinprm IS NULL OR sinprm = '0') "
            + "                THEN "
            + "                    12 "
            + "                WHEN "
            + "                    a.paytrm = 12 "
            + "                        AND (sinprm IS NULL OR sinprm = '0') "
            + "                THEN "
            + "                    1 "
            + "                WHEN "
            + "                    a.paytrm = 4 "
            + "                        AND (sinprm IS NULL OR sinprm = '0') "
            + "                THEN "
            + "                    3 "
            + "                WHEN "
            + "                    a.paytrm = 2 "
            + "                        AND (sinprm IS NULL OR sinprm = '0') "
            + "                THEN "
            + "                    6 "
            + "                WHEN sinprm = '1' THEN '1' "
            + "            END paytrm, "
            + "            MAX(sinprm) sinprm, "
            + "            MAX(IF(a.icpyer = a.txnyer "
            + "                AND a.icpmon = a.txnmth, 'True', 'False')) fstprm, "
            + "            MIN(b.icpdat) icpdat, "
            + "            g.appdat "
            + "    FROM "
            + "        inbillingtransactions a "
            + "    INNER JOIN inproposals b ON a.sbucod = b.sbucod "
            + "        AND a.pprnum = b.pprnum "
            + "        AND b.pprsta NOT IN ('INAC') "
            + "    INNER JOIN inagentmast g ON b.sbucod = g.sbucod "
            + "        AND b.advcod = g.agncod "
            + "    WHERE "
            + "        a.sbucod = ? AND g.agncls='IC' "
            + "            AND a.txndat BETWEEN DATE_FORMAT(now(),'%Y-%m-01') AND LAST_DAY(now()) "
            + "            AND a.doccod <> 'PRMI' "
            + "            AND a.amount <> 0 "
            + "    GROUP BY a.sbucod , a.pprnum , a.txnyer , a.txnmth , a.advcod "
            + "    HAVING SUM(IF(a.amount <> 0, a.amount * - 1, 0)) <> 0) x "
            + "WHERE "
            + "    txnyer = YEAR(NOW()) and txnmth=MONTH(now()) "
            + "GROUP BY txnyer , txnmth , polnum , advcod having mcfp > 0 ) z group by advcod  "
            + "having count(polnum) > 3  order by sum(mcfp) DESC limit 3 ", new Object[] { "450"}, new Top3RowMapper());
	}

	@Override
	public List<Top3> getTopIS() throws Exception {
		return jdbcTemplate.query("SELECT sum(mcfp) mcfp,count(polnum) polnum,advcod t3code,prnnam t3name FROM ( "
	            + "SELECT  "
	            + "    SUM(IF(fstprm = 'True', "
	            + "        ((x.grsprm / x.paytrm) * (SELECT  "
	            + "                trgval "
	            + "            FROM "
	            + "                inproductperformance p "
	            + "            WHERE "
	            + "                p.sbucod = x.sbucod "
	            + "                    AND p.prdcod = x.prdcod "
	            + "                    AND stadat <= x.icpdat "
	            + "                    AND (enddat IS NULL OR enddat >= x.icpdat) "
	            + "                    AND p.frmrng <= ABS((x.grsprm) / x.paytrm) "
	            + "                    AND p.torang >= ABS((x.grsprm) / x.paytrm) "
	            + "                    AND IF(p.sinprm = '1', p.sinprm, '0') = IF(x.sinprm = '1', x.sinprm, '0'))) / 100, "
	            + "        0)) mcfp, "
	            + "        polnum , advcod,prnnam, "
	            + "    txnmth mononl, "
	            + "    txnyer yeronl "
	            + "FROM "
	            + "    (SELECT  "
	            + "        a.sbucod, "
	            + "            MAX(a.advcod) advcod, "
	            + "            g.prnnam, "
	            + "            a.txnyer, "
	            + "            a.txnmth, "
	            + "            MAX(a.prdcod) prdcod, "
	            + "            MAX(a.polnum) polnum, "
	            + "            IF(SUM(IF(a.amount <> 0, a.amount, 0)) <> 0, MAX(a.grsprm), 0) grsprm, "
	            + "            CASE "
	            + "                WHEN "
	            + "                    a.paytrm = 1 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    12 "
	            + "                WHEN "
	            + "                    a.paytrm = 12 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    1 "
	            + "                WHEN "
	            + "                    a.paytrm = 4 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    3 "
	            + "                WHEN "
	            + "                    a.paytrm = 2 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    6 "
	            + "                WHEN sinprm = '1' THEN '1' "
	            + "            END paytrm, "
	            + "            MAX(sinprm) sinprm, "
	            + "            MAX(IF(a.icpyer = a.txnyer "
	            + "                AND a.icpmon = a.txnmth, 'True', 'False')) fstprm, "
	            + "            MIN(b.icpdat) icpdat, "
	            + "            g.appdat "
	            + "    FROM "
	            + "        inbillingtransactions a "
	            + "    INNER JOIN inproposals b ON a.sbucod = b.sbucod "
	            + "        AND a.pprnum = b.pprnum "
	            + "        AND b.pprsta NOT IN ('INAC') "
	            + "    INNER JOIN inagentmast g ON b.sbucod = g.sbucod "
	            + "        AND b.advcod = g.agncod "
	            + "    WHERE "
	            + "        a.sbucod = ? AND g.agncls='UNL' AND g.subtyp IN('PI','NI') "
	            + "            AND a.txndat BETWEEN DATE_FORMAT(now(),'%Y-%m-01') AND LAST_DAY(now()) "
	            + "            AND a.doccod <> 'PRMI' "
	            + "            AND a.amount <> 0 "
	            + "    GROUP BY a.sbucod , a.pprnum , a.txnyer , a.txnmth , a.advcod "
	            + "    HAVING SUM(IF(a.amount <> 0, a.amount * - 1, 0)) <> 0) x "
	            + "WHERE "
	            + "    txnyer = YEAR(NOW()) and txnmth=MONTH(now()) "
	            + "GROUP BY txnyer , txnmth , polnum , advcod having mcfp > 0 ) z group by advcod  "
	            + "having count(polnum) > 4  order by sum(mcfp) DESC limit 3 ", new Object[] { "450"}, new Top3RowMapper());
	}

	@Override
	public List<Top3> getTopUL() throws Exception {
		return jdbcTemplate.query("SELECT sum(mcfp) mcfp,count(polnum) polnum,advcod t3code,prnnam t3name FROM ( "
	            + "SELECT  "
	            + "    SUM(IF(fstprm = 'True', "
	            + "        ((x.grsprm / x.paytrm) * (SELECT  "
	            + "                trgval "
	            + "            FROM "
	            + "                inproductperformance p "
	            + "            WHERE "
	            + "                p.sbucod = x.sbucod "
	            + "                    AND p.prdcod = x.prdcod "
	            + "                    AND stadat <= x.icpdat "
	            + "                    AND (enddat IS NULL OR enddat >= x.icpdat) "
	            + "                    AND p.frmrng <= ABS((x.grsprm) / x.paytrm) "
	            + "                    AND p.torang >= ABS((x.grsprm) / x.paytrm) "
	            + "                    AND IF(p.sinprm = '1', p.sinprm, '0') = IF(x.sinprm = '1', x.sinprm, '0'))) / 100, "
	            + "        0)) mcfp, "
	            + "        polnum , advcod,prnnam, "
	            + "    txnmth mononl, "
	            + "    txnyer yeronl "
	            + "FROM "
	            + "    (SELECT  "
	            + "        a.sbucod, "
	            + "            MAX(a.advcod) advcod, "
	            + "            g.prnnam, "
	            + "            a.txnyer, "
	            + "            a.txnmth, "
	            + "            MAX(a.prdcod) prdcod, "
	            + "            MAX(a.polnum) polnum, "
	            + "            IF(SUM(IF(a.amount <> 0, a.amount, 0)) <> 0, MAX(a.grsprm), 0) grsprm, "
	            + "            CASE "
	            + "                WHEN "
	            + "                    a.paytrm = 1 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    12 "
	            + "                WHEN "
	            + "                    a.paytrm = 12 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    1 "
	            + "                WHEN "
	            + "                    a.paytrm = 4 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    3 "
	            + "                WHEN "
	            + "                    a.paytrm = 2 "
	            + "                        AND (sinprm IS NULL OR sinprm = '0') "
	            + "                THEN "
	            + "                    6 "
	            + "                WHEN sinprm = '1' THEN '1' "
	            + "            END paytrm, "
	            + "            MAX(sinprm) sinprm, "
	            + "            MAX(IF(a.icpyer = a.txnyer "
	            + "                AND a.icpmon = a.txnmth, 'True', 'False')) fstprm, "
	            + "            MIN(b.icpdat) icpdat, "
	            + "            g.appdat "
	            + "    FROM "
	            + "        inbillingtransactions a "
	            + "    INNER JOIN inproposals b ON a.sbucod = b.sbucod "
	            + "        AND a.pprnum = b.pprnum "
	            + "        AND b.pprsta NOT IN ('INAC') "
	            + "    INNER JOIN inagentmast g ON b.sbucod = g.sbucod "
	            + "        AND b.advcod = g.agncod "
	            + "    WHERE "
	            + "        a.sbucod = ? AND g.agncls='UNL' AND g.subtyp IN('PU','NU') "
	            + "            AND a.txndat BETWEEN DATE_FORMAT(now(),'%Y-%m-01') AND LAST_DAY(now()) "
	            + "            AND a.doccod <> 'PRMI' "
	            + "            AND a.amount <> 0 "
	            + "    GROUP BY a.sbucod , a.pprnum , a.txnyer , a.txnmth , a.advcod "
	            + "    HAVING SUM(IF(a.amount <> 0, a.amount * - 1, 0)) <> 0) x "
	            + "WHERE "
	            + "    txnyer = YEAR(NOW()) and txnmth=MONTH(now()) "
	            + "GROUP BY txnyer , txnmth , polnum , advcod having mcfp > 0 ) z group by advcod  "
	            + "having count(polnum) > 9  order by sum(mcfp) DESC limit 3 ", new Object[] { "450"}, new Top3RowMapper());
	}

	@Override
	public List<Top3> getTopBranch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Top3> getTopRegion() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Top3> getTopZone() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}