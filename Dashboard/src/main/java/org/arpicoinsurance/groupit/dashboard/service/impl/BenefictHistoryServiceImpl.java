package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.BenefictHistoryDao;
import org.arpicoinsurance.groupit.dashboard.dto.BenefictHistory;
import org.arpicoinsurance.groupit.dashboard.service.BenefictHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BenefictHistoryServiceImpl implements BenefictHistoryService{

	@Autowired 
	private BenefictHistoryDao benefictHistoryDao;
	
	@Override
	public List<BenefictHistory> getHistory(String nic) throws Exception {
		return benefictHistoryDao.getHistory(nic);
	}

}
