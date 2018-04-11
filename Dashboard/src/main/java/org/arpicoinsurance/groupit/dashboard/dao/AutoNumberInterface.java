package org.arpicoinsurance.groupit.dashboard.dao;

import com.mysql.jdbc.Connection;

public interface AutoNumberInterface {

	 public String[] getNewSerialNumber(String sbu_code, String
			 loc_code, String serial_id, Connection dbconnection, String instatus);

}
