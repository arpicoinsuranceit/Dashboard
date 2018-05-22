package org.arpicoinsurance.groupit.dashboard.service;

import java.util.HashMap;

public interface PreviousPolicyService {

	HashMap< String , Object> getSumAtRisk(String nic) throws Exception;

}
