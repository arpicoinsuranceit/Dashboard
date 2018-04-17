package org.arpicoinsurance.groupit.dashboard.dao.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.arpicoinsurance.groupit.dashboard.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String save(HashMap<String, Object> details) throws Exception {

		jdbcTemplate.update(
				"insert into marksys.incustomers (sbucod, cscode, ppdnam, ppddob, ppdnag, ppdsex, \r\n"
						+ "ppdcst, ppdtel, sponam, sagnxt, ntitle, spodob, numchl, ppdnic, sponic) \r\n"
						+ "value ('450',?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { (String) details.get("cscode"), (String) details.get("ppdnam"),
						new SimpleDateFormat("dd-MM-yyyy").parse((String) details.get("ppddob")),
						Integer.parseInt(details.get("ppdnag").toString()), (String) details.get("ppdsex"),
						(String) details.get("ppdcst"), (String) details.get("ppdtel"), (String) details.get("sponam"),
						Integer.parseInt(details.get("sagnxt").toString()), (String) details.get("ntitle"),
						details.get("spodob") != null ? new SimpleDateFormat("dd-MM-yyyy").parse((String) details.get("spodob")) : null,
						Integer.parseInt(details.get("numchl").toString()), (String) details.get("ppdnic"),
						(String) details.get("sponic")

				});
		return "Success";
	}

}
