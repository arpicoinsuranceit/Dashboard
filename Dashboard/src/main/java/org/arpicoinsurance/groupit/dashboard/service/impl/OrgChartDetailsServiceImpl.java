package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.arpicoinsurance.groupit.dashboard.dao.OrgChartDao;
import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDetailsDto;
import org.arpicoinsurance.groupit.dashboard.dto.Test;
import org.arpicoinsurance.groupit.dashboard.service.OrgChartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrgChartDetailsServiceImpl implements OrgChartDetailsService {

	@Autowired
	private OrgChartDao orgChartDao;

	@Override
	public void load() throws Exception {
		List<OrgChartDetailsDto> chartDetailsDtos = orgChartDao.getOrgChartList();

		List<Object> list = new ArrayList<>();

		for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
			System.out.println(orgChartDetailsDto.toString());
		}

		for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
			Test test = new Test();
			if (orgChartDetailsDto.getAgncls().equals("RGN") && orgChartDetailsDto.getSubdcd().equals("RSM")
					&& orgChartDetailsDto.getActive() == 1) {
				String rgn = orgChartDetailsDto.getRgncod();
				test = getTest(test, orgChartDetailsDto);
				orgChartDetailsDto.setActive(0);

				for (OrgChartDetailsDto orgChartDetailsDto1 : chartDetailsDtos) {
					Test test1 = new Test();
					if(orgChartDetailsDto1.getAgncls().equals(rgn) && orgChartDetailsDto1.getSubdcd().equals("ARSM")
							&& orgChartDetailsDto1.getActive() == 1) {
						test1 = getTest(test1, orgChartDetailsDto1);
						orgChartDetailsDto1.setActive(0);
						
						for (OrgChartDetailsDto orgChartDetailsDto2 : chartDetailsDtos) {
							Test test2 = new Test();
							if(orgChartDetailsDto2.getAgncls().equals(rgn) && orgChartDetailsDto2.getSubdcd().equals("ARSM")
									&& orgChartDetailsDto2.getActive() == 1) {
								test1 = getTest(test2, orgChartDetailsDto2);
								orgChartDetailsDto2.setActive(0);
								
								
								
							}
						}
						
					}
				}

			}

			if (orgChartDetailsDto.getAgncls().equals("RGN") && orgChartDetailsDto.getSubdcd().equals("ARSM")
					&& orgChartDetailsDto.getActive() == 1) {
				String rgn = orgChartDetailsDto.getRgncod();
				test = getTest(test, orgChartDetailsDto);
				orgChartDetailsDto.setActive(0);

				for (OrgChartDetailsDto orgChartDetailsDto1 : chartDetailsDtos) {
					Test test1 = new Test();
					if(orgChartDetailsDto1.getAgncls().equals(rgn) && orgChartDetailsDto1.getSubdcd().equals("NABR")
							&& orgChartDetailsDto1.getActive() == 1) {
						test1 = getTest(test1, orgChartDetailsDto1);
						orgChartDetailsDto1.setActive(0);
						
					}
				}

			}

			if (orgChartDetailsDto.getAgncls().equals("RGN") && orgChartDetailsDto.getSubdcd().equals("NABR")
					&& orgChartDetailsDto.getActive() == 1) {
				String rgn = orgChartDetailsDto.getRgncod();
				test = getTest(test, orgChartDetailsDto);
				orgChartDetailsDto.setActive(0);

				for (OrgChartDetailsDto orgChartDetailsDto1 : chartDetailsDtos) {

				}

			}

			list.add(test);
		}

	}

	private Test getTest(Test test, OrgChartDetailsDto orgChartDetailsDto) {
		test.setName(orgChartDetailsDto.getShrtnm());
		test.setLocation(orgChartDetailsDto.getLoc_name());
		test.setDesignation(orgChartDetailsDto.getSubdes());
		test.setChilds(new ArrayList<>());

		return test;
	}

}
