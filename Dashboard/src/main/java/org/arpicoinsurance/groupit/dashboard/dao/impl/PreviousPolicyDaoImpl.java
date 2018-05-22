package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.dao.PreviousPolicyDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.PreviousPolicyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PreviousPolicyDaoImpl implements PreviousPolicyDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public HashMap<String, Object> getsumAtRisk(String nic) throws Exception {
		// System.out.println(nic);
		HashMap<String, Object> details = null;
		try {
			details = jdbcTemplate.queryForObject(
					"select sum(sumrkm) as sumAtRisk, max(cscode) as custCode from inproposals a \r\n"
							+ "where a.sbucod='450' and a.pprsta <> 'INAC' and pprsta in ('PLISU','LAMD') \r\n"
							+ "and (a.polnum is not null or polnum <> '') and TRIM(a.ppdnic)= ? \r\n"
							+ "and TIMESTAMPDIFF(YEAR,icpdat,sysdate()) <=  2 group by a.ppdnic",
					new Object[] { nic }, new PreviousPolicyRowMapper());
		} catch (Exception e) {
		}
		return details != null ? details : new HashMap<>();
	}

}
