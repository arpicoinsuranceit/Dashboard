package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.AgentDto;
import org.springframework.jdbc.core.RowMapper;

public class AgentRowMapper implements RowMapper<AgentDto>{

	@Override
	public AgentDto mapRow(ResultSet rst, int row) throws SQLException {
		
		AgentDto agent=new AgentDto();
		if(rst!=null) {
		agent.setAgentAccountNo(rst.getString("agentAccountNo"));
		agent.setAgentAddress1(rst.getString("agentAddress1"));
		agent.setAgentAddress2(rst.getString("agentAddress2"));
		agent.setAgentAppoinmentDate(rst.getString("agentAppoinmentDate"));
		agent.setAgentDesignation(rst.getString("agentDesignation"));
		agent.setAgentDob(rst.getString("agentDob"));
		agent.setAgentFirstName(rst.getString("agentFirstName"));
		agent.setAgentLastName(rst.getString("agentLastName"));
		agent.setAgentMidName(rst.getString("agentMidName"));
		agent.setAgentMobile(rst.getString("agentMobile"));
		agent.setAgentNic(rst.getString("agentNic"));
		agent.setAgentShortName(rst.getString("agentShortName"));
		agent.setAgentSlii(rst.getString("agentSlii"));
		}
		else {
			System.out.println("nullllllllllllllllllllllllllll");
		}
		return agent;
	}

}
