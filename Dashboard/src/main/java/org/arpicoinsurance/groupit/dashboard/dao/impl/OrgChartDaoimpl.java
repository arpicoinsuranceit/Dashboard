package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.OrgChartDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.InquiryLoadRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.OrgChartDetailsRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.OrgChartDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrgChartDaoimpl implements OrgChartDao{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<OrgChartDetailsDto> getOrgChartList(String exp) throws Exception {
		return jdbcTemplate.query(
				"select a.agncod, a.shrtnm,l.loc_name,d.subdes,d.subdcd,a.agncls, l.loc_code, r.rgncod, r.zoncod, a.unlcod from inagentmast a inner join insubdesignation d \r\n" + 
				"on a.agncls=d.descod and a.subdcd=d.subdcd and a.subtyp=d.subtyp\r\n" + 
				"inner join rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code\r\n" + 
				"inner join inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod\r\n" + 
				"where a.sbucod='450' and "+ exp +" and a.agnsta in ('ACT', 'INAC');", new OrgChartDetailsRowMapper());
	}

}
