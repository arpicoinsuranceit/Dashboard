package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.arpicoinsurance.groupit.dashboard.dao.CommisionRateDao;
import org.arpicoinsurance.groupit.dashboard.dto.CommisionRatePara;
import org.arpicoinsurance.groupit.dashboard.service.CommisionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommisionRateServiceImpl implements CommisionRateService {

	@Autowired
	private CommisionRateDao commisionRateDao;
	
	@Override
	public HashMap<String, Double> getCommisionRate(CommisionRatePara commisionRatePara)
			throws Exception {
		HashMap< String , Double> commisionRate = commisionRateDao.getCommisionRate(commisionRatePara);
		return commisionRate;
	}

}
