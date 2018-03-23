package org.arpicoinsurance.groupit.dashboard.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpicoinsurance.groupit.dashboard.dto.PaymentHistoryDto;
import org.springframework.jdbc.core.RowMapper;

public class PaymentHistoryRowMapper implements RowMapper<PaymentHistoryDto>{

	@Override
	public PaymentHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		PaymentHistoryDto paymentHistoryDto = new PaymentHistoryDto();
		
		paymentHistoryDto.setYear(rs.getInt("txnyer"));
		paymentHistoryDto.setMonth(rs.getInt("txnmth"));
		paymentHistoryDto.setDate(rs.getString("txndat"));
		paymentHistoryDto.setSettledAmt(rs.getDouble("setamt"));
		paymentHistoryDto.setDueAmt(rs.getDouble("dueamt"));
		paymentHistoryDto.setDueDate(rs.getString("duedat"));
		paymentHistoryDto.setOutstanding(rs.getDouble("outstd"));
		paymentHistoryDto.setRemark(rs.getString("remark"));
		
		return paymentHistoryDto;
	}

}
