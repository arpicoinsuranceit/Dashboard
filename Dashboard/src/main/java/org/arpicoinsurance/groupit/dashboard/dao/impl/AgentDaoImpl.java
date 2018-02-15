package org.arpicoinsurance.groupit.dashboard.dao.impl;

import org.arpicoinsurance.groupit.dashboard.dao.AgentDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.AgentRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.AgentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgentDaoImpl implements AgentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public AgentDto getAgent(String agentCode) throws Exception {
		System.out.println(agentCode);
		/*String sql = "select i.agnnam as agentFirstName, i.midnam as agentMidName,"
				+ " i.lasnam as agentLastName, i.shrtnm as agentShortName, i.agnnic "
				+ "as agentNic, i.sliirg as agentSlii, i.appdat as agentAppoinmentDate,"
				+ " i.agnad1 as agentAddress1, i.agnad2 as agentAddress2, i.agnmob as"
				+ " agentMobile, i.agndob as agentDob, i.accnum as agentAccountNo, "
				+ "d.subdes as agentDesignation from inagentmast i, insubdesignation d "
				+ "where i.subdcd=d.subdcd and i.subtyp=d.subtyp and i.agncod=? and i.sbucod=?";*/
		return jdbcTemplate.queryForObject("select i.agnnam as agentFirstName, i.midnam as agentMidName,"
				+ " i.lasnam as agentLastName, i.shrtnm as agentShortName, i.agnnic "
				+ "as agentNic, i.sliirg as agentSlii, i.appdat as agentAppoinmentDate,"
				+ " i.agnad1 as agentAddress1, i.agnad2 as agentAddress2, i.agnmob as"
				+ " agentMobile, i.agndob as agentDob, i.accnum as agentAccountNo, "
				+ "d.subdes as agentDesignation from inagentmast i, insubdesignation d "
				+ "where i.subdcd=d.subdcd and i.subtyp=d.subtyp and i.agncod=? and i.sbucod=?", new Object[] { agentCode, "450" }, new AgentRowMapper());
	}

}
