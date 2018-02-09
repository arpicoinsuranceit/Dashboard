package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.ArrayList;

import org.arpicoinsurance.groupit.dashboard.dto.DevPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.dto.MonthlyTarget;
import org.arpicoinsurance.groupit.dashboard.dto.NameSeriasPair;
import org.arpicoinsurance.groupit.dashboard.dto.NameValuePair;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPol;
import org.arpicoinsurance.groupit.dashboard.dto.Top3;
import org.arpicoinsurance.groupit.dashboard.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

	@Override
	public MainRespDto getDashboard(Integer id) throws Exception {

		MainRespDto mainRespDto = new MainRespDto();

		MonthlyTarget monthlyTarget = new MonthlyTarget();

		monthlyTarget.setActual(150000);
		monthlyTarget.setTarget(200000);
		monthlyTarget.setTargetExpand(250000);
		mainRespDto.setMonthlyTarget(monthlyTarget);

		mainRespDto.setYearlyTarget(getYearlyValues());
		mainRespDto.setNop(getNOP());
		mainRespDto.setGwp(getNOP());
		mainRespDto.setMcfp(getNOP());
		mainRespDto.setRiny1(getNOP());
		mainRespDto.setFyp(getNOP());
		mainRespDto.setIc(getTop3());
		mainRespDto.setUl(getTop3());
		mainRespDto.setRegion(getTop3());
		mainRespDto.setBranch(getTop3());
		mainRespDto.setZone(getTop3());
		mainRespDto.setDevPolicieList(getDeiPol());
		mainRespDto.setPendingPolList(getPendingPol());
		return mainRespDto;
	}

	private ArrayList<PendingPol> getPendingPol() {
		ArrayList<PendingPol> arrayList=new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			PendingPol pendingPol=new PendingPol();
			pendingPol.setAgentCode("000000"+i);
			pendingPol.setBranch("branch"+i);
			pendingPol.setCustName("Customer "+i);
			pendingPol.setPropNo("Prop0000"+i);
			pendingPol.setProposal(5000.00);
			pendingPol.setReq("o.s.w.s.handler.SimpleUrlHandlerMapping");
			
			arrayList.add(pendingPol);
		}
		return arrayList;
	}

	private ArrayList<DevPolicies> getDeiPol() {
		ArrayList<DevPolicies> arrayList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			DevPolicies devPolicies=new DevPolicies();
			devPolicies.setArias(i);
			devPolicies.setBranch("branch"+i);
			devPolicies.setCustName("Customer "+i);
			devPolicies.setPolNum(""+i);
			devPolicies.setPremium(25000.00);
			arrayList.add(devPolicies);
		}
		return arrayList;
	}

	private ArrayList<Top3> getTop3() {
		
		ArrayList<Top3> arrayList=new ArrayList<>();
		
		Top3 top31=new Top3();
		top31.setCode("00001");
		top31.setName("Sample Name 1");
		top31.setUrl("#");
		
		Top3 top32=new Top3();
		top32.setCode("00002");
		top32.setName("Sample Name 2");
		top32.setUrl("#");
		 
		Top3 top33=new Top3();
		top33.setCode("00003");
		top33.setName("Sample Name 3");
		top33.setUrl("#");
		
		arrayList.add(top31);
		arrayList.add(top32);
		arrayList.add(top33);
		return arrayList;
	}

	private ArrayList<NameSeriasPair> getYearlyValues() {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair jan = new NameSeriasPair();

		ArrayList<NameValuePair> janVal = new ArrayList<>();

		NameValuePair janT = new NameValuePair();
		janT.setName("Target");
		janT.setValue(250000);
		janVal.add(janT);
		NameValuePair janA = new NameValuePair();
		janA.setName("Actual");
		janA.setValue(225000);
		janVal.add(janA);

		jan.setName("Jan.");
		jan.setSeries(janVal);

		yearlyValues.add(jan);

		NameSeriasPair feb = new NameSeriasPair();
		ArrayList<NameValuePair> febVal = new ArrayList<>();
		NameValuePair febT = new NameValuePair();
		febT.setName("Target");
		febT.setValue(150000);
		febVal.add(febT);
		NameValuePair febA = new NameValuePair();
		febA.setName("Actual");
		febA.setValue(225000);
		febVal.add(febA);
		feb.setName("Feb.");
		feb.setSeries(febVal);
		yearlyValues.add(feb);

		NameSeriasPair mar = new NameSeriasPair();
		ArrayList<NameValuePair> marVal = new ArrayList<>();
		NameValuePair marT = new NameValuePair();
		marT.setName("Target");
		marT.setValue(175000);
		marVal.add(marT);
		NameValuePair marA = new NameValuePair();
		marA.setName("Actual");
		marA.setValue(150000);
		marVal.add(marA);
		mar.setName("Mar.");
		mar.setSeries(marVal);
		yearlyValues.add(mar);

		NameSeriasPair apr = new NameSeriasPair();
		ArrayList<NameValuePair> aprVal = new ArrayList<>();
		NameValuePair aprT = new NameValuePair();
		aprT.setName("Target");
		aprT.setValue(180000);
		aprVal.add(aprT);
		NameValuePair aprA = new NameValuePair();
		aprA.setName("Actual");
		aprA.setValue(170000);
		aprVal.add(aprA);
		apr.setName("Apr.");
		apr.setSeries(aprVal);
		yearlyValues.add(apr);

		NameSeriasPair may = new NameSeriasPair();
		ArrayList<NameValuePair> mayVal = new ArrayList<>();
		NameValuePair mayT = new NameValuePair();
		mayT.setName("Target");
		mayT.setValue(145000);
		mayVal.add(mayT);
		NameValuePair mayA = new NameValuePair();
		mayA.setName("Actual");
		mayA.setValue(150000);
		mayVal.add(mayA);
		may.setName("May");
		may.setSeries(mayVal);
		yearlyValues.add(may);

		NameSeriasPair jun = new NameSeriasPair();
		ArrayList<NameValuePair> junVal = new ArrayList<>();
		NameValuePair junT = new NameValuePair();
		junT.setName("Target");
		junT.setValue(180000);
		junVal.add(junT);
		NameValuePair junA = new NameValuePair();
		junA.setName("Actual");
		junA.setValue(130000);
		junVal.add(junA);
		jun.setName("Jun.");
		jun.setSeries(junVal);
		yearlyValues.add(jun);

		NameSeriasPair Jul = new NameSeriasPair();
		ArrayList<NameValuePair> JulVal = new ArrayList<>();
		NameValuePair JulT = new NameValuePair();
		JulT.setName("Target");
		JulT.setValue(125000);
		JulVal.add(JulT);
		NameValuePair JulA = new NameValuePair();
		JulA.setName("Actual");
		JulA.setValue(150000);
		JulVal.add(JulA);
		Jul.setName("Jul.");
		Jul.setSeries(JulVal);
		yearlyValues.add(Jul);

		NameSeriasPair aug = new NameSeriasPair();
		ArrayList<NameValuePair> augVal = new ArrayList<>();
		NameValuePair augT = new NameValuePair();
		augT.setName("Target");
		augT.setValue(250000);
		augVal.add(augT);
		NameValuePair augA = new NameValuePair();
		augA.setName("Actual");
		augA.setValue(225000);
		augVal.add(augA);
		aug.setName("Aug.");
		aug.setSeries(augVal);
		yearlyValues.add(aug);

		NameSeriasPair sep = new NameSeriasPair();
		ArrayList<NameValuePair> sepVal = new ArrayList<>();
		NameValuePair sepT = new NameValuePair();
		sepT.setName("Target");
		sepT.setValue(250000);
		sepVal.add(sepT);
		NameValuePair sepA = new NameValuePair();
		sepA.setName("Actual");
		sepA.setValue(147000);
		sepVal.add(sepA);
		sep.setName("Sep.");
		sep.setSeries(sepVal);
		yearlyValues.add(sep);

		NameSeriasPair oct = new NameSeriasPair();
		ArrayList<NameValuePair> octVal = new ArrayList<>();
		NameValuePair octT = new NameValuePair();
		octT.setName("Target");
		octT.setValue(150000);
		octVal.add(octT);
		oct.setName("Oct.");
		oct.setSeries(octVal);
		yearlyValues.add(oct);

		NameSeriasPair nov = new NameSeriasPair();
		ArrayList<NameValuePair> novVal = new ArrayList<>();
		NameValuePair novT = new NameValuePair();
		novT.setName("Target");
		novT.setValue(200000);
		novVal.add(novT);
		nov.setName("Nov.");
		nov.setSeries(novVal);
		yearlyValues.add(nov);

		NameSeriasPair dec = new NameSeriasPair();
		ArrayList<NameValuePair> decVal = new ArrayList<>();
		NameValuePair decT = new NameValuePair();
		decT.setName("Target");
		decT.setValue(170000);
		decVal.add(decT);
		dec.setName("Dec.");
		dec.setSeries(decVal);
		yearlyValues.add(dec);

		return yearlyValues;
	}

	private ArrayList<NameSeriasPair> getNOP() {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair target = new NameSeriasPair();
		NameSeriasPair commitment = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();

		NameValuePair janA = new NameValuePair();
		janA.setName("Jan.");
		janA.setValue(213000);
		monthA.add(janA);
		NameValuePair febA = new NameValuePair();
		febA.setName("Feb.");
		febA.setValue(234000);
		monthA.add(febA);
		NameValuePair marA = new NameValuePair();
		marA.setName("Mar.");
		marA.setValue(246000);
		monthA.add(marA);
		NameValuePair aprA = new NameValuePair();
		aprA.setName("Apr.");
		aprA.setValue(246000);
		monthA.add(aprA);
		NameValuePair mayA = new NameValuePair();
		mayA.setName("May.");
		mayA.setValue(254000);
		monthA.add(mayA);
		NameValuePair junA = new NameValuePair();
		junA.setName("Jun.");
		junA.setValue(148000);
		monthA.add(junA);
		NameValuePair julA = new NameValuePair();
		julA.setName("Jul.");
		julA.setValue(147000);
		monthA.add(julA);
		NameValuePair augA = new NameValuePair();
		augA.setName("Aug.");
		augA.setValue(123000);
		monthA.add(augA);
		NameValuePair sepA = new NameValuePair();
		sepA.setName("Sep.");
		sepA.setValue(145000);
		monthA.add(sepA);
		NameValuePair octA = new NameValuePair();
		octA.setName("Oct.");
		octA.setValue(155000);
		monthA.add(octA);
		NameValuePair novA = new NameValuePair();
		novA.setName("Nov.");
		novA.setValue(165000);
		monthA.add(novA);
		NameValuePair decA = new NameValuePair();
		decA.setName("Dec.");
		decA.setValue(145000);
		monthA.add(decA);

		ArrayList<NameValuePair> monthT = new ArrayList<>();

		NameValuePair janT = new NameValuePair();
		janT.setName("Jan.");
		janT.setValue(248000);
		monthT.add(janT);
		NameValuePair febT = new NameValuePair();
		febT.setName("Feb.");
		febT.setValue(249000);
		monthT.add(febT);
		NameValuePair marT = new NameValuePair();
		marT.setName("Mar.");
		marT.setValue(269000);
		monthT.add(marT);
		NameValuePair aprT = new NameValuePair();
		aprT.setName("Apr.");
		aprT.setValue(247000);
		monthT.add(aprT);
		NameValuePair mayT = new NameValuePair();
		mayT.setName("May.");
		mayT.setValue(259000);
		monthT.add(mayT);
		NameValuePair junT = new NameValuePair();
		junT.setName("Jun.");
		junT.setValue(258000);
		monthT.add(junT);
		NameValuePair julT = new NameValuePair();
		julT.setName("Jul.");
		julT.setValue(257000);
		monthT.add(julT);
		NameValuePair augT = new NameValuePair();
		augT.setName("Aug.");
		augT.setValue(213000);
		monthT.add(augT);
		NameValuePair sepT = new NameValuePair();
		sepT.setName("Sep.");
		sepT.setValue(123000);
		monthT.add(sepT);
		NameValuePair octT = new NameValuePair();
		octT.setName("Oct.");
		octT.setValue(321000);
		monthT.add(octT);
		NameValuePair novT = new NameValuePair();
		novT.setName("Nov.");
		novT.setValue(456000);
		monthT.add(novT);
		NameValuePair decT = new NameValuePair();
		decT.setName("Dec.");
		decT.setValue(145000);
		monthT.add(decT);
		
		ArrayList<NameValuePair> monthC = new ArrayList<>();

		NameValuePair janC = new NameValuePair();
		janC.setName("Jan.");
		janC.setValue(145000);
		monthC.add(janC);
		NameValuePair febC = new NameValuePair();
		febC.setName("Feb.");
		febC.setValue(189000);
		monthC.add(febC);
		NameValuePair marC = new NameValuePair();
		marC.setName("Mar.");
		marC.setValue(155000);
		monthC.add(marC);
		NameValuePair aprC = new NameValuePair();
		aprC.setName("Apr.");
		aprC.setValue(320000);
		monthC.add(aprC);
		NameValuePair mayC = new NameValuePair();
		mayC.setName("May.");
		mayC.setValue(146000);
		monthC.add(mayC);
		NameValuePair junC = new NameValuePair();
		junC.setName("Jun.");
		junC.setValue(152000);
		monthC.add(junC);
		NameValuePair julC = new NameValuePair();
		julC.setName("Jul.");
		julC.setValue(120000);
		monthC.add(julC);
		NameValuePair augC = new NameValuePair();
		augC.setName("Aug.");
		augC.setValue(145000);
		monthC.add(augC);
		NameValuePair sepC = new NameValuePair();
		sepC.setName("Sep.");
		sepC.setValue(250000);
		monthC.add(sepC);
		NameValuePair octC = new NameValuePair();
		octC.setName("Oct.");
		octC.setValue(180000);
		monthC.add(octC);
		NameValuePair novC = new NameValuePair();
		novC.setName("Nov.");
		novC.setValue(140000);
		monthC.add(novC);
		NameValuePair decC = new NameValuePair();
		decC.setName("Dec.");
		decC.setValue(150000);
		monthC.add(decC);

		actual.setName("Actual");
		actual.setSeries(monthA);
		target.setName("Target");
		target.setSeries(monthT);
		commitment.setName("Commitment");
		commitment.setSeries(monthC);

		yearlyValues.add(actual);
		yearlyValues.add(target);
		yearlyValues.add(commitment);

		return yearlyValues;
	}

}
