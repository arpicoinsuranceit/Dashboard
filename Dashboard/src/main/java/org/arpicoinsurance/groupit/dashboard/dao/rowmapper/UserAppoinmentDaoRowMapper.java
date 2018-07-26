package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.helper.UserAppoinmentHelper;
import org.springframework.jdbc.core.RowMapper;


public class UserAppoinmentDaoRowMapper implements RowMapper<UserAppoinmentHelper>{

	@Override
	public UserAppoinmentHelper mapRow(ResultSet resultSet, int arg1) throws SQLException {
		// System.out.println("Row Mapper Coloums Set Called");
		UserAppoinmentHelper appoinInfo = new UserAppoinmentHelper();
		appoinInfo.setAddress1(resultSet.getString("agnad1"));
		appoinInfo.setAddress2(resultSet.getString("agnad2"));
		appoinInfo.setAgentName(resultSet.getString("agnnam"));
		appoinInfo.setShortName(resultSet.getString("prnnam"));
		appoinInfo.setAgtCod(resultSet.getString("agncod"));
		appoinInfo.setMiddleName(resultSet.getString("midnam"));
		appoinInfo.setLastName(resultSet.getString("lasnam"));
		appoinInfo.setAgentNic(resultSet.getString("agnnic"));
		appoinInfo.setDesCategory(resultSet.getString("subdcd"));
		appoinInfo.setDesignation(resultSet.getString("subdes"));
		appoinInfo.setAppoinmentDate(resultSet.getDate("appdat"));
		appoinInfo.setAgentTitle(resultSet.getString("agntit"));
		appoinInfo.setAgentAllowance(resultSet.getDouble("alwamt"));
		
		return appoinInfo;
	}

}
