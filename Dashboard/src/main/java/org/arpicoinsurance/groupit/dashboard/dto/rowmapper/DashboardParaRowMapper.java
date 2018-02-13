package org.arpicoinsurance.groupit.dashboard.dto.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.springframework.jdbc.core.RowMapper;

public class DashboardParaRowMapper implements RowMapper<DashboardPara> {

	@Override
	public DashboardPara mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardPara dashboardPara = new DashboardPara();
		dashboardPara.setDashtype(rs.getString("dashtype"));
		dashboardPara.setDashpara(rs.getString("dashpara"));
		return dashboardPara;
	}

}
