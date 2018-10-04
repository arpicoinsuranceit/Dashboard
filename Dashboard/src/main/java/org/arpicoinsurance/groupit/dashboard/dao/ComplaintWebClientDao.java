package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;


public interface ComplaintWebClientDao {

	List<String> loadProposalNumbers(String nic) throws Exception;

}
