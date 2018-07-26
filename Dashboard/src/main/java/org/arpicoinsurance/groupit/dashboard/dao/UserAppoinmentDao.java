package org.arpicoinsurance.groupit.dashboard.dao;

import org.arpicoinsurance.groupit.dashboard.helper.UserAppoinmentHelper;

public interface UserAppoinmentDao {

	UserAppoinmentHelper findByAgtCod(Integer usrCod) throws Exception;
	
}
