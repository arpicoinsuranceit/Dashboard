package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.dto.CommisionRatePara;

public interface CommisionRateDao {
	
	HashMap< String , Double> getCommisionRate(CommisionRatePara commisionRatePara) throws Exception;

}
