package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.ProposalGeneralDto;
import org.springframework.jdbc.core.RowMapper;

public class ProposalGeneralRowMapper implements RowMapper<ProposalGeneralDto> {

	@Override
	public ProposalGeneralDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProposalGeneralDto proposalGeneralDto = new ProposalGeneralDto();
		
		proposalGeneralDto.setProposalNo(rs.getString("pprnum"));
		proposalGeneralDto.setPolicyNo(rs.getString("polnum"));
		proposalGeneralDto.setCommencementDate(rs.getString("comdat"));
		proposalGeneralDto.setBranchCode(rs.getString("loccod"));
		proposalGeneralDto.setProductCode(rs.getString("prdcod"));
		proposalGeneralDto.setProductName(rs.getString("prdnam"));
		proposalGeneralDto.setExpDate(rs.getString("expdat"));
		proposalGeneralDto.setSeqNo(rs.getString("prpseq"));
		
		proposalGeneralDto.setMainLifesex(rs.getString("ppdsex"));
		proposalGeneralDto.setMainLifeName(rs.getString("ppdnam"));
		proposalGeneralDto.setMainLifeNameIni(rs.getString("ppdini"));
		proposalGeneralDto.setMainlifeAddress(rs.getString("ppdad1")+rs.getString("ppdad2")+rs.getString("ppdad3"));
		proposalGeneralDto.setMainLifeNic(rs.getString("ppdnic"));
		proposalGeneralDto.setMainLifeTel(rs.getString("ppdtel"));
		proposalGeneralDto.setMainLifeMob(rs.getString("ppdmob"));
		proposalGeneralDto.setMainLifeEmail(rs.getString("ppdeml"));
		proposalGeneralDto.setMainLifeDob(rs.getString("ppddob"));
		proposalGeneralDto.setMainLifeNextAge(rs.getInt("ppdnag"));
		proposalGeneralDto.setMainLifeOcu(rs.getString("ppdocu"));
		proposalGeneralDto.setMainLifeStatus(rs.getString("ppdcst"));
		
		proposalGeneralDto.setBankNo(rs.getString("ban_no"));
		proposalGeneralDto.setAccountNo(rs.getString("accnum"));
		proposalGeneralDto.setPrevilageCardNo(rs.getString("crmnum"));
		
		proposalGeneralDto.setSpouseName(rs.getString("sponam"));
		proposalGeneralDto.setSpouseNameIni(rs.getString("spoini"));
		proposalGeneralDto.setSpouseNic(rs.getString("sponic"));
		proposalGeneralDto.setSpouseDob(rs.getString("spodob"));
		proposalGeneralDto.setSpouseocu(rs.getString("spoocu"));
		
		proposalGeneralDto.setPayTerm(rs.getString("pay_term"));
		proposalGeneralDto.setTargetPremium(rs.getDouble("trgprm"));
		proposalGeneralDto.setRelTerm(rs.getInt("rlftrm"));
		proposalGeneralDto.setTopTerm(rs.getInt("toptrm"));
		proposalGeneralDto.setBasicSum(rs.getDouble("bassum"));
		proposalGeneralDto.setPremiumForBasicSum(rs.getDouble("premum"));
		proposalGeneralDto.setTotalPremiun(rs.getDouble("totprm"));
		proposalGeneralDto.setQuotationNum(rs.getString("quonum"));
		
		proposalGeneralDto.setProposalStatus(rs.getString("pprsta"));
		proposalGeneralDto.setProposalDescription(rs.getString("stadsc"));
		
		proposalGeneralDto.setMainLifeSumAtRisk(rs.getDouble("sumrkm"));
		proposalGeneralDto.setSpouseSumAtRisk(rs.getDouble("sumrks"));
		return proposalGeneralDto;
	}

}
