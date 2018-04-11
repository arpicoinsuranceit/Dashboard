package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.arpicoinsurance.groupit.dashboard.dao.AutoNumberInterface;
import org.arpicoinsurance.groupit.dashboard.dao.CustomerDao;
import org.arpicoinsurance.groupit.dashboard.dto.InvpSavePersonalInfo;
import org.arpicoinsurance.groupit.dashboard.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private AutoNumberInterface autoNmberInterface;

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public String save(InvpSavePersonalInfo info) throws Exception {
		
		String custCode = null;
		
		String[] cuspinnumber = autoNmberInterface.getNewSerialNumber("450", "", "CSPINSQ", null, "");

		if (cuspinnumber[0].equalsIgnoreCase("Success")) {

			if (!cuspinnumber[0].equalsIgnoreCase("0")) {
				custCode = cuspinnumber[1]; // add the serail number
			}
		} else {
			throw new Exception("Cannot find the autonumber sequence for customer creation");
		}
		
		
		HashMap< String , Object> hashMap = new HashMap<>();
		
		hashMap.put("cscode", custCode);
		hashMap.put("ppdnam", info.get_mainlife().get_mName());
		hashMap.put("ppddob", info.get_mainlife().get_mDob());
		hashMap.put("ppdnag", info.get_mainlife().get_mAge());
		hashMap.put("ppdsex", info.get_mainlife().get_mGender());
		hashMap.put("ppdcst", info.get_mainlife().get_mCivilStatus());
		hashMap.put("ppdtel", info.get_mainlife().get_mMobile());
		hashMap.put("ntitle", info.get_mainlife().get_mTitle());
		hashMap.put("ppdnic", info.get_mainlife().get_mNic());
		hashMap.put("numchl", info.get_childrenList() !=null ? info.get_childrenList().size() : 0);
		if(info.get_spouse()!=null) {
			hashMap.put("sponam", info.get_spouse().get_sName());
			hashMap.put("sagnxt", info.get_spouse().get_sAge());
			hashMap.put("spodob", info.get_spouse().get_sDob());
			hashMap.put("sponic", info.get_spouse().get_sNic());
		}else {
			hashMap.put("sponam", "");
			hashMap.put("sagnxt", -1);
			hashMap.put("spodob", null);
			hashMap.put("sponic", "");
		}
		if(customerDao.save(hashMap).equals("Success")) {
			
			return custCode;
		}
		
		return null;
	}

}
