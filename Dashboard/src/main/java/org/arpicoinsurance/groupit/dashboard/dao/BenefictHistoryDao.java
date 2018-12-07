package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.BenefictHistory;

public interface BenefictHistoryDao {

	public List<BenefictHistory> getHistory (String nic) throws Exception;
	
}
