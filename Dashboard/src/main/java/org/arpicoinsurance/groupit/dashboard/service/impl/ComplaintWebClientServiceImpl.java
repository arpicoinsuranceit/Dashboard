package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.List;
import org.arpicoinsurance.groupit.dashboard.dao.ComplaintWebClientDao;
import org.arpicoinsurance.groupit.dashboard.service.ComplaintWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintWebClientServiceImpl implements ComplaintWebClientService{
	
	@Autowired
	private ComplaintWebClientDao complaintWebClientDao;

	@Override
	public List<String> loadProposalNumbers(String nic) throws Exception {
		
		if(nic!=null) {
			return complaintWebClientDao.loadProposalNumbers(nic);
		}
		
		return null;
	}

	

}
