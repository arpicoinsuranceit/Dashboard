package org.arpicoinsurance.groupit.dashboard.controller;

import java.util.ArrayList;
import java.util.TreeMap;
import org.arpicoinsurance.groupit.dashboard.helper.CurPolicy;
import org.arpicoinsurance.groupit.dashboard.helper.CurProposal;
import org.arpicoinsurance.groupit.dashboard.helper.CurQuo;
import org.arpicoinsurance.groupit.dashboard.helper.DashboardData;
import org.arpicoinsurance.groupit.dashboard.helper.NOP;
import org.arpicoinsurance.groupit.dashboard.helper.Target;
import org.arpicoinsurance.groupit.dashboard.helper.Top3;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DashboardController {

	@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
	public DashboardData loadDashboardData(@PathVariable Integer id) {

		Target target = new Target();
		target.setMonthlyTotalTarget(50000.00);
		target.setCompletedMonthlyTarget(30000.00);

		TreeMap<Integer, Double> targetAllYear = new TreeMap<>();
		targetAllYear.put(10, 20000.00);
		targetAllYear.put(1, 10000.00);
		targetAllYear.put(2, 20000.00);
		targetAllYear.put(3, 30000.00);
		targetAllYear.put(4, 25000.00);
		targetAllYear.put(5, 40000.00);
		targetAllYear.put(6, 39000.00);
		targetAllYear.put(7, 10000.00);
		targetAllYear.put(8, 20000.00);
		targetAllYear.put(9, 30000.00);
		targetAllYear.put(12, 25000.00);
		targetAllYear.put(11, 40000.00);

		TreeMap<Integer, Double> targetActual = new TreeMap<>();
		targetActual.put(4, 20000.00);
		targetActual.put(1, 10000.00);
		targetActual.put(2, 20000.00);
		targetActual.put(3, 30000.00);

		target.setCalenderYearActualTarget(targetActual);
		target.setCalenderYearTarget(targetAllYear);

		///////////////////////// NOP///////////////////////////

		TreeMap<Integer, Double> policies = new TreeMap<>();
		policies.put(4, 20000.00);
		policies.put(1, 10000.00);
		policies.put(2, 20000.00);
		policies.put(3, 30000.00);

		NOP nop = new NOP();
		nop.setAllPolicyCount(156);
		nop.setPolicies(policies);

		///////////////////////// TOP 3 UnitLeaders////////////////////////////////

		ArrayList<Top3> unitLeaders = new ArrayList<>();
		Top3 top1 = new Top3();
		top1.setPosition(1);
		top1.setCode("9090");
		top1.setBranch("colombo");
		top1.setName("Harindi");
		top1.setProfile("abc.png");

		Top3 top2 = new Top3();
		top2.setPosition(2);
		top2.setCode("9560");
		top2.setBranch("colombo");
		top2.setName("Ancde");
		top2.setProfile("abc.png");

		Top3 top3 = new Top3();
		top3.setPosition(3);
		top3.setCode("1010");
		top3.setBranch("colombo");
		top3.setName("Bcd");
		top3.setProfile("abc.png");

		unitLeaders.add(top1);
		unitLeaders.add(top2);
		unitLeaders.add(top3);

		///////////////////////// TOP 3 Branch////////////////////////////////

		ArrayList<Top3> branch = new ArrayList<>();
		Top3 top4 = new Top3();
		top4.setPosition(1);
		top4.setCode("9090");
		top4.setBranch("colombo");
		top4.setName("Harindi");
		top4.setProfile("abc.png");

		Top3 top5 = new Top3();
		top5.setPosition(2);
		top5.setCode("9560");
		top5.setBranch("colombo");
		top5.setName("Ancde");
		top5.setProfile("abc.png");

		Top3 top6 = new Top3();
		top6.setPosition(3);
		top6.setCode("1010");
		top6.setBranch("colombo");
		top6.setName("Bcd");
		top6.setProfile("abc.png");

		branch.add(top4);
		branch.add(top5);
		branch.add(top6);

		////////////////////////// Policies/////////////////////////////////

		ArrayList<CurPolicy> curMonthPolicies = new ArrayList<>();
		CurPolicy curPolicy = new CurPolicy();
		curPolicy.setName("Abc def");
		curPolicy.setPolicyNo("0023");
		curPolicy.setMobile("077777777");
		curPolicy.setPremium(10000.00);
		curPolicy.setProductName("INVP");
		
		curMonthPolicies.add(curPolicy);

		////////////////////////// Proposals/////////////////////////////////

		ArrayList<CurProposal> curMonthProposals = new ArrayList<>();
		CurProposal curProposal=new CurProposal();
		curProposal.setName("Abc def");
		curProposal.setProposalNo("0023");
		curProposal.setStatus("Pending");
		curProposal.setPremium(10000.00);
		curProposal.setProductName("INVP");
		
		curMonthProposals.add(curProposal);

		////////////////////////// Policies/////////////////////////////////

		ArrayList<CurQuo> curQuos = new ArrayList<>();
		CurQuo curQuo=new CurQuo();
		curQuo.setName("Abc def");
		curQuo.setQuoNum("001");
		curQuo.setMobile("077777777");
		curQuo.setPremium(10000.00);
		curQuo.setProductName("INVP");
		
		curQuos.add(curQuo);

		////////////////////////////////////////////////

		DashboardData dashboardData = new DashboardData();
		dashboardData.setUnitLeaders(unitLeaders);
		dashboardData.setBranch(branch);
		dashboardData.setNop(nop);
		dashboardData.setTarget(target);
		dashboardData.setCurMonthPolicies(curMonthPolicies);
		dashboardData.setCurMonthProposals(curMonthProposals);
		dashboardData.setCurMonthQuos(curQuos);

		return dashboardData;
	}

}
