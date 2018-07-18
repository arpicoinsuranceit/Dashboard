package org.arpicoinsurance.groupit.dashboard.service;

public interface UserAppoinmentService {

	byte [] createAppoinment(Integer usrCode) throws Exception;
	
	byte [] createTravelling(Integer usrCode)throws Exception;

}
