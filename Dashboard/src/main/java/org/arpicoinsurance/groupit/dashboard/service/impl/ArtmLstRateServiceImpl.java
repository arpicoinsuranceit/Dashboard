package org.arpicoinsurance.groupit.dashboard.service.impl;

import javax.transaction.Transactional;

import org.arpicoinsurance.groupit.dashboard.dao.ArtmLstRateDao;
import org.arpicoinsurance.groupit.dashboard.service.ArtmLstRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ArtmLstRateServiceImpl implements ArtmLstRateService {

	@Autowired
	ArtmLstRateDao artmLstRateDao;

	@Override
	public Double getLstYerRate(String quoCreDate) throws Exception {

		Double artmRate = artmLstRateDao.getLstYerRate(quoCreDate);

		return artmRate;
	}

}
