package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.BenefictHistoryDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.BenefictHistoryRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.BenefictHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BenefictHistoryDaoImpl implements BenefictHistoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<BenefictHistory> getHistory(String nic) throws Exception {

		return jdbcTemplate.query(
				"SELECT   " + 
				"    p.pprnum,  " + 
				"    p.prdcod,  " + 
				"    b.ridcod,  " + 
				"    p.totprm,  " + 
				"    SUM(b.sumasu) AS sumasu,  " + 
				"    SUM(b.rdrprm) AS rdrprm,  " + 
				"    (CASE  " + 
				"        WHEN p.ppdnic = '"+nic+"' THEN 'M'  " + 
				"        WHEN p.sponic = '"+nic+"' THEN 'S'  " + 
				"        ELSE 'N'  " + 
				"    END) AS type,  " + 
				"    (CASE  " + 
				"        WHEN p.sinprm = 1 and p.paytrm = 1 THEN 'S'  " + 
				"        WHEN p.sinprm != 1 and p.paytrm = 1 THEN 'Y'  " + 
				"        WHEN p.paytrm = 2 THEN 'H'  " + 
				"        WHEN p.paytrm = 1 THEN 'Q'  " + 
				"        WHEN p.paytrm = 12 THEN 'M'  " + 
				"        ELSE 'N'  " + 
				"    END) AS frequance  " + 
				"FROM  " + 
				"    inproposals p  " + 
				"        INNER JOIN  " + 
				"    inpropaddbenefit b ON p.sbucod = b.sbucod  " + 
				"        AND p.pprnum = b.pprnum  " + 
				"        AND p.prpseq = b.prpseq  " + 
				"WHERE  " + 
				"    p.sbucod = '450'  " + 
				"        AND (p.ppdnic = '"+nic+"' OR p.sponic = '"+nic+"')  " + 
				"        AND p.pprsta IN ('PLISU' , 'PLAPS', 'LAMD', 'L0','L1','L2','L3')  " + 
				"        AND b.ridcod IN ('HCBF' , 'HCBFC',  " + 
				"        'HCBFS',  " + 
				"        'HCBI',  " + 
				"        'HCBIC',  " + 
				"        'HCBIS',  " + 
				"        'HRB',  " + 
				"        'HRBC',  " + 
				"        'HRBS',  " + 
				"        'SHCBI',  " + 
				"        'SHCBIC',  " + 
				"        'SHCBIS',  " + 
				"        'SUHRB',  " + 
				"        'SUHRBC',  " + 
				"        'SUHRBS')  " + 
				"        AND b.sumasu > 0  " + 
				"GROUP BY ridcod",
				new BenefictHistoryRowMapper());
	}

}
