package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.arpicoinsurance.groupit.dashboard.dao.AutoNumberInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AutoNumberMapper implements AutoNumberInterface {

	@Autowired
	private DataSource dataSource;

	@Override
	public String[] getNewSerialNumber(String sbu_code, String loc_code, String serial_id,
			com.mysql.jdbc.Connection given_connection, String instatus) {
		long increment_by = 0;
		long minimum_number = 0;
		long maximum_number = 0;
		long current_number = 0;
		String cycle = "N";
		int result = 0;
		String responce[] = new String[2];
		Connection con = null;
		// MySqlDataProvider dataprovider = (MySqlDataProvider)
		// DataProvider.getDataProvider();

		try {
			if (given_connection == null) {
				con = dataSource.getConnection();
			} else {
				con = given_connection;
			}
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql;
			if (loc_code.equalsIgnoreCase("")) {
				sql = "select * from smsequence where sbucod='" + sbu_code + "' and seqid='" + serial_id
						+ "' for update";
			} else {
				sql = "select * from smsequence where sbucod='" + sbu_code + "' and loccod='" + loc_code
						+ "' and seqid='" + serial_id + "' for update";
			}
			ResultSet rst = stmt.executeQuery(sql);
			if (rst.next()) {
				rst.beforeFirst();
			} else {
				rst.close();
				sql = "select * from smsequence where sbucod='" + sbu_code
						+ "' and (loccod='' or loccod is null) and seqid='" + serial_id + "' for update";
				rst = stmt.executeQuery(sql);
			}

			if (rst.next()) {

				increment_by = rst.getLong("incby");
				minimum_number = rst.getLong("minv");
				maximum_number = rst.getLong("maxv");
				current_number = rst.getLong("curv");
				cycle = rst.getString("cycl");
				current_number += increment_by;

				if (current_number < minimum_number) {
					result = -2; // current number is lessthan minumum number;
					responce[0] = "Error";
					responce[1] = String.valueOf(result);

				} else if (current_number > maximum_number) {

					if (cycle.equalsIgnoreCase("Y")) {
						current_number = minimum_number;
						result = 1;
					} else {
						result = -3; // current number is greater than the maximum number;
						responce[0] = "Error";
						responce[1] = String.valueOf(result);
					}

				} else {
					result = 1;
				}

			} else {
				result = -1; // squence object cannot be found
				responce[0] = "Error";
				responce[1] = String.valueOf(result);
			}

			if (result == 1) {

				rst.close();

				if (loc_code.equalsIgnoreCase("")) {
					sql = "update smsequence set curv='" + current_number + "' where sbucod='" + sbu_code
							+ "' and seqid='" + serial_id + "'";
				} else {
					sql = "update smsequence set curv='" + current_number + "' where sbucod='" + sbu_code
							+ "' and loccod='" + loc_code + "' and seqid='" + serial_id + "'";
				}

				int upd_cnt = stmt.executeUpdate(sql);
				if (upd_cnt == 0) {
					sql = "update smsequence set curv='" + current_number + "' where sbucod='" + sbu_code
							+ "' and (loccod='' or loccod is null) and seqid='" + serial_id + "'";
					upd_cnt = stmt.executeUpdate(sql);
				}

				responce[0] = "Success";
				responce[1] = String.valueOf(current_number);

			}

			// con.commit();
			// con.close();

		} catch (Exception e) {
			Logger log = Logger.getLogger("");
			log.log(Level.WARNING, "App Error", e);
			result = -999; // unexpected error has occured
			responce[0] = "Error";
			responce[1] = String.valueOf(result) + ". " + e.getLocalizedMessage();

			try {
				con.rollback();
				if (given_connection == null) {
					con.close();
				}

			} catch (SQLException se) {
				log = Logger.getLogger("");
				log.log(Level.WARNING, "App Error", se);
			}

		} finally {
			try {
				if (given_connection == null) {
					//con.commit();
					con.close();

				}

			} catch (SQLException se) {
				Logger log = Logger.getLogger("");
				log.log(Level.WARNING, "App Error", se);
			}
		}

		return responce;

	}
}
