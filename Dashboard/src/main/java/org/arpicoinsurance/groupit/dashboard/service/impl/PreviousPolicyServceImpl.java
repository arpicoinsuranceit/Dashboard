package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.arpicoinsurance.groupit.dashboard.dao.PreviousPolicyDao;
import org.arpicoinsurance.groupit.dashboard.service.PreviousPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PreviousPolicyServceImpl implements PreviousPolicyService{

	@Autowired
	private PreviousPolicyDao previousPolicyDao;
	
	@Override
	public HashMap< String , Object> getSumAtRisk(String nic) {
		HashMap< String , Object> details = previousPolicyDao.getsumAtRisk(nic);
		return details;
	}

}
