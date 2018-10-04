package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;
import org.arpicoinsurance.groupit.dashboard.dao.ComplaintWebClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComplaintWebClientDaoImpl implements ComplaintWebClientDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<String> loadProposalNumbers(String nic) throws Exception {
		
		List<String> proposalNo= jdbcTemplate.queryForList("select polnum from inproposals where ppdnic='"+nic+"' and pprsta <> 'INAC' and sbucod='450' group by polnum;", String.class);
		System.out.println(proposalNo.size());
		return proposalNo;
	}

	

}
