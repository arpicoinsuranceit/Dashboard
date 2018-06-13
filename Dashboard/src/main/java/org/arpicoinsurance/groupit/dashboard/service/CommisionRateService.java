package org.arpicoinsurance.groupit.dashboard.service;

import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.dto.CommisionRatePara;

public interface CommisionRateService {
	
	HashMap< String , Double> getCommisionRate(CommisionRatePara commisionRatePara) throws Exception;

}
