package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.arpicoinsurance.groupit.dashboard.dao.UserAppoinmentDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.UserAppoinmentDaoRowMapper;
import org.arpicoinsurance.groupit.dashboard.helper.UserAppoinmentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserAppoinmentDaoImpl implements UserAppoinmentDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public UserAppoinmentHelper findByAgtCod(Integer usrCod) throws Exception {
		
		// System.out.println("Dao Impl Called : " +usrCod);

		UserAppoinmentHelper userAppoinmentHelper = null;
		try {
			List<Object> args = new ArrayList<>();
			args.add(usrCod);

			System.out.println(usrCod);

			userAppoinmentHelper = jdbcTemplate.queryForObject(
					"select i.agncod,i.agntit,i.agnnam,i.midnam,i.lasnam,i.agnad1,i.agnad2,i.agnnic,i.appdat,i.prnnam,i.alwamt, "
							+ "i.subdcd, d.subdes " + "from inagentmast  i inner join insubdesignation  d "
							+ "on i.sbucod=d.sbucod and i.subdcd=d.subdcd where i.agncod =? and d.subtyp in ('NP','NI','NU')",
					new UserAppoinmentDaoRowMapper(), args.toArray());
			return userAppoinmentHelper;
		} catch (Exception e) {
			System.out.println("DAO IMPL Error");
			return null;
		}

	}

	protected UserAppoinmentHelper getReq(ResultSet resultSet) throws SQLException {
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
