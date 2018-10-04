package org.arpicoinsurance.groupit.dashboard.service;

import java.util.List;

public interface ComplaintWebClientService {
	
	List<String> loadProposalNumbers(String nic) throws Exception;
	
}
