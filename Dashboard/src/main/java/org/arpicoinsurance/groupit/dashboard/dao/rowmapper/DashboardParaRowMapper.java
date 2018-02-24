package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.springframework.jdbc.core.RowMapper;

public class DashboardParaRowMapper implements RowMapper<DashboardPara> {

	@Override
	public DashboardPara mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardPara dashboardPara = new DashboardPara();
		dashboardPara.setDashtype("DB2");
		dashboardPara.setDashpara(rs.getString("dashpara"));
		dashboardPara.setUsertype(rs.getString("usertype"));
		dashboardPara.setDashyear(rs.getInt("dashyear"));
		dashboardPara.setDashmonth(rs.getInt("dashmonth"));
		return dashboardPara;
	}

}
