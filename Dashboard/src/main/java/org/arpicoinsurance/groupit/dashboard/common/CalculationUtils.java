package org.arpicoinsurance.groupit.dashboard.common;

public class CalculationUtils {

	public String getPara(String dashpara) {
		String[] paras = dashpara.split(",");
		String returnPara ="";
		for (String para : paras) {
			returnPara = returnPara+"'"+para+"',";
		}
		return returnPara.substring(0, (returnPara.length()-1));
	}
}
