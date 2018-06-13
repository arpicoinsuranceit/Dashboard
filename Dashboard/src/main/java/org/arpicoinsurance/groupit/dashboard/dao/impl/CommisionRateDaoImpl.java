package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.dao.CommisionRateDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.CommisionRateRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.CommisionRatePara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommisionRateDaoImpl implements CommisionRateDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public HashMap<String, Double> getCommisionRate(CommisionRatePara commisionRatePara)
			throws Exception {
		//System.out.println(commisionRatePara.getPrdcod()+" "+commisionRatePara.getComyer()+" "+commisionRatePara.getToterm());
		HashMap<String, Double> commisionRate = null;
		try {
			commisionRate = jdbcTemplate.queryForObject(
					"select comper,comsin,combrk,combrs from inproductcom where prdcod=? and comyer=? and frmtrm <=? and toterm >=? ",
					new Object[] { commisionRatePara.getPrdcod(),commisionRatePara.getComyer(),commisionRatePara.getFrmtrm(),commisionRatePara.getToterm() }, new CommisionRateRowMapper());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return commisionRate != null ? commisionRate : new HashMap<>();
	}

}
