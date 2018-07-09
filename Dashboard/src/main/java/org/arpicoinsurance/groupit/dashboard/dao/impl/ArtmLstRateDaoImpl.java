package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.ArtmLstRateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class ArtmLstRateDaoImpl implements ArtmLstRateDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Double getLstYerRate(String quoCreDate) throws Exception {

		Double lstYearRate = null;

		try {

			List<Object> args = new ArrayList<>();
			args.add(quoCreDate);
			args.add(quoCreDate);

			lstYearRate = jdbcTemplate.query(
					"select othrpr from ininterest_rates where prdcod='ARTM' and cat_01='ARTM' and to_dat >= ? and frmdat <= ?",
					args.toArray(), new ResultSetExtractor<Double>() {

						@Override
						public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
							Double lstYearRateTemp = null;
							if (rs.next()) {
								return rs.getDouble("othrpr");
							}
							return lstYearRateTemp;
						}
					});
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return lstYearRate;
	}

}
