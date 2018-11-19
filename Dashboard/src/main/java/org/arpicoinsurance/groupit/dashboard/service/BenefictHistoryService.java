package org.arpicoinsurance.groupit.dashboard.service;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.BenefictHistory;

public interface BenefictHistoryService {

	public List<BenefictHistory> getHistory(String nic) throws Exception;
}
