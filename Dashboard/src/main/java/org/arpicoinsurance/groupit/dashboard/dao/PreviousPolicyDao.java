package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.HashMap;

public interface PreviousPolicyDao {

	HashMap< String , Object> getsumAtRisk(String nic) throws Exception;

}
