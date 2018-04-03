package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.arpicoinsurance.groupit.dashboard.dao.OrgChartDao;
import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDetailsDto;
import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDto;
import org.arpicoinsurance.groupit.dashboard.service.OrgChartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrgChartDetailsServiceImpl implements OrgChartDetailsService {

	@Autowired
	private OrgChartDao orgChartDao;

	@Override
	public List<OrgChartDto> load() throws Exception {
		List<OrgChartDetailsDto> chartDetailsDtos = orgChartDao.getOrgChartList();

		List<OrgChartDto> list = new ArrayList<>();

		for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
			if (orgChartDetailsDto.getSubdcd().equals("RSM") || orgChartDetailsDto.getSubdcd().equals("ARSM")) {
				
				OrgChartDto orgChartDto = new OrgChartDto();
				orgChartDto.setDesignation(orgChartDetailsDto.getSubdes());
				orgChartDto.setDesCod(orgChartDetailsDto.getSubdcd());
				orgChartDto.setLocation(orgChartDetailsDto.getRgncod());
				orgChartDto.setName(orgChartDetailsDto.getShrtnm());
				
				list.add(orgChartDto);
			}
		}

		if (list.size() > 0) {
			for (OrgChartDto orgChartDto : list) {
				for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
					if (orgChartDto.getLocation().equals(orgChartDetailsDto.getRgncod())
							&& (orgChartDetailsDto.getSubdcd().equals("SM")
									|| orgChartDetailsDto.getSubdcd().equals("ASM")
									|| orgChartDetailsDto.getSubdcd().equals("NABR"))) {
						
						OrgChartDto test2 = new OrgChartDto();
						test2.setDesignation(orgChartDetailsDto.getSubdes());
						test2.setDesCod(orgChartDetailsDto.getSubdcd());
						test2.setLocation(orgChartDetailsDto.getLoc_code());
						test2.setName(orgChartDetailsDto.getShrtnm());
						
						orgChartDto.getChilds().add(test2);
						
					}
				}

			}
		} else {
			for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
				if (orgChartDetailsDto.getSubdcd().equals("SM") || orgChartDetailsDto.getSubdcd().equals("ASM")
						|| orgChartDetailsDto.getSubdcd().equals("NABR")) {
					OrgChartDto test2 = new OrgChartDto();
					test2.setDesignation(orgChartDetailsDto.getSubdes());
					test2.setDesCod(orgChartDetailsDto.getSubdcd());
					test2.setLocation(orgChartDetailsDto.getLoc_code());
					test2.setName(orgChartDetailsDto.getShrtnm());
					
					list.add(test2);
				}
			}
		}
		
		
		
		if (list.size() > 0) {
			for (OrgChartDto orgChartDto : list) {
				if(orgChartDto.getChilds().size()>1) {
					for (OrgChartDto test2 : orgChartDto.getChilds()) {
						
						for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
							if (test2.getLocation().equals(orgChartDetailsDto.getLoc_code()) && (orgChartDetailsDto.getSubdcd().equals("FSDM") || orgChartDetailsDto.getSubdcd().equals("AFSDM") || orgChartDetailsDto.getSubdcd().equals("SFSDE") || orgChartDetailsDto.getSubdcd().equals("FSDE") || 
									orgChartDetailsDto.getSubdcd().equals("FSDO") || orgChartDetailsDto.getSubdcd().equals("RAM") || orgChartDetailsDto.getSubdcd().equals("ARAM") || orgChartDetailsDto.getSubdcd().equals("SRAE") || 
									orgChartDetailsDto.getSubdcd().equals("RAE") || orgChartDetailsDto.getSubdcd().equals("RAO") || orgChartDetailsDto.getSubdcd().equals("UDM") || orgChartDetailsDto.getSubdcd().equals("AUDM") || 
									orgChartDetailsDto.getSubdcd().equals("SUDE") || orgChartDetailsDto.getSubdcd().equals("UDE") || orgChartDetailsDto.getSubdcd().equals("UDO") || orgChartDetailsDto.getSubdcd().equals("RADM") || 
									orgChartDetailsDto.getSubdcd().equals("ARADM") || orgChartDetailsDto.getSubdcd().equals("SRADE") || orgChartDetailsDto.getSubdcd().equals("RADE") || orgChartDetailsDto.getSubdcd().equals("RADO")))
							
							{
								OrgChartDto test3 = new OrgChartDto();
								test3.setDesignation(orgChartDetailsDto.getSubdes());
								test3.setDesCod(orgChartDetailsDto.getSubdcd());
								test3.setLocation(orgChartDetailsDto.getLoc_code());
								test3.setName(orgChartDetailsDto.getShrtnm());
								
								test2.getChilds().add(test3);
							}
						}
						
					}
				}else {
					
					for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
						if (orgChartDto.getLocation().equals(orgChartDetailsDto.getLoc_code()) && (orgChartDetailsDto.getSubdcd().equals("FSDM") || orgChartDetailsDto.getSubdcd().equals("AFSDM") || orgChartDetailsDto.getSubdcd().equals("SFSDE") || orgChartDetailsDto.getSubdcd().equals("FSDE") || 
								orgChartDetailsDto.getSubdcd().equals("FSDO") || orgChartDetailsDto.getSubdcd().equals("RAM") || orgChartDetailsDto.getSubdcd().equals("ARAM") || orgChartDetailsDto.getSubdcd().equals("SRAE") || 
								orgChartDetailsDto.getSubdcd().equals("RAE") || orgChartDetailsDto.getSubdcd().equals("RAO") || orgChartDetailsDto.getSubdcd().equals("UDM") || orgChartDetailsDto.getSubdcd().equals("AUDM") || 
								orgChartDetailsDto.getSubdcd().equals("SUDE") || orgChartDetailsDto.getSubdcd().equals("UDE") || orgChartDetailsDto.getSubdcd().equals("UDO") || orgChartDetailsDto.getSubdcd().equals("RADM") || 
								orgChartDetailsDto.getSubdcd().equals("ARADM") || orgChartDetailsDto.getSubdcd().equals("SRADE") || orgChartDetailsDto.getSubdcd().equals("RADE") || orgChartDetailsDto.getSubdcd().equals("RADO")))
						
						{
							OrgChartDto test2 = new OrgChartDto();
							test2.setDesignation(orgChartDetailsDto.getSubdes());
							test2.setDesCod(orgChartDetailsDto.getSubdcd());
							test2.setLocation(orgChartDetailsDto.getLoc_code());
							test2.setName(orgChartDetailsDto.getShrtnm());
							
							orgChartDto.getChilds().add(test2);
						}
					}
					
				}
				

			}
		} else {
			for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
				if (orgChartDetailsDto.getSubdcd().equals("FSDM") || orgChartDetailsDto.getSubdcd().equals("AFSDM") || orgChartDetailsDto.getSubdcd().equals("SFSDE") || orgChartDetailsDto.getSubdcd().equals("FSDE") || 
						orgChartDetailsDto.getSubdcd().equals("FSDO") || orgChartDetailsDto.getSubdcd().equals("RAM") || orgChartDetailsDto.getSubdcd().equals("ARAM") || orgChartDetailsDto.getSubdcd().equals("SRAE") || 
						orgChartDetailsDto.getSubdcd().equals("RAE") || orgChartDetailsDto.getSubdcd().equals("RAO") || orgChartDetailsDto.getSubdcd().equals("UDM") || orgChartDetailsDto.getSubdcd().equals("AUDM") || 
						orgChartDetailsDto.getSubdcd().equals("SUDE") || orgChartDetailsDto.getSubdcd().equals("UDE") || orgChartDetailsDto.getSubdcd().equals("UDO") || orgChartDetailsDto.getSubdcd().equals("RADM") || 
						orgChartDetailsDto.getSubdcd().equals("ARADM") || orgChartDetailsDto.getSubdcd().equals("SRADE") || orgChartDetailsDto.getSubdcd().equals("RADE") || orgChartDetailsDto.getSubdcd().equals("RADO"))
				
				{
					OrgChartDto test2 = new OrgChartDto();
					test2.setDesignation(orgChartDetailsDto.getSubdes());
					test2.setDesCod(orgChartDetailsDto.getSubdcd());
					test2.setLocation(orgChartDetailsDto.getLoc_code());
					test2.setName(orgChartDetailsDto.getShrtnm());
					
					list.add(test2);
				}
			}
		}
		
		if (list.size() > 0) {
			for (OrgChartDto orgChartDto : list) {
				if(orgChartDto.getChilds().size()>1) {
					for (OrgChartDto test2 : orgChartDto.getChilds()) {
						if(test2.getChilds().size()>1) {
							for (OrgChartDto test3 : test2.getChilds()) {
								for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
									if (test3.getLocation().equals(orgChartDetailsDto.getLoc_code()) && orgChartDetailsDto.getSubdcd().equals("INC"))
									{
										OrgChartDto test4 = new OrgChartDto();
										test4.setDesignation(orgChartDetailsDto.getSubdes());
										test4.setDesCod(orgChartDetailsDto.getSubdcd());
										test4.setLocation(orgChartDetailsDto.getLoc_code());
										test4.setName(orgChartDetailsDto.getShrtnm());
										
										test3.getChilds().add(test4);
									}
								}
							}
						}else {
							for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
								if (orgChartDto.getLocation().equals(orgChartDetailsDto.getLoc_code()) && orgChartDetailsDto.getSubdcd().equals("INC"))
								{
									OrgChartDto test3 = new OrgChartDto();
									test3.setDesignation(orgChartDetailsDto.getSubdes());
									test3.setDesCod(orgChartDetailsDto.getSubdcd());
									test3.setLocation(orgChartDetailsDto.getLoc_code());
									test3.setName(orgChartDetailsDto.getShrtnm());
									
									test2.getChilds().add(test3);
								}
							}
						}
						
					}
				}else {
					for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
						if (orgChartDto.getLocation().equals(orgChartDetailsDto.getLoc_code()) && orgChartDetailsDto.getSubdcd().equals("INC"))
						{
							OrgChartDto test2 = new OrgChartDto();
							test2.setDesignation(orgChartDetailsDto.getSubdes());
							test2.setDesCod(orgChartDetailsDto.getSubdcd());
							test2.setLocation(orgChartDetailsDto.getLoc_code());
							test2.setName(orgChartDetailsDto.getShrtnm());
							
							orgChartDto.getChilds().add(test2);
						}
					}
				}
			}
		} else {
			for (OrgChartDetailsDto orgChartDetailsDto : chartDetailsDtos) {
				if (orgChartDetailsDto.getSubdcd().equals("INC"))
				{
					OrgChartDto test2 = new OrgChartDto();
					test2.setDesignation(orgChartDetailsDto.getSubdes());
					test2.setDesCod(orgChartDetailsDto.getSubdcd());
					test2.setLocation(orgChartDetailsDto.getLoc_code());
					test2.setName(orgChartDetailsDto.getShrtnm());
					
					list.add(test2);
				}
			}
		}
		return list;

	}

}
