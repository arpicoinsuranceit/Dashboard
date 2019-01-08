package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpicoinsurance.groupit.dashboard.common.CalculationUtils;
import org.arpicoinsurance.groupit.dashboard.dao.InproposalsDao;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.BenefictRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.ChildRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.HealthCareRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.InquiryLoadRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.MedicalReqRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.NomineeRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.PaymentHistoryRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.PilicyDispatchRowmapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.ProposalGeneralRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.SettlementRowMapper;
import org.arpicoinsurance.groupit.dashboard.dao.rowmapper.TransferHistoryRowMapper;
import org.arpicoinsurance.groupit.dashboard.dto.BenefictInquiryDto;
import org.arpicoinsurance.groupit.dashboard.dto.ChildDto;
import org.arpicoinsurance.groupit.dashboard.dto.HelthCare;
import org.arpicoinsurance.groupit.dashboard.dto.InquiryLoad;
import org.arpicoinsurance.groupit.dashboard.dto.MedicalReqDto;
import org.arpicoinsurance.groupit.dashboard.dto.NomineeInquiryDao;
import org.arpicoinsurance.groupit.dashboard.dto.PaymentHistoryDto;
import org.arpicoinsurance.groupit.dashboard.dto.PolicyDispatch;
import org.arpicoinsurance.groupit.dashboard.dto.PolicyDispatchAcknowDto;
import org.arpicoinsurance.groupit.dashboard.dto.ProposalGeneralDto;
import org.arpicoinsurance.groupit.dashboard.dto.SettlementDto;
import org.arpicoinsurance.groupit.dashboard.dto.TransferHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InproposalDaoImpl implements InproposalsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<InquiryLoad> getInquiriesIC(String advCode, Integer offset, Integer limit, String data)
			throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(advCode);
		args.add(offset);
		args.add(limit);

		return jdbcTemplate.query(
				"SELECT " + "        p.pprnum, p.polnum, p.ppdnam, p.ppdnic, p.prdcod,p.pprsta,p.advcod" + "    FROM"
						+ "        inproposals p" + "    WHERE" + "        p.sbucod = '450' and p.pprsta <> 'INAC'"
						+ "	    and p.advcod = ?" + data + "        order by CAST(p.pprnum AS SIGNED) limit ?,?",
				args.toArray(), new InquiryLoadRowMapper());
	}

	@Override
	public Integer getInquiryICCount(String advCode, String data) throws Exception {

		List<Object> args = new ArrayList<>();
		args.add(advCode);

		Integer count = jdbcTemplate.queryForObject("SELECT " + "        count(*) as count" + "    FROM"
				+ "        inproposals p" + "    WHERE" + "        p.sbucod = '450' and p.pprsta <> 'INAC'"
				+ "	    and p.advcod = ?" + data + " order by CAST(p.pprnum AS SIGNED)", Integer.class,
				args.toArray());

		return count;
	}

	@Override
	public List<InquiryLoad> getInquiriesUNL(String advCode, String unlCod, Integer offset, Integer limit, String data)
			throws Exception {

		List<Object> args = new ArrayList<>();
		args.add(advCode);
		args.add(unlCod);
		args.add(offset);
		args.add(limit);

		return jdbcTemplate.query(
				"SELECT " + "        p.pprnum, p.polnum, p.ppdnam, p.ppdnic, p.prdcod,p.pprsta,p.advcod" + "    FROM"
						+ "        inproposals p" + "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod"
						+ "        AND p.advcod = a.agncod" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC'" + data
						+ "	    and (a.agncod = ? OR a.unlcod = ?) order by CAST(p.pprnum AS SIGNED) limit ?,?",
				args.toArray(), new InquiryLoadRowMapper());
	}
	
	

	@Override
	public Integer getInquiryUNLCount(String advCode, String unlCod, String data) throws Exception {

		List<Object> args = new ArrayList<>();
		args.add(advCode);
		args.add(unlCod);

		Integer count = jdbcTemplate.queryForObject("SELECT " + "        count(*) as count" + "    FROM"
				+ "        inproposals p" + "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod"
				+ "        AND p.advcod = a.agncod" + "    WHERE" + "        p.sbucod = '450' and p.pprsta <> 'INAC'"
				+ data + "	    and (a.agncod = ? OR a.unlcod = ?)", Integer.class, args.toArray());

		return count;
	}

	@Override
	public List<InquiryLoad> getInquiriesBranch(String branchCod, Integer offset, Integer limit, String data)
			throws Exception {
		CalculationUtils calculationUtils = new CalculationUtils();
		List<Object> args = new ArrayList<>();
//		System.out.println(branchCod);
//		System.out.println(branchCod);
		String branchCodePara = calculationUtils.getPara(branchCod);
		args.add(offset);
		args.add(limit);
		calculationUtils = null;
		return jdbcTemplate.query(
				"SELECT" + "        p.pprnum, p.polnum, p.ppdnam, p.ppdnic, p.prdcod,p.pprsta,p.advcod" + "    FROM"
						+ "        inproposals p" + "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod"
						+ "        AND p.advcod = a.agncod" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC' "+ data + " and a.loccod IN("+branchCodePara+") order by CAST(p.pprnum AS SIGNED) limit ?,?",
				args.toArray(), new InquiryLoadRowMapper());
	}

	@Override
	public Integer getInquiryBranchCount(String branchCod, String data) throws Exception {
		CalculationUtils calculationUtils = new CalculationUtils();
		List<Object> args = new ArrayList<>();
		String branchCodePara = calculationUtils.getPara(branchCod);
		//System.out.println(branchCod);
		//args.add(calculationUtils.getPara(branchCod));
		calculationUtils = null;
		
		//System.out.println("************************* "+calculationUtils.getPara(branchCod));
		Integer count = jdbcTemplate.queryForObject("SELECT " + "        count(*) as count" + "    FROM"
				+ "        inproposals p" + "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod"
				+ "        AND p.advcod = a.agncod" + "    WHERE" + "        p.sbucod = '450' and p.pprsta <> 'INAC'"
				+ data + "	    and a.loccod IN("+branchCodePara+") ", Integer.class, args.toArray());

		return count;
	}

	@Override
	public List<InquiryLoad> getInquiriesRegion(String regionCod, Integer offset, Integer limit, String data)
			throws Exception {
		List<Object> args = new ArrayList<>();
		CalculationUtils calculationUtils = new CalculationUtils();
		String regionCodePara = calculationUtils.getPara(regionCod);
		//args.add(calculationUtils.getPara(regionCod));
		args.add(offset);
		args.add(limit);
		calculationUtils = null;
		return jdbcTemplate.query(
				"SELECT " + "        p.pprnum, p.polnum, p.ppdnam, p.ppdnic, p.prdcod,p.pprsta,p.advcod" + "    FROM"
						+ "        inproposals p" + "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod"
						+ "        AND p.advcod = a.agncod"
						+ "	INNER JOIN rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC'" + data + "	    and l.rgncod IN("+ regionCodePara +") order by CAST(p.pprnum AS SIGNED) limit ?,?",
				args.toArray(), new InquiryLoadRowMapper());
	}

	@Override
	public Integer getInquiryRegionCount(String regionCod, String data) throws Exception {
		List<Object> args = new ArrayList<>();
		CalculationUtils calculationUtils = new CalculationUtils();
		String regionCodePara = calculationUtils.getPara(regionCod);
		//args.add(calculationUtils.getPara(regionCod));
		calculationUtils = null;
		Integer count = jdbcTemplate.queryForObject(
				"SELECT " + "        count(*) as count" + "    FROM" + "        inproposals p"
						+ "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod" + "        AND p.advcod = a.agncod"
						+ "	INNER JOIN rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC'" + data + "	    and l.rgncod IN("+ regionCodePara +") ",
				Integer.class, args.toArray());

		return count;
	}

	@Override
	public List<InquiryLoad> getInquiriesZone(String zoneCod, Integer offset, Integer limit, String data)
			throws Exception {
		List<Object> args = new ArrayList<>();
		CalculationUtils calculationUtils = new CalculationUtils();
		String zoneCodePara = calculationUtils.getPara(zoneCod);
		//args.add(calculationUtils.getPara(zoneCod));
		args.add(offset);
		args.add(limit);
		calculationUtils = null;
		return jdbcTemplate.query(
				"SELECT " + "        p.pprnum, p.polnum, p.ppdnam, p.ppdnic, p.prdcod,p.pprsta,p.advcod" + "    FROM"
						+ "        inproposals p" + "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod"
						+ "        AND p.advcod = a.agncod"
						+ "	INNER JOIN rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code"
						+ "    INNER JOIN inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC'" + data + "	    and r.zoncod IN ("+ zoneCodePara +") order by CAST(p.pprnum AS SIGNED) limit ?,?",
				args.toArray(), new InquiryLoadRowMapper());
	}

	@Override
	public Integer getInquiryZoneCount(String zoneCod, String data) throws Exception {
		List<Object> args = new ArrayList<>();
		CalculationUtils calculationUtils = new CalculationUtils();
		String zoneCodePara = calculationUtils.getPara(zoneCod);
		//args.add(calculationUtils.getPara(zoneCod));
		calculationUtils = null;
		Integer count = jdbcTemplate.queryForObject(
				"SELECT " + "        count(*) as count" + "    FROM" + "        inproposals p"
						+ "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod" + "        AND p.advcod = a.agncod"
						+ "	INNER JOIN rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code"
						+ "    INNER JOIN inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC'" + data + "	    and r.zoncod IN ("+ zoneCodePara +") ",
				Integer.class, args.toArray());

		return count;
	}

	
	@Override
	public List<InquiryLoad> getInquiriesHo(String dashpara, int offset, int limit, String data) {
		List<Object> args = new ArrayList<>();
		args.add(offset);
		args.add(limit);

		return jdbcTemplate.query(
				"SELECT " 
						+ "        p.pprnum, p.polnum, p.ppdnam, p.ppdnic, p.prdcod,p.pprsta,p.advcod" + "    FROM"
						+ "        inproposals p" + "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod"
						+ "        AND p.advcod = a.agncod"
						+ "	INNER JOIN rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code"
						+ "    INNER JOIN inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC'" + data + " order by CAST(p.pprnum AS SIGNED) limit ?,?",
				args.toArray(), new InquiryLoadRowMapper());
		
	}
	
	@Override
	public Integer getInquiriesHoCount(String dashpara, String data) {
		List<Object> args = new ArrayList<>();

		Integer count = jdbcTemplate.queryForObject(
				"SELECT " + "        count(*) as count" + "    FROM" + "        inproposals p"
						+ "    INNER JOIN inagentmast a ON p.sbucod = a.sbucod" + "        AND p.advcod = a.agncod"
						+ "	INNER JOIN rms_locations l on a.sbucod=l.sbu_code and a.loccod=l.loc_code"
						+ "    INNER JOIN inregion r on l.sbu_code=r.sbucod and l.rgncod=r.rgncod" + "    WHERE"
						+ "        p.sbucod = '450' and p.pprsta <> 'INAC'" + data + "",
				Integer.class, args.toArray());

		return count;
	}
	
	@Override
	public ProposalGeneralDto getProposalGeneralDetails(String proposalNo) throws Exception {

		ProposalGeneralDto proposalGeneralDto = jdbcTemplate.queryForObject(
				"select p.sumrkm, p.sumrks, p.pprnum, p.polnum, p.comdat, p.loccod, p.prdcod, p.prdnam, p.expdat, p.ppdsex, p.ppdnam, p.ppdini, p.ppdad1, p.ppdad2, p.ppdad3, \r\n" + 
				"p.ppdnic, p.ppdtel, p.ppdmob, p.ppdeml, p.ppddob, p.ppdnag, p.ppdocu, p.ppdcst, p.ban_no, p.accnum, p.crmnum, p.sponam, p.spoini, p.sponic, \r\n" + 
				"p.spodob, p.spoocu, p.prpseq,\r\n" + 
				"case when p.paytrm = 1 and (p.sinprm is null or p.sinprm='0')  then 'Yearly'\r\n" + 
				"	when p.paytrm = 12 and (p.sinprm is null or p.sinprm='0') then 'Monthly'\r\n" + 
				"	when p.paytrm = 4 and (p.sinprm is null or p.sinprm='0') then 'Quartaly'\r\n" + 
				"	when p.paytrm = 2 and (p.sinprm is null or p.sinprm='0') then 'Half Yearly'\r\n" + 
				"	when sinprm='1' then 'Single Premium' end as pay_term, p.trgprm, p.rlftrm , p.toptrm, p.bassum, p.premum, p.totprm, p.quonum, p.pprsta, st.stadsc\r\n" + 
				"from inproposals p inner join smtrxnstatus st on st.statid = p.pprsta\r\n" + 
				"where p.pprnum = '"+ proposalNo +"' and p.pprsta <> 'INAC'",
				new ProposalGeneralRowMapper());

		return proposalGeneralDto;
	}

	@Override
	public List<ChildDto> getChildrenDetails(String proposalNo, String branchCode, Integer seqNo) throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(proposalNo);
		args.add(seqNo);
		args.add(branchCode);

		return jdbcTemplate.query(
				"select f.fmlnam as name, f.fmlrel as relation, f.fmldob as dob, f.fmlage as age, f.fmlsex as sex, f.cicapp as cibc, f.hrbapp as hrbc, f.hbcapp as hbc, f.shrbap as suhrbc  from \r\n"
						+ "inpropfamdetails f where f.sbucod='450' and f.pprnum = ? and f.prpseq = ? and f.loccod = ?",
				args.toArray(), new ChildRowMapper());
	}

	@Override
	public List<NomineeInquiryDao> getNomineeDetails(String proposalNo, String branchCode, Integer seqNo)
			throws Exception {

		List<Object> args = new ArrayList<>();
		args.add(proposalNo);
		args.add(seqNo);
		args.add(branchCode);

		return jdbcTemplate.query(
				"select n.nomnam as name, n.nomrel as relation, n.nomnic as nic, n.nomdob as dob, n.nomshr as shared, n.gurdnm as gname, n.gurnic as gnic, n.gurdob as gdob, n.gurrel as grelation  from\r\n"
						+ "inpropnomdetails n where n.sbucod='450' and n.pprnum = ? and n.prpseq = ? and n.loccod = ?",
				args.toArray(), new NomineeRowMapper());

	}

	@Override
	public List<BenefictInquiryDto> getBenefictDetails(String proposalNo, String branchCode, Integer seqNo)
			throws Exception {

		List<Object> args = new ArrayList<>();
		args.add(proposalNo);
		args.add(seqNo);
		args.add(branchCode);

		return jdbcTemplate.query(
				"select b.ridcod as riderCode, b.ridnam as riderName, b.ridtrm as term , b.sumasu as sumAssured, b.rdrprm as premium, b.instyp as benType from\r\n"
						+ "inpropaddbenefit b where b.sbucod='450' and b.pprnum = ? and b.prpseq = ? and b.loccod = ?",
				args.toArray(), new BenefictRowMapper());
	}

	@Override
	public List<MedicalReqDto> getMedicalReqDetails(String proposalNo, String branchCode, Integer seqNo)
			throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(proposalNo);
		args.add(seqNo);
		args.add(branchCode);

		return jdbcTemplate.query(
				"select r.medcod as testCode, r.mednam as testName, r.medorg as origin, r.tessta as recived, r.hoscod as hospital, r.rcddat as testDate, r.payamt as amount, r.paysta as payStatus, r.addnot as additionalNote, r.instyp as type  from \r\n"
						+ "inpropmedicalreq r where r.sbucod='450' and r.pprnum = ? and r.prpseq = ?  and r.loccod = ? ",
				args.toArray(), new MedicalReqRowMapper());
	}

	@Override
	public List<TransferHistoryDto> getTransferHistoryDetails(String proposalNo) throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(proposalNo);
		args.add(proposalNo);

		return jdbcTemplate.query(
				"select a.pprnum,a.advcod,ag.shrtnm,ag.agncls,date_format(a.creadt,'%d-%m-%Y') frmdat,'' todate from inproposals a \r\n"
						+ "inner join inagentmast ag on ag.sbucod=a.sbucod and ag.agncod=a.advcod \r\n"
						+ "where a.sbucod='450' and a.pprsta <> 'INAC' and a.polnum is not null and a.pprnum= ? \r\n"
						+ "union\r\n"
						+ "select a.pprnum,b.advcod,ag.shrtnm,ag.agncls,date_format(b.creadt,'%d-%m-%Y') frmdat,date_format(date_add(a.creadt,INTERVAL -1 DAY),'%d-%m-%Y') todate from inproposals a  inner join inproposals b\r\n"
						+ "on a.sbucod=b.sbucod and a.loccod=b.loccod and a.pprnum=b.pprnum and a.advcod <> b.advcod\r\n"
						+ "inner join inagentmast ag on ag.sbucod=b.sbucod and ag.agncod=b.advcod\r\n"
						+ "where a.sbucod='450' and a.pprsta <> 'INAC' and b.polnum is not null and a.pprnum=? ",
				args.toArray(), new TransferHistoryRowMapper());
	}

	@Override
	public List<SettlementDto> getSettlementDetails(String proposalNo) throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(proposalNo);
		args.add(proposalNo);

		return jdbcTemplate.query(
				"select a.docnum,a.ppdnam,b.totprm,a.doccod,a.loccod,if(a.paymod = 'CS', 'not applicable ( N/A )',a.chqrel) as chqrel,a.paymod from intransactions a inner join (select sbucod,doccod,docnum,sum(depost*-1) totprm from inbillingtransactions \r\n"
						+ "where sbucod='450' and pprnum= ? group by sbucod,doccod,docnum having sum(depost*-1) > 0) b \r\n"
						+ "on a.sbucod=b.sbucod and a.doccod=b.doccod and a.docnum=b.docnum \r\n"
						+ "where a.sbucod='450' and a.pprnum= ? and a.linnum=0 ",
				args.toArray(), new SettlementRowMapper());
	}

	@Override
	public List<PaymentHistoryDto> getPaymentHistoryDetails(String polocyNumber, String branchCode) throws Exception {
		List<Object> args = new ArrayList<>();
		args.add(polocyNumber);

		return jdbcTemplate.query(
				"select txnyer,txnmth,max(txndat) txndat,sum(if(doccod <> 'PRMI',amount,0)) setamt,sum(if(doccod = 'PRMI',amount,0)) dueamt,max(duedat) duedat, \r\n"
						+ "sum(if(doccod = 'PRMI',amount,0))+sum(if(doccod <> 'PRMI',amount,0)) outstd, \r\n"
						+ "ifnull((select group_concat(docnum) docnum from inbillingtransactions b \r\n"
						+ "where a.sbucod=b.sbucod and a.pprnum=b.pprnum and a.txnyer=b.txnyer \r\n"
						+ "and a.txnmth=b.txnmth and doccod <> 'PRMI' and amount <> 0 and txntyp <> 'RECOVERY' group by txnyer,txnmth),'') remark  \r\n"
						+ "from inbillingtransactions a where sbucod='450' and polnum= ? and amount <> 0 \r\n"
						+ "group by txnyer desc,txnmth desc",
				args.toArray(), new PaymentHistoryRowMapper());
	}

	@Override
	public PolicyDispatchAcknowDto getPolicyDispatch(String policyNo) throws Exception {

		PolicyDispatchAcknowDto acknowDto = new PolicyDispatchAcknowDto();

		List<PolicyDispatch> dispatch = jdbcTemplate.query(
				"SELECT dspdat, agncod , agnnam, ackdat, cusdat FROM inpoldispatchlog where sbucod='450' and polnum="
						+ policyNo,
				new PilicyDispatchRowmapper());
		List<HelthCare> helthCare = jdbcTemplate
				.query("SELECT cadsdt, remark FROM inhealthcarecard where sbucod='450' and polnum='" + policyNo
						+ "'  order by lockin desc limit 1", new HealthCareRowMapper());
		if (dispatch.size() < 1) {
			acknowDto.setDispatch(new PolicyDispatch());
		}else {
			acknowDto.setDispatch(dispatch.get(0));
		}
		if (helthCare.size() > 0) {
			acknowDto.setCare(helthCare.get(0));
		}else {
			acknowDto.setCare(new HelthCare());
		}

		return acknowDto;
	}
}
