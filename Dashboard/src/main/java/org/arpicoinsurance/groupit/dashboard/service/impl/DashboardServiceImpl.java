package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.DashboardTypeDao;
import org.arpicoinsurance.groupit.dashboard.dao.PolicyDataDao;
import org.arpicoinsurance.groupit.dashboard.dao.TargetActualDao;
import org.arpicoinsurance.groupit.dashboard.dao.TargetCommitmentActualDao;
import org.arpicoinsurance.groupit.dashboard.dao.Top3Dao;
import org.arpicoinsurance.groupit.dashboard.dto.AgentAchievement;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DuePolicies;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.dto.MonthlyTarget;
import org.arpicoinsurance.groupit.dashboard.dto.NameSeriasPair;
import org.arpicoinsurance.groupit.dashboard.dto.NameValuePair;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.PolicySummary;
import org.arpicoinsurance.groupit.dashboard.dto.TargetCommitmentActual;
import org.arpicoinsurance.groupit.dashboard.dto.Top3;
import org.arpicoinsurance.groupit.dashboard.dto.UNLAchievement;
import org.arpicoinsurance.groupit.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	TargetActualDao targetActualDao;

	@Autowired
	DashboardTypeDao dashboardTypeDao;

	@Autowired
	TargetCommitmentActualDao targetCommitmentActualDao;

	@Autowired
	PolicyDataDao policyDataDao;

	@Autowired
	Top3Dao top3Dao;

	@Override
	public MainRespDto getDashboard(String userCode) throws Exception {
		// System.out.println(userCode);
		DashboardPara dashboardPara = dashboardTypeDao.getDashboardPara(userCode);

		AgentAchievement agentAchievement = targetActualDao.getAgentAchievement(dashboardPara);
		List<AgentAchievement> agentAchievements = targetActualDao.getAgentAchievements(dashboardPara);

		List<DuePolicies> duePolicies = policyDataDao.getDuePolicies(dashboardPara);
		Integer currentMonthNOP = policyDataDao.getCurrentMonthNOP(dashboardPara);
		// System.out.println(currentMonthNOP+" - currentMonthNOP");
		List<PendingPolicies> pendingPolicies = policyDataDao.getPendingPolicies(dashboardPara);

		dashboardPara.setDashtype("BRANCH");
		dashboardPara.setDashpara("BOR");
		List<TargetCommitmentActual> targetCommitmentActualNOPList = targetCommitmentActualDao
				.getCurrentYearNOP(dashboardPara);
		List<TargetCommitmentActual> targetCommitmentActualGWPList = targetCommitmentActualDao
				.getCurrentYearGWP(dashboardPara);
		List<TargetCommitmentActual> targetCommitmentActualMCFPList = targetCommitmentActualDao
				.getCurrentYearMCFP(dashboardPara);
		List<TargetCommitmentActual> targetCommitmentActualFYPAchList = targetCommitmentActualDao
				.getCurrentYearFYPAch(dashboardPara);
		List<TargetCommitmentActual> targetCommitmentActualRTNY1List = targetCommitmentActualDao
				.getCurrentYearRTNY1(dashboardPara);

		MainRespDto mainRespDto = new MainRespDto();

		MonthlyTarget monthlyTarget = new MonthlyTarget();

		monthlyTarget.setActual(agentAchievement.getTrgach().intValue());
		monthlyTarget.setTarget(agentAchievement.getTrgamt().intValue());
		Double targetExpand = agentAchievement.getTrgamt() + (agentAchievement.getTrgamt() / 3);
		monthlyTarget.setTargetExpand(targetExpand.intValue());

		// mainRespDto.setYearlyTarget(getYearlyValues(agentAchievement,agentAchievements));

		mainRespDto.setNop(processTargetCommitmentActualChart(targetCommitmentActualNOPList));
		mainRespDto.setGwp(processTargetCommitmentActualChart(targetCommitmentActualGWPList));
		mainRespDto.setMcfp(processTargetCommitmentActualChart(targetCommitmentActualMCFPList));
		mainRespDto.setRiny1(processCommitmentActualChart(targetCommitmentActualRTNY1List));
		mainRespDto.setFyp(processTargetCommitmentActualChart(targetCommitmentActualFYPAchList));

		/////////////////// Need to implement/////////////////////////////////////

		mainRespDto.setMonthlyTarget(monthlyTarget);
		mainRespDto.setMonthlyTargetFYP(monthlyTarget);
		mainRespDto.setMonthlyTargetGWP(monthlyTarget);
		mainRespDto.setMonthlyTargetMCFP(monthlyTarget);
		mainRespDto.setMonthlyTargetNOP(monthlyTarget);

		mainRespDto.setNopC(processTargetCommitmentActualCumulativeChart(targetCommitmentActualNOPList));
		mainRespDto.setGwpC(processTargetCommitmentActualCumulativeChart(targetCommitmentActualGWPList));
		mainRespDto.setMcfpC(processTargetCommitmentActualCumulativeChart(targetCommitmentActualMCFPList));
		mainRespDto.setFypC(processTargetCommitmentActualCumulativeChart(targetCommitmentActualFYPAchList));

		mainRespDto.setPolicySummery(new ArrayList<>());

		ArrayList<NameValuePair> arrayList = new ArrayList<>();

		NameValuePair nameValuePair1 = new NameValuePair();
		nameValuePair1.setName("Policy Enfored");
		nameValuePair1.setValue(5);

		arrayList.add(nameValuePair1);

		NameValuePair nameValuePair2 = new NameValuePair();
		nameValuePair2.setName("Amount");
		nameValuePair2.setValue(5000);

		arrayList.add(nameValuePair2);

		NameValuePair nameValuePair3 = new NameValuePair();
		nameValuePair3.setName("Policy Lapsed");
		nameValuePair3.setValue(5);

		arrayList.add(nameValuePair3);

		NameValuePair nameValuePair4 = new NameValuePair();
		nameValuePair4.setName("Amount");
		nameValuePair4.setValue(50000);

		arrayList.add(nameValuePair4);

		NameValuePair nameValuePair5 = new NameValuePair();
		nameValuePair5.setName("Policy Lapsed P.");
		nameValuePair5.setValue(5);

		arrayList.add(nameValuePair5);

		NameValuePair nameValuePair6 = new NameValuePair();
		nameValuePair6.setName("Amount");
		nameValuePair6.setValue(5);

		arrayList.add(nameValuePair6);

		mainRespDto.setPolicySummery(arrayList);

		////////////////////////////////////////////////////////

		// mainRespDto.setIc(getTop3());
		// mainRespDto.setUl(getTop3());
		// mainRespDto.setRegion(getTop3());
		// mainRespDto.setBranch(getTop3());
		// mainRespDto.setZone(getTop3());
		mainRespDto.setDuePolicieList(duePolicies);
		mainRespDto.setPendingPolList(pendingPolicies);
		return mainRespDto;
	}

	private ArrayList<NameSeriasPair> getYearlyValues(List<AgentAchievement> achievements,
			DashboardPara dashboardPara) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		AgentAchievement achievement = new AgentAchievement(0.0, 0.0);

		for (int i = 0; i < 12; i++) {
			// System.out.println(i+" - "+dashboardPara.getDashmonth());
			if (i == 0) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair jan = new NameSeriasPair();
				ArrayList<NameValuePair> janVal = new ArrayList<>();
				NameValuePair janT = new NameValuePair();
				janT.setName("Target");
				janT.setValue(achievement.getTrgamt().intValue());
				janVal.add(janT);
				NameValuePair janA = new NameValuePair();
				janA.setName("Actual");
				janA.setValue(achievement.getTrgach().intValue());
				janVal.add(janA);
				jan.setName("Jan.");
				jan.setSeries(janVal);
				yearlyValues.add(jan);
				achievement.setTrgach(0.0);

			} else if (i == 1) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair feb = new NameSeriasPair();
				ArrayList<NameValuePair> febVal = new ArrayList<>();
				NameValuePair febT = new NameValuePair();
				febT.setName("Target");
				febT.setValue(achievement.getTrgamt().intValue());
				febVal.add(febT);
				NameValuePair febA = new NameValuePair();
				febA.setName("Actual");
				febA.setValue(achievement.getTrgach().intValue());
				febVal.add(febA);
				feb.setName("Feb.");
				feb.setSeries(febVal);
				yearlyValues.add(feb);
				achievement.setTrgach(0.0);

			} else if (i == 2) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair mar = new NameSeriasPair();
				ArrayList<NameValuePair> marVal = new ArrayList<>();
				NameValuePair marT = new NameValuePair();
				marT.setName("Target");
				marT.setValue(achievement.getTrgamt().intValue());
				marVal.add(marT);
				NameValuePair marA = new NameValuePair();
				marA.setName("Actual");
				marA.setValue(achievement.getTrgach().intValue());
				marVal.add(marA);
				mar.setName("Mar.");
				mar.setSeries(marVal);
				yearlyValues.add(mar);
				achievement.setTrgach(0.0);

			} else if (i == 3) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair apr = new NameSeriasPair();
				ArrayList<NameValuePair> aprVal = new ArrayList<>();
				NameValuePair aprT = new NameValuePair();
				aprT.setName("Target");
				aprT.setValue(achievement.getTrgamt().intValue());
				aprVal.add(aprT);
				NameValuePair aprA = new NameValuePair();
				aprA.setName("Actual");
				aprA.setValue(achievement.getTrgach().intValue());
				aprVal.add(aprA);
				apr.setName("Apr.");
				apr.setSeries(aprVal);
				yearlyValues.add(apr);
				achievement.setTrgach(0.0);

			} else if (i == 4) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair may = new NameSeriasPair();
				ArrayList<NameValuePair> mayVal = new ArrayList<>();
				NameValuePair mayT = new NameValuePair();
				mayT.setName("Target");
				mayT.setValue(achievement.getTrgamt().intValue());
				mayVal.add(mayT);
				NameValuePair mayA = new NameValuePair();
				mayA.setName("Actual");
				mayA.setValue(achievement.getTrgach().intValue());
				mayVal.add(mayA);
				may.setName("May");
				may.setSeries(mayVal);
				yearlyValues.add(may);
				achievement.setTrgach(0.0);

			} else if (i == 5) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair jun = new NameSeriasPair();
				ArrayList<NameValuePair> junVal = new ArrayList<>();
				NameValuePair junT = new NameValuePair();
				junT.setName("Target");
				junT.setValue(achievement.getTrgamt().intValue());
				junVal.add(junT);
				NameValuePair junA = new NameValuePair();
				junA.setName("Actual");
				junA.setValue(achievement.getTrgach().intValue());
				junVal.add(junA);
				jun.setName("Jun.");
				jun.setSeries(junVal);
				yearlyValues.add(jun);
				achievement.setTrgach(0.0);

			} else if (i == 6) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair Jul = new NameSeriasPair();
				ArrayList<NameValuePair> JulVal = new ArrayList<>();
				NameValuePair JulT = new NameValuePair();
				JulT.setName("Target");
				JulT.setValue(achievement.getTrgamt().intValue());
				JulVal.add(JulT);
				NameValuePair JulA = new NameValuePair();
				JulA.setName("Actual");
				JulA.setValue(achievement.getTrgach().intValue());
				JulVal.add(JulA);
				Jul.setName("Jul.");
				Jul.setSeries(JulVal);
				yearlyValues.add(Jul);
				achievement.setTrgach(0.0);

			} else if (i == 7) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair aug = new NameSeriasPair();
				ArrayList<NameValuePair> augVal = new ArrayList<>();
				NameValuePair augT = new NameValuePair();
				augT.setName("Target");
				augT.setValue(achievement.getTrgamt().intValue());
				augVal.add(augT);
				NameValuePair augA = new NameValuePair();
				augA.setName("Actual");
				augA.setValue(achievement.getTrgach().intValue());
				augVal.add(augA);
				aug.setName("Aug.");
				aug.setSeries(augVal);
				yearlyValues.add(aug);
				achievement.setTrgach(0.0);

			} else if (i == 8) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair sep = new NameSeriasPair();
				ArrayList<NameValuePair> sepVal = new ArrayList<>();
				NameValuePair sepT = new NameValuePair();
				sepT.setName("Target");
				sepT.setValue(achievement.getTrgamt().intValue());
				sepVal.add(sepT);
				NameValuePair sepA = new NameValuePair();
				sepA.setName("Actual");
				sepA.setValue(achievement.getTrgach().intValue());
				sepVal.add(sepA);
				sep.setName("Sep.");
				sep.setSeries(sepVal);
				yearlyValues.add(sep);
				achievement.setTrgach(0.0);

			} else if (i == 9) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair oct = new NameSeriasPair();
				ArrayList<NameValuePair> octVal = new ArrayList<>();
				NameValuePair octT = new NameValuePair();
				octT.setName("Target");
				octT.setValue(achievement.getTrgamt().intValue());
				octVal.add(octT);
				NameValuePair octA = new NameValuePair();
				octA.setName("Actual");
				octA.setValue(achievement.getTrgach().intValue());
				octVal.add(octA);
				oct.setName("Oct.");
				oct.setSeries(octVal);
				yearlyValues.add(oct);
				achievement.setTrgach(0.0);

			} else if (i == 10) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair nov = new NameSeriasPair();
				ArrayList<NameValuePair> novVal = new ArrayList<>();
				NameValuePair novT = new NameValuePair();
				novT.setName("Target");
				novT.setValue(achievement.getTrgamt().intValue());
				novVal.add(novT);
				NameValuePair novA = new NameValuePair();
				novA.setName("Actual");
				novA.setValue(achievement.getTrgach().intValue());
				novVal.add(novA);
				nov.setName("Nov.");
				nov.setSeries(novVal);
				yearlyValues.add(nov);
				achievement.setTrgach(0.0);

			} else if (i == 11) {
				if (achievements != null && i < achievements.size()) {
					achievement = achievements.get(i);
				} else if ((i + 1) == dashboardPara.getDashmonth() && achievement.getTrgach() == 0.0) {
					achievement.setTrgamt(7500.0);
				}

				NameSeriasPair dec = new NameSeriasPair();
				ArrayList<NameValuePair> decVal = new ArrayList<>();
				NameValuePair decT = new NameValuePair();
				decT.setName("Target");
				decT.setValue(achievement.getTrgamt().intValue());
				decVal.add(decT);
				NameValuePair decA = new NameValuePair();
				decA.setName("Actual");
				decA.setValue(achievement.getTrgach().intValue());
				decVal.add(decA);
				dec.setName("Dec.");
				dec.setSeries(decVal);
				yearlyValues.add(dec);
				achievement.setTrgach(0.0);

			}
		}

		return yearlyValues;
	}

	private ArrayList<NameSeriasPair> processTargetCommitmentActualChart(
			List<TargetCommitmentActual> targetCommitmentActualNOPList) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair target = new NameSeriasPair();
		NameSeriasPair commitment = new NameSeriasPair();
		NameSeriasPair carryForward = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();
		ArrayList<NameValuePair> monthT = new ArrayList<>();
		ArrayList<NameValuePair> monthC = new ArrayList<>();
		ArrayList<NameValuePair> monthCF = new ArrayList<>();

		if (targetCommitmentActualNOPList != null) {
			Double trgcfa = 0.0;
			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualNOPList) {

				if (targetCommitmentActual.getMonth() == 1) {

					NameValuePair janT = new NameValuePair();
					janT.setName("Jan.");
					janT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(janT);

					NameValuePair janC = new NameValuePair();
					janC.setName("Jan.");
					janC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(janC);

					NameValuePair janA = new NameValuePair();
					janA.setName("Jan.");
					janA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(janA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair janCF = new NameValuePair();
					janCF.setName("Jan.");
					janCF.setValue(trgcfa.intValue());
					monthCF.add(janCF);

				} else if (targetCommitmentActual.getMonth() == 2) {

					NameValuePair febT = new NameValuePair();
					febT.setName("Feb.");
					febT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(febT);

					NameValuePair febC = new NameValuePair();
					febC.setName("Feb.");
					febC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(febC);

					NameValuePair febA = new NameValuePair();
					febA.setName("Feb.");
					febA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(febA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair febCF = new NameValuePair();
					febCF.setName("Feb.");
					febCF.setValue(trgcfa.intValue());
					monthCF.add(febCF);

				} else if (targetCommitmentActual.getMonth() == 3) {

					NameValuePair marT = new NameValuePair();
					marT.setName("Mar.");
					marT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(marT);

					NameValuePair marC = new NameValuePair();
					marC.setName("Mar.");
					marC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(marC);

					NameValuePair marA = new NameValuePair();
					marA.setName("Mar.");
					marA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(marA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair marCF = new NameValuePair();
					marCF.setName("Mar.");
					marCF.setValue(trgcfa.intValue());
					monthCF.add(marCF);

				} else if (targetCommitmentActual.getMonth() == 4) {

					NameValuePair aprT = new NameValuePair();
					aprT.setName("Apr.");
					aprT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(aprT);

					NameValuePair aprC = new NameValuePair();
					aprC.setName("Apr.");
					aprC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(aprC);

					NameValuePair aprA = new NameValuePair();
					aprA.setName("Apr.");
					aprA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(aprA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair aprCF = new NameValuePair();
					aprCF.setName("Apr.");
					aprCF.setValue(trgcfa.intValue());
					monthCF.add(aprCF);

				} else if (targetCommitmentActual.getMonth() == 5) {

					NameValuePair mayT = new NameValuePair();
					mayT.setName("May.");
					mayT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(mayT);

					NameValuePair mayC = new NameValuePair();
					mayC.setName("May.");
					mayC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(mayC);

					NameValuePair mayA = new NameValuePair();
					mayA.setName("May.");
					mayA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(mayA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair mayCF = new NameValuePair();
					mayCF.setName("May.");
					mayCF.setValue(trgcfa.intValue());
					monthCF.add(mayCF);

				} else if (targetCommitmentActual.getMonth() == 6) {

					NameValuePair junT = new NameValuePair();
					junT.setName("Jun.");
					junT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(junT);

					NameValuePair junC = new NameValuePair();
					junC.setName("Jun.");
					junC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(junC);

					NameValuePair junA = new NameValuePair();
					junA.setName("Jun.");
					junA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(junA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair junCF = new NameValuePair();
					junCF.setName("Jun.");
					junCF.setValue(trgcfa.intValue());
					monthCF.add(junCF);

				} else if (targetCommitmentActual.getMonth() == 7) {

					NameValuePair julT = new NameValuePair();
					julT.setName("Jul.");
					julT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(julT);

					NameValuePair julC = new NameValuePair();
					julC.setName("Jul.");
					julC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(julC);

					NameValuePair julA = new NameValuePair();
					julA.setName("Jul.");
					julA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(julA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair julCF = new NameValuePair();
					julCF.setName("Jul.");
					julCF.setValue(trgcfa.intValue());
					monthCF.add(julCF);

				} else if (targetCommitmentActual.getMonth() == 8) {

					NameValuePair augT = new NameValuePair();
					augT.setName("Aug.");
					augT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(augT);

					NameValuePair augC = new NameValuePair();
					augC.setName("Aug.");
					augC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(augC);

					NameValuePair augA = new NameValuePair();
					augA.setName("Aug.");
					augA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(augA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair augCF = new NameValuePair();
					augCF.setName("Aug.");
					augCF.setValue(trgcfa.intValue());
					monthCF.add(augCF);

				} else if (targetCommitmentActual.getMonth() == 9) {

					NameValuePair sepT = new NameValuePair();
					sepT.setName("Sep.");
					sepT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(sepT);

					NameValuePair sepC = new NameValuePair();
					sepC.setName("Sep.");
					sepC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(sepC);

					NameValuePair sepA = new NameValuePair();
					sepA.setName("Sep.");
					sepA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(sepA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair sepCF = new NameValuePair();
					sepCF.setName("Sep.");
					sepCF.setValue(trgcfa.intValue());
					monthCF.add(sepCF);

				} else if (targetCommitmentActual.getMonth() == 10) {

					NameValuePair octT = new NameValuePair();
					octT.setName("Oct.");
					octT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(octT);

					NameValuePair octC = new NameValuePair();
					octC.setName("Oct.");
					octC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(octC);

					NameValuePair octA = new NameValuePair();
					octA.setName("Oct.");
					octA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(octA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair octCF = new NameValuePair();
					octCF.setName("Oct.");
					octCF.setValue(trgcfa.intValue());
					monthCF.add(octCF);

				} else if (targetCommitmentActual.getMonth() == 11) {

					NameValuePair novT = new NameValuePair();
					novT.setName("Nov.");
					novT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(novT);

					NameValuePair novC = new NameValuePair();
					novC.setName("Nov.");
					novC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(novC);

					NameValuePair novA = new NameValuePair();
					novA.setName("Nov.");
					novA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(novA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair novCF = new NameValuePair();
					novCF.setName("Nov.");
					novCF.setValue(trgcfa.intValue());
					monthCF.add(novCF);

				} else if (targetCommitmentActual.getMonth() == 12) {

					NameValuePair decT = new NameValuePair();
					decT.setName("Dec.");
					decT.setValue(targetCommitmentActual.getTarget().intValue());
					monthT.add(decT);

					NameValuePair decC = new NameValuePair();
					decC.setName("Dec.");
					decC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(decC);

					NameValuePair decA = new NameValuePair();
					decA.setName("Dec.");
					decA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(decA);

					trgcfa = (targetCommitmentActual.getTarget()
							- (targetCommitmentActual.getActual() > 0 ? targetCommitmentActual.getActual()
									: targetCommitmentActual.getTarget()));
					NameValuePair decCF = new NameValuePair();
					decCF.setName("Dec.");
					decCF.setValue(trgcfa.intValue());
					monthCF.add(decCF);

				}
			}
		}

		actual.setName("Actual");
		actual.setSeries(monthA);
		target.setName("Target");
		target.setSeries(monthT);
		commitment.setName("Commitment");
		commitment.setSeries(monthC);
		carryForward.setName("Carry Forward");
		carryForward.setSeries(monthCF);

		yearlyValues.add(target);
		yearlyValues.add(commitment);
		yearlyValues.add(actual);
		yearlyValues.add(carryForward);

		return yearlyValues;
	}

	private ArrayList<NameSeriasPair> processTargetCommitmentActualCumulativeChart(
			List<TargetCommitmentActual> targetCommitmentActualNOPList) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair target = new NameSeriasPair();
		NameSeriasPair commitment = new NameSeriasPair();
		NameSeriasPair carryForward = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();
		ArrayList<NameValuePair> monthT = new ArrayList<>();
		ArrayList<NameValuePair> monthC = new ArrayList<>();
		ArrayList<NameValuePair> monthCF = new ArrayList<>();

		if (targetCommitmentActualNOPList != null) {
			Double actualAmount = 0.0;
			Double targetAmount = 0.0;
			Double commitmentAmount = 0.0;
			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualNOPList) {

				if (targetCommitmentActual.getMonth() == 1) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair janT = new NameValuePair();
					janT.setName("Jan.");
					janT.setValue(targetAmount.intValue());
					monthT.add(janT);

					NameValuePair janC = new NameValuePair();
					janC.setName("Jan.");
					janC.setValue(commitmentAmount.intValue());
					monthC.add(janC);

					NameValuePair janA = new NameValuePair();
					janA.setName("Jan.");
					janA.setValue(actualAmount.intValue());
					monthA.add(janA);

					NameValuePair janCF = new NameValuePair();
					janCF.setName("Jan.");
					janCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(janCF);

				} else if (targetCommitmentActual.getMonth() == 2) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair febT = new NameValuePair();
					febT.setName("Feb.");
					febT.setValue(targetAmount.intValue());
					monthT.add(febT);

					NameValuePair febC = new NameValuePair();
					febC.setName("Feb.");
					febC.setValue(commitmentAmount.intValue());
					monthC.add(febC);

					NameValuePair febA = new NameValuePair();
					febA.setName("Feb.");
					febA.setValue(actualAmount.intValue());
					monthA.add(febA);

					NameValuePair febCF = new NameValuePair();
					febCF.setName("Feb.");
					febCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(febCF);

				} else if (targetCommitmentActual.getMonth() == 3) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair marT = new NameValuePair();
					marT.setName("Mar.");
					marT.setValue(targetAmount.intValue());
					monthT.add(marT);

					NameValuePair marC = new NameValuePair();
					marC.setName("Mar.");
					marC.setValue(commitmentAmount.intValue());
					monthC.add(marC);

					NameValuePair marA = new NameValuePair();
					marA.setName("Mar.");
					marA.setValue(actualAmount.intValue());
					monthA.add(marA);

					NameValuePair marCF = new NameValuePair();
					marCF.setName("Mar.");
					marCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(marCF);

				} else if (targetCommitmentActual.getMonth() == 4) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair aprT = new NameValuePair();
					aprT.setName("Apr.");
					aprT.setValue(targetAmount.intValue());
					monthT.add(aprT);

					NameValuePair aprC = new NameValuePair();
					aprC.setName("Apr.");
					aprC.setValue(commitmentAmount.intValue());
					monthC.add(aprC);

					NameValuePair aprA = new NameValuePair();
					aprA.setName("Apr.");
					aprA.setValue(actualAmount.intValue());
					monthA.add(aprA);

					NameValuePair arpCF = new NameValuePair();
					arpCF.setName("Apr.");
					arpCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(arpCF);

				} else if (targetCommitmentActual.getMonth() == 5) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair mayT = new NameValuePair();
					mayT.setName("May.");
					mayT.setValue(targetAmount.intValue());
					monthT.add(mayT);

					NameValuePair mayC = new NameValuePair();
					mayC.setName("May.");
					mayC.setValue(commitmentAmount.intValue());
					monthC.add(mayC);

					NameValuePair mayA = new NameValuePair();
					mayA.setName("May.");
					mayA.setValue(actualAmount.intValue());
					monthA.add(mayA);

					NameValuePair mayCF = new NameValuePair();
					mayCF.setName("May.");
					mayCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(mayCF);

				} else if (targetCommitmentActual.getMonth() == 6) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair junT = new NameValuePair();
					junT.setName("Jun.");
					junT.setValue(targetAmount.intValue());
					monthT.add(junT);

					NameValuePair junC = new NameValuePair();
					junC.setName("Jun.");
					junC.setValue(commitmentAmount.intValue());
					monthC.add(junC);

					NameValuePair junA = new NameValuePair();
					junA.setName("Jun.");
					junA.setValue(actualAmount.intValue());
					monthA.add(junA);

					NameValuePair junCF = new NameValuePair();
					junCF.setName("Jun.");
					junCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(junCF);

				} else if (targetCommitmentActual.getMonth() == 7) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair julT = new NameValuePair();
					julT.setName("Jul.");
					julT.setValue(targetAmount.intValue());
					monthT.add(julT);

					NameValuePair julC = new NameValuePair();
					julC.setName("Jul.");
					julC.setValue(commitmentAmount.intValue());
					monthC.add(julC);

					NameValuePair julA = new NameValuePair();
					julA.setName("Jul.");
					julA.setValue(actualAmount.intValue());
					monthA.add(julA);

					NameValuePair julCF = new NameValuePair();
					julCF.setName("Jul.");
					julCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(julCF);

				} else if (targetCommitmentActual.getMonth() == 8) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair augT = new NameValuePair();
					augT.setName("Aug.");
					augT.setValue(targetAmount.intValue());
					monthT.add(augT);

					NameValuePair augC = new NameValuePair();
					augC.setName("Aug.");
					augC.setValue(commitmentAmount.intValue());
					monthC.add(augC);

					NameValuePair augA = new NameValuePair();
					augA.setName("Aug.");
					augA.setValue(actualAmount.intValue());
					monthA.add(augA);

					NameValuePair augCF = new NameValuePair();
					augCF.setName("Aug.");
					augCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(augCF);

				} else if (targetCommitmentActual.getMonth() == 9) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair sepT = new NameValuePair();
					sepT.setName("Sep.");
					sepT.setValue(targetAmount.intValue());
					monthT.add(sepT);

					NameValuePair sepC = new NameValuePair();
					sepC.setName("Sep.");
					sepC.setValue(commitmentAmount.intValue());
					monthC.add(sepC);

					NameValuePair sepA = new NameValuePair();
					sepA.setName("Sep.");
					sepA.setValue(actualAmount.intValue());
					monthA.add(sepA);

					NameValuePair sepCF = new NameValuePair();
					sepCF.setName("Sep.");
					sepCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(sepCF);

				} else if (targetCommitmentActual.getMonth() == 10) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair octT = new NameValuePair();
					octT.setName("Oct.");
					octT.setValue(targetAmount.intValue());
					monthT.add(octT);

					NameValuePair octC = new NameValuePair();
					octC.setName("Oct.");
					octC.setValue(commitmentAmount.intValue());
					monthC.add(octC);

					NameValuePair octA = new NameValuePair();
					octA.setName("Oct.");
					octA.setValue(actualAmount.intValue());
					monthA.add(octA);

					NameValuePair octCF = new NameValuePair();
					octCF.setName("Oct.");
					octCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(octCF);

				} else if (targetCommitmentActual.getMonth() == 11) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair novT = new NameValuePair();
					novT.setName("Nov.");
					novT.setValue(targetAmount.intValue());
					monthT.add(novT);

					NameValuePair novC = new NameValuePair();
					novC.setName("Nov.");
					novC.setValue(commitmentAmount.intValue());
					monthC.add(novC);

					NameValuePair novA = new NameValuePair();
					novA.setName("Nov.");
					novA.setValue(actualAmount.intValue());
					monthA.add(novA);

					NameValuePair novCF = new NameValuePair();
					novCF.setName("Nov.");
					novCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(novCF);

				} else if (targetCommitmentActual.getMonth() == 12) {

					targetAmount = targetAmount + targetCommitmentActual.getTarget();
					commitmentAmount = commitmentAmount + targetCommitmentActual.getCommitment();
					actualAmount = actualAmount + targetCommitmentActual.getActual();

					NameValuePair decT = new NameValuePair();
					decT.setName("Dec.");
					decT.setValue(targetAmount.intValue());
					monthT.add(decT);

					NameValuePair decC = new NameValuePair();
					decC.setName("Dec.");
					decC.setValue(commitmentAmount.intValue());
					monthC.add(decC);

					NameValuePair decA = new NameValuePair();
					decA.setName("Dec.");
					decA.setValue(actualAmount.intValue());
					monthA.add(decA);

					NameValuePair decCF = new NameValuePair();
					decCF.setName("Dec.");
					decCF.setValue(
							targetCommitmentActual.getActual() > 0 ? (targetAmount.intValue() - actualAmount.intValue())
									: 0);
					monthCF.add(decCF);

				}
			}
		}

		actual.setName("Actual");
		actual.setSeries(monthA);
		target.setName("Target");
		target.setSeries(monthT);
		commitment.setName("Commitment");
		commitment.setSeries(monthC);
		carryForward.setName("Carry Forward");
		carryForward.setSeries(monthCF);

		yearlyValues.add(target);
		yearlyValues.add(commitment);
		yearlyValues.add(actual);
		yearlyValues.add(carryForward);

		return yearlyValues;
	}

	private ArrayList<NameSeriasPair> processCommitmentActualChart(
			List<TargetCommitmentActual> targetCommitmentActualNOPList) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair commitment = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();
		ArrayList<NameValuePair> monthC = new ArrayList<>();
		if (targetCommitmentActualNOPList != null) {
			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualNOPList) {

				if (targetCommitmentActual.getMonth() == 1) {

					NameValuePair janC = new NameValuePair();
					janC.setName("Jan.");
					janC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(janC);

					NameValuePair janA = new NameValuePair();
					janA.setName("Jan.");
					janA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(janA);

				} else if (targetCommitmentActual.getMonth() == 2) {

					NameValuePair febC = new NameValuePair();
					febC.setName("Feb.");
					febC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(febC);

					NameValuePair febA = new NameValuePair();
					febA.setName("Feb.");
					febA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(febA);

				} else if (targetCommitmentActual.getMonth() == 3) {

					NameValuePair marC = new NameValuePair();
					marC.setName("Mar.");
					marC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(marC);

					NameValuePair marA = new NameValuePair();
					marA.setName("Mar.");
					marA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(marA);

				} else if (targetCommitmentActual.getMonth() == 4) {

					NameValuePair aprC = new NameValuePair();
					aprC.setName("Apr.");
					aprC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(aprC);

					NameValuePair aprA = new NameValuePair();
					aprA.setName("Apr.");
					aprA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(aprA);

				} else if (targetCommitmentActual.getMonth() == 5) {

					NameValuePair mayC = new NameValuePair();
					mayC.setName("May.");
					mayC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(mayC);

					NameValuePair mayA = new NameValuePair();
					mayA.setName("May.");
					mayA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(mayA);

				} else if (targetCommitmentActual.getMonth() == 6) {

					NameValuePair junC = new NameValuePair();
					junC.setName("Jun.");
					junC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(junC);

					NameValuePair junA = new NameValuePair();
					junA.setName("Jun.");
					junA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(junA);

				} else if (targetCommitmentActual.getMonth() == 7) {

					NameValuePair julC = new NameValuePair();
					julC.setName("Jul.");
					julC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(julC);

					NameValuePair julA = new NameValuePair();
					julA.setName("Jul.");
					julA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(julA);

				} else if (targetCommitmentActual.getMonth() == 8) {

					NameValuePair augC = new NameValuePair();
					augC.setName("Aug.");
					augC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(augC);

					NameValuePair augA = new NameValuePair();
					augA.setName("Aug.");
					augA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(augA);

				} else if (targetCommitmentActual.getMonth() == 9) {

					NameValuePair sepC = new NameValuePair();
					sepC.setName("Sep.");
					sepC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(sepC);

					NameValuePair sepA = new NameValuePair();
					sepA.setName("Sep.");
					sepA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(sepA);

				} else if (targetCommitmentActual.getMonth() == 10) {

					NameValuePair octC = new NameValuePair();
					octC.setName("Oct.");
					octC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(octC);

					NameValuePair octA = new NameValuePair();
					octA.setName("Oct.");
					octA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(octA);

				} else if (targetCommitmentActual.getMonth() == 11) {

					NameValuePair novC = new NameValuePair();
					novC.setName("Nov.");
					novC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(novC);

					NameValuePair novA = new NameValuePair();
					novA.setName("Nov.");
					novA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(novA);

				} else if (targetCommitmentActual.getMonth() == 12) {

					NameValuePair decC = new NameValuePair();
					decC.setName("Dec.");
					decC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(decC);

					NameValuePair decA = new NameValuePair();
					decA.setName("Dec.");
					decA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(decA);

				}
			}
		}

		actual.setName("Actual");
		actual.setSeries(monthA);
		commitment.setName("Commitment");
		commitment.setSeries(monthC);

		yearlyValues.add(commitment);
		yearlyValues.add(actual);

		return yearlyValues;
	}

	@Override
	public DashboardPara getDashboardPara(String userid) throws Exception {
		Calendar calendar = Calendar.getInstance();
		DashboardPara dashboardPara = null;
		try {
			try {
				dashboardPara = dashboardTypeDao.getDashboardPara(userid);
				return dashboardPara;
			} catch (Exception e) {
				if (dashboardPara != null && dashboardPara.getDashpara() != null
						&& dashboardPara.getDashtype() != null) {
					return dashboardPara;
				} else if (dashboardTypeDao.isHo(userid).equals("HO")) {
					dashboardPara = new DashboardPara();
					dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
					dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));
					dashboardPara.setDashpara("HO");
					dashboardPara.setDashtype("HO");
					dashboardPara.setUsertype("HO");
					return dashboardPara;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));
		dashboardPara.setDashpara("Error");
		dashboardPara.setDashtype("Error");
		dashboardPara.setUsertype("Error");
		return dashboardPara;

	}

	@Override
	public List<Object> getCurrentMonthYearlyTarget(String userid, String dashpara, String usertype) throws Exception {
		List<Object> currentMonthYearlyTarget = new ArrayList<>();
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		MonthlyTarget monthlyTarget = new MonthlyTarget();
		ArrayList<NameSeriasPair> yearlyTarget = new ArrayList<NameSeriasPair>();
		monthlyTarget.setTarget(7500);
		monthlyTarget.setTargetExpand(10000);
		monthlyTarget.setActual(0);

		try {
			List<AgentAchievement> agentAchievements = targetActualDao.getAgentAchievements(dashboardPara);

			if (agentAchievements != null) {

				for (AgentAchievement agentAchievement : agentAchievements) {
					if (agentAchievement.getMonth() == dashboardPara.getDashmonth()) {
						monthlyTarget.setTarget(agentAchievement.getTrgamt().intValue());
						monthlyTarget.setActual(agentAchievement.getTrgach().intValue());
						Double targetExpand = agentAchievement.getTrgamt() + (agentAchievement.getTrgamt() / 3);
						monthlyTarget.setTargetExpand(targetExpand.intValue());
					}
				}

				yearlyTarget = getYearlyValues(agentAchievements, dashboardPara);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		currentMonthYearlyTarget.add(monthlyTarget);
		currentMonthYearlyTarget.add(yearlyTarget);
		monthlyTarget = null;
		yearlyTarget = null;

		return currentMonthYearlyTarget;
	}

	@Override
	public List<DuePolicies> getDuePolicies(String userid, String dashpara, String usertype) throws Exception {
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		return policyDataDao.getDuePolicies(dashboardPara);
	}

	@Override
	public List<PendingPolicies> getPendingPolicies(String userid, String dashpara, String usertype) throws Exception {
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		return policyDataDao.getPendingPolicies(dashboardPara);
	}

	@Override
	public List<Top3> getTopIC() throws Exception {
		return top3Dao.getTopIC();
	}

	@Override
	public List<Top3> getTopIS() throws Exception {
		return top3Dao.getTopIS();
	}

	@Override
	public List<Top3> getTopUL() throws Exception {
		return top3Dao.getTopUL();
	}

	@Override
	public List<Top3> getTopBranch() throws Exception {
		return top3Dao.getTopBranch();
	}

	@Override
	public List<Top3> getTopRegion() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Top3> getTopZone() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getCurrentMonthYearlyTargetUNL(String userid, String dashpara, String usertype)
			throws Exception {
		List<Object> currentMonthYearlyTarget = new ArrayList<>();
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		MonthlyTarget monthlyTarget = new MonthlyTarget();
		ArrayList<NameSeriasPair> yearlyTarget = new ArrayList<NameSeriasPair>();
		ArrayList<NameSeriasPair> yearlyTargetActualCF = new ArrayList<NameSeriasPair>();
		ArrayList<NameSeriasPair> yearlyTargetActualCFCumulative = new ArrayList<NameSeriasPair>();
		monthlyTarget.setTarget(0);
		monthlyTarget.setTargetExpand(0);
		monthlyTarget.setActual(0);

		try {
			List<UNLAchievement> unlAchievements = targetActualDao.getUNLAchievements(dashboardPara);

			if (unlAchievements != null) {

				for (UNLAchievement unlAchievement : unlAchievements) {
					if (unlAchievement.getMonth() == dashboardPara.getDashmonth()) {
						monthlyTarget.setTarget(
								(unlAchievement.getTrgamt().intValue() + unlAchievement.getTrgtcfa().intValue()));
						monthlyTarget.setActual(unlAchievement.getTrgach().intValue());
						Double targetExpand = unlAchievement.getTrgamt() + (unlAchievement.getTrgamt() / 3);
						monthlyTarget.setTargetExpand(targetExpand.intValue());
					}
				}

				AgentAchievement achievement = new AgentAchievement(monthlyTarget.getTarget().doubleValue(), 0.0,
						dashboardPara.getDashmonth(), dashboardPara.getDashyear());

				try {
					achievement = targetActualDao.getAgentAchievement(dashboardPara);
					monthlyTarget.setActual(achievement.getTrgach().intValue());
				} catch (Exception e) {
					e.printStackTrace();
				}

				yearlyTarget = getYearlyValuesUNL(achievement, unlAchievements);

				yearlyTargetActualCF = processTargetActualCFChartUNL(achievement, unlAchievements);

				yearlyTargetActualCFCumulative = processTargetActualCumulativeChartUNL(achievement, unlAchievements);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		currentMonthYearlyTarget.add(monthlyTarget);
		currentMonthYearlyTarget.add(yearlyTarget);
		currentMonthYearlyTarget.add(yearlyTargetActualCF);
		currentMonthYearlyTarget.add(yearlyTargetActualCFCumulative);
		monthlyTarget = null;
		yearlyTarget = null;
		yearlyTargetActualCF = null;
		yearlyTargetActualCFCumulative = null;

		return currentMonthYearlyTarget;
	}

	private ArrayList<NameSeriasPair> getYearlyValuesUNL(AgentAchievement agentAchievement,
			List<UNLAchievement> achievements) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		for (UNLAchievement achievement : achievements) {

			if (achievement.getMonth() == 1) {

				NameSeriasPair jan = new NameSeriasPair();

				ArrayList<NameValuePair> janVal = new ArrayList<>();

				NameValuePair janT = new NameValuePair();
				janT.setName("Target");
				janT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				janVal.add(janT);
				NameValuePair janA = new NameValuePair();
				janA.setName("Actual");
				janA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				janVal.add(janA);

				jan.setName("Jan.");
				jan.setSeries(janVal);

				yearlyValues.add(jan);

			} else if (achievement.getMonth() == 2) {
				NameSeriasPair feb = new NameSeriasPair();
				ArrayList<NameValuePair> febVal = new ArrayList<>();
				NameValuePair febT = new NameValuePair();
				febT.setName("Target");
				febT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				febVal.add(febT);
				NameValuePair febA = new NameValuePair();
				febA.setName("Actual");
				febA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				febVal.add(febA);
				feb.setName("Feb.");
				feb.setSeries(febVal);
				yearlyValues.add(feb);

			} else if (achievement.getMonth() == 3) {
				NameSeriasPair mar = new NameSeriasPair();
				ArrayList<NameValuePair> marVal = new ArrayList<>();
				NameValuePair marT = new NameValuePair();
				marT.setName("Target");
				marT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				marVal.add(marT);
				NameValuePair marA = new NameValuePair();
				marA.setName("Actual");
				marA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				marVal.add(marA);
				mar.setName("Mar.");
				mar.setSeries(marVal);
				yearlyValues.add(mar);

			} else if (achievement.getMonth() == 4) {
				NameSeriasPair apr = new NameSeriasPair();
				ArrayList<NameValuePair> aprVal = new ArrayList<>();
				NameValuePair aprT = new NameValuePair();
				aprT.setName("Target");
				aprT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				aprVal.add(aprT);
				NameValuePair aprA = new NameValuePair();
				aprA.setName("Actual");
				aprA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				aprVal.add(aprA);
				apr.setName("Apr.");
				apr.setSeries(aprVal);
				yearlyValues.add(apr);

			} else if (achievement.getMonth() == 5) {
				NameSeriasPair may = new NameSeriasPair();
				ArrayList<NameValuePair> mayVal = new ArrayList<>();
				NameValuePair mayT = new NameValuePair();
				mayT.setName("Target");
				mayT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				mayVal.add(mayT);
				NameValuePair mayA = new NameValuePair();
				mayA.setName("Actual");
				mayA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				mayVal.add(mayA);
				may.setName("May");
				may.setSeries(mayVal);
				yearlyValues.add(may);

			} else if (achievement.getMonth() == 6) {
				NameSeriasPair jun = new NameSeriasPair();
				ArrayList<NameValuePair> junVal = new ArrayList<>();
				NameValuePair junT = new NameValuePair();
				junT.setName("Target");
				junT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				junVal.add(junT);
				NameValuePair junA = new NameValuePair();
				junA.setName("Actual");
				junA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				junVal.add(junA);
				jun.setName("Jun.");
				jun.setSeries(junVal);
				yearlyValues.add(jun);

			} else if (achievement.getMonth() == 7) {
				NameSeriasPair Jul = new NameSeriasPair();
				ArrayList<NameValuePair> JulVal = new ArrayList<>();
				NameValuePair JulT = new NameValuePair();
				JulT.setName("Target");
				JulT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				JulVal.add(JulT);
				NameValuePair JulA = new NameValuePair();
				JulA.setName("Actual");
				JulA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				JulVal.add(JulA);
				Jul.setName("Jul.");
				Jul.setSeries(JulVal);
				yearlyValues.add(Jul);

			} else if (achievement.getMonth() == 8) {
				NameSeriasPair aug = new NameSeriasPair();
				ArrayList<NameValuePair> augVal = new ArrayList<>();
				NameValuePair augT = new NameValuePair();
				augT.setName("Target");
				augT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				augVal.add(augT);
				NameValuePair augA = new NameValuePair();
				augA.setName("Actual");
				augA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				augVal.add(augA);
				aug.setName("Aug.");
				aug.setSeries(augVal);
				yearlyValues.add(aug);

			} else if (achievement.getMonth() == 9) {
				NameSeriasPair sep = new NameSeriasPair();
				ArrayList<NameValuePair> sepVal = new ArrayList<>();
				NameValuePair sepT = new NameValuePair();
				sepT.setName("Target");
				sepT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				sepVal.add(sepT);
				NameValuePair sepA = new NameValuePair();
				sepA.setName("Actual");
				sepA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				sepVal.add(sepA);
				sep.setName("Sep.");
				sep.setSeries(sepVal);
				yearlyValues.add(sep);

			} else if (achievement.getMonth() == 10) {
				NameSeriasPair oct = new NameSeriasPair();
				ArrayList<NameValuePair> octVal = new ArrayList<>();
				NameValuePair octT = new NameValuePair();
				octT.setName("Target");
				octT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				octVal.add(octT);
				NameValuePair octA = new NameValuePair();
				octA.setName("Actual");
				octA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				octVal.add(octA);
				oct.setName("Oct.");
				oct.setSeries(octVal);
				yearlyValues.add(oct);

			} else if (achievement.getMonth() == 11) {
				NameSeriasPair nov = new NameSeriasPair();
				ArrayList<NameValuePair> novVal = new ArrayList<>();
				NameValuePair novT = new NameValuePair();
				novT.setName("Target");
				novT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				novVal.add(novT);
				NameValuePair novA = new NameValuePair();
				novA.setName("Actual");
				novA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				novVal.add(novA);
				nov.setName("Nov.");
				nov.setSeries(novVal);
				yearlyValues.add(nov);

			} else if (achievement.getMonth() == 12) {
				NameSeriasPair dec = new NameSeriasPair();
				ArrayList<NameValuePair> decVal = new ArrayList<>();
				NameValuePair decT = new NameValuePair();
				decT.setName("Target");
				decT.setValue((achievement.getTrgamt().intValue() + achievement.getTrgtcfa().intValue()));
				decVal.add(decT);
				NameValuePair decA = new NameValuePair();
				decA.setName("Actual");
				decA.setValue(
						agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue()
								: achievement.getTrgach().intValue());
				decVal.add(decA);
				dec.setName("Dec.");
				dec.setSeries(decVal);
				yearlyValues.add(dec);

			}
		}

		return yearlyValues;
	}

	private ArrayList<NameSeriasPair> processTargetActualCFChartUNL(AgentAchievement agentAchievement,
			List<UNLAchievement> achievements) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair target = new NameSeriasPair();
		NameSeriasPair carryForward = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();
		ArrayList<NameValuePair> monthT = new ArrayList<>();
		ArrayList<NameValuePair> monthCF = new ArrayList<>();

		Integer trgach = 0;

		if (achievements != null) {
			for (UNLAchievement achievement : achievements) {

				if (achievement.getMonth() == 1) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair janT = new NameValuePair();
					janT.setName("Jan.");
					janT.setValue(achievement.getTrgamt().intValue());
					monthT.add(janT);

					NameValuePair janA = new NameValuePair();
					janA.setName("Jan.");
					janA.setValue(trgach);
					monthA.add(janA);

					NameValuePair janCF = new NameValuePair();
					janCF.setName("Jan.");
					janCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(janCF);

				} else if (achievement.getMonth() == 2) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair febT = new NameValuePair();
					febT.setName("Feb.");
					febT.setValue(achievement.getTrgamt().intValue());
					monthT.add(febT);

					NameValuePair febA = new NameValuePair();
					febA.setName("Feb.");
					febA.setValue(trgach);
					monthA.add(febA);

					NameValuePair febCF = new NameValuePair();
					febCF.setName("Feb.");
					febCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(febCF);

				} else if (achievement.getMonth() == 3) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair marT = new NameValuePair();
					marT.setName("Mar.");
					marT.setValue(achievement.getTrgamt().intValue());
					monthT.add(marT);

					NameValuePair marA = new NameValuePair();
					marA.setName("Mar.");
					marA.setValue(trgach);
					monthA.add(marA);

					NameValuePair marCF = new NameValuePair();
					marCF.setName("Mar.");
					marCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(marCF);

				} else if (achievement.getMonth() == 4) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair aprT = new NameValuePair();
					aprT.setName("Apr.");
					aprT.setValue(achievement.getTrgamt().intValue());
					monthT.add(aprT);

					NameValuePair aprA = new NameValuePair();
					aprA.setName("Apr.");
					aprA.setValue(trgach);
					monthA.add(aprA);

					NameValuePair aprCF = new NameValuePair();
					aprCF.setName("Apr.");
					aprCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(aprCF);

				} else if (achievement.getMonth() == 5) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair mayT = new NameValuePair();
					mayT.setName("May.");
					mayT.setValue(achievement.getTrgamt().intValue());
					monthT.add(mayT);

					NameValuePair mayA = new NameValuePair();
					mayA.setName("May.");
					mayA.setValue(trgach);
					monthA.add(mayA);

					NameValuePair mayCF = new NameValuePair();
					mayCF.setName("May.");
					mayCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(mayCF);

				} else if (achievement.getMonth() == 6) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair junT = new NameValuePair();
					junT.setName("Jun.");
					junT.setValue(achievement.getTrgamt().intValue());
					monthT.add(junT);

					NameValuePair junA = new NameValuePair();
					junA.setName("Jun.");
					junA.setValue(trgach);
					monthA.add(junA);

					NameValuePair junCF = new NameValuePair();
					junCF.setName("Jun.");
					junCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(junCF);

				} else if (achievement.getMonth() == 7) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair julT = new NameValuePair();
					julT.setName("Jul.");
					julT.setValue(achievement.getTrgamt().intValue());
					monthT.add(julT);

					NameValuePair julA = new NameValuePair();
					julA.setName("Jul.");
					julA.setValue(trgach);
					monthA.add(julA);

					NameValuePair julCF = new NameValuePair();
					julCF.setName("Jul.");
					julCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(julCF);

				} else if (achievement.getMonth() == 8) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair augT = new NameValuePair();
					augT.setName("Aug.");
					augT.setValue(achievement.getTrgamt().intValue());
					monthT.add(augT);

					NameValuePair augA = new NameValuePair();
					augA.setName("Aug.");
					augA.setValue(trgach);
					monthA.add(augA);

					NameValuePair augCF = new NameValuePair();
					augCF.setName("Aug.");
					augCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(augCF);

				} else if (achievement.getMonth() == 9) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair sepT = new NameValuePair();
					sepT.setName("Sep.");
					sepT.setValue(achievement.getTrgamt().intValue());
					monthT.add(sepT);

					NameValuePair sepA = new NameValuePair();
					sepA.setName("Sep.");
					sepA.setValue(trgach);
					monthA.add(sepA);

					NameValuePair sepCF = new NameValuePair();
					sepCF.setName("Sep.");
					sepCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(sepCF);

				} else if (achievement.getMonth() == 10) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair octT = new NameValuePair();
					octT.setName("Oct.");
					octT.setValue(achievement.getTrgamt().intValue());
					monthT.add(octT);

					NameValuePair octA = new NameValuePair();
					octA.setName("Oct.");
					octA.setValue(trgach);
					monthA.add(octA);

					NameValuePair octCF = new NameValuePair();
					octCF.setName("Oct.");
					octCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(octCF);

				} else if (achievement.getMonth() == 11) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair novT = new NameValuePair();
					novT.setName("Nov.");
					novT.setValue(achievement.getTrgamt().intValue());
					monthT.add(novT);

					NameValuePair novA = new NameValuePair();
					novA.setName("Nov.");
					novA.setValue(trgach);
					monthA.add(novA);

					NameValuePair novCF = new NameValuePair();
					novCF.setName("Nov.");
					novCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(novCF);

				} else if (achievement.getMonth() == 12) {

					trgach = agentAchievement.getMonth() == achievement.getMonth()
							? agentAchievement.getTrgach().intValue()
							: achievement.getTrgach().intValue();
					NameValuePair decT = new NameValuePair();
					decT.setName("Dec.");
					decT.setValue(achievement.getTrgamt().intValue());
					monthT.add(decT);

					NameValuePair decA = new NameValuePair();
					decA.setName("Dec.");
					decA.setValue(trgach);
					monthA.add(decA);

					NameValuePair decCF = new NameValuePair();
					decCF.setName("Dec.");
					decCF.setValue(achievement.getTrgtcfa().intValue());
					monthCF.add(decCF);

				}
			}
		}

		actual.setName("Actual");
		actual.setSeries(monthA);
		target.setName("Target");
		target.setSeries(monthT);
		carryForward.setName("Carry Forward");
		carryForward.setSeries(monthCF);

		yearlyValues.add(target);
		yearlyValues.add(actual);
		yearlyValues.add(carryForward);

		return yearlyValues;
	}

	private ArrayList<NameSeriasPair> processTargetActualCumulativeChartUNL(AgentAchievement agentAchievement,
			List<UNLAchievement> achievements) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair target = new NameSeriasPair();
		NameSeriasPair carryForward = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();
		ArrayList<NameValuePair> monthT = new ArrayList<>();
		ArrayList<NameValuePair> monthCF = new ArrayList<>();

		if (achievements != null) {
			Double trgach = 0.0;
			Double trgamt = 0.0;
			Double trgtcfa = 0.0;

			for (UNLAchievement achievement : achievements) {

				if (achievement.getMonth() == 1) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair janT = new NameValuePair();
					janT.setName("Jan.");
					janT.setValue(trgamt.intValue());
					monthT.add(janT);

					NameValuePair janA = new NameValuePair();
					janA.setName("Jan.");
					janA.setValue(trgach.intValue());
					monthA.add(janA);

					NameValuePair janCF = new NameValuePair();
					janCF.setName("Jan.");
					janCF.setValue(trgtcfa.intValue());
					monthCF.add(janCF);

				} else if (achievement.getMonth() == 2) {
					// agentAchievement.getMonth() == achievement.getMonth() ?
					// agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue();
					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair febT = new NameValuePair();
					febT.setName("Feb.");
					febT.setValue(trgamt.intValue());
					monthT.add(febT);

					NameValuePair febA = new NameValuePair();
					febA.setName("Feb.");
					febA.setValue(trgach.intValue());
					monthA.add(febA);

					NameValuePair febCF = new NameValuePair();
					febCF.setName("Feb.");
					febCF.setValue(trgtcfa.intValue());
					monthCF.add(febCF);

				} else if (achievement.getMonth() == 3) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair marT = new NameValuePair();
					marT.setName("Mar.");
					marT.setValue(trgamt.intValue());
					monthT.add(marT);

					NameValuePair marA = new NameValuePair();
					marA.setName("Mar.");
					marA.setValue(trgach.intValue());
					monthA.add(marA);

					NameValuePair marCF = new NameValuePair();
					marCF.setName("Mar.");
					marCF.setValue(trgtcfa.intValue());
					monthCF.add(marCF);

				} else if (achievement.getMonth() == 4) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair aprT = new NameValuePair();
					aprT.setName("Apr.");
					aprT.setValue(trgamt.intValue());
					monthT.add(aprT);

					NameValuePair aprA = new NameValuePair();
					aprA.setName("Apr.");
					aprA.setValue(trgach.intValue());
					monthA.add(aprA);

					NameValuePair aprCF = new NameValuePair();
					aprCF.setName("Apr.");
					aprCF.setValue(trgtcfa.intValue());
					monthCF.add(aprCF);

				} else if (achievement.getMonth() == 5) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair mayT = new NameValuePair();
					mayT.setName("May.");
					mayT.setValue(trgamt.intValue());
					monthT.add(mayT);

					NameValuePair mayA = new NameValuePair();
					mayA.setName("May.");
					mayA.setValue(trgach.intValue());
					monthA.add(mayA);

					NameValuePair mayCF = new NameValuePair();
					mayCF.setName("May.");
					mayCF.setValue(trgtcfa.intValue());
					monthCF.add(mayCF);

				} else if (achievement.getMonth() == 6) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair junT = new NameValuePair();
					junT.setName("Jun.");
					junT.setValue(trgamt.intValue());
					monthT.add(junT);

					NameValuePair junA = new NameValuePair();
					junA.setName("Jun.");
					junA.setValue(trgach.intValue());
					monthA.add(junA);

					NameValuePair junCF = new NameValuePair();
					junCF.setName("Jun.");
					junCF.setValue(trgtcfa.intValue());
					monthCF.add(junCF);

				} else if (achievement.getMonth() == 7) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair julT = new NameValuePair();
					julT.setName("Jul.");
					julT.setValue(trgamt.intValue());
					monthT.add(julT);

					NameValuePair julA = new NameValuePair();
					julA.setName("Jul.");
					julA.setValue(trgach.intValue());
					monthA.add(julA);

					NameValuePair julCF = new NameValuePair();
					julCF.setName("Jul.");
					julCF.setValue(trgtcfa.intValue());
					monthCF.add(julCF);

				} else if (achievement.getMonth() == 8) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair augT = new NameValuePair();
					augT.setName("Aug.");
					augT.setValue(trgamt.intValue());
					monthT.add(augT);

					NameValuePair augA = new NameValuePair();
					augA.setName("Aug.");
					augA.setValue(trgach.intValue());
					monthA.add(augA);

					NameValuePair augCF = new NameValuePair();
					augCF.setName("Aug.");
					augCF.setValue(trgtcfa.intValue());
					monthCF.add(augCF);

				} else if (achievement.getMonth() == 9) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair sepT = new NameValuePair();
					sepT.setName("Sep.");
					sepT.setValue(trgamt.intValue());
					monthT.add(sepT);

					NameValuePair sepA = new NameValuePair();
					sepA.setName("Sep.");
					sepA.setValue(trgach.intValue());
					monthA.add(sepA);

					NameValuePair sepCF = new NameValuePair();
					sepCF.setName("Sep.");
					sepCF.setValue(trgtcfa.intValue());
					monthCF.add(sepCF);

				} else if (achievement.getMonth() == 10) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair octT = new NameValuePair();
					octT.setName("Oct.");
					octT.setValue(trgamt.intValue());
					monthT.add(octT);

					NameValuePair octA = new NameValuePair();
					octA.setName("Oct.");
					octA.setValue(trgach.intValue());
					monthA.add(octA);

					NameValuePair octCF = new NameValuePair();
					octCF.setName("Oct.");
					octCF.setValue(trgtcfa.intValue());
					monthCF.add(octCF);

				} else if (achievement.getMonth() == 11) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair novT = new NameValuePair();
					novT.setName("Nov.");
					novT.setValue(trgamt.intValue());
					monthT.add(novT);

					NameValuePair novA = new NameValuePair();
					novA.setName("Nov.");
					novA.setValue(trgach.intValue());
					monthA.add(novA);

					NameValuePair novCF = new NameValuePair();
					novCF.setName("Nov.");
					novCF.setValue(trgtcfa.intValue());
					monthCF.add(novCF);

				} else if (achievement.getMonth() == 12) {

					trgach = (trgach
							+ (agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach()
									: achievement.getTrgach()));
					trgamt = (trgamt + achievement.getTrgamt());
					trgtcfa = (trgtcfa + achievement.getTrgtcfa());

					NameValuePair decT = new NameValuePair();
					decT.setName("Dec.");
					decT.setValue(trgamt.intValue());
					monthT.add(decT);

					NameValuePair decA = new NameValuePair();
					decA.setName("Dec.");
					decA.setValue(trgach.intValue());
					monthA.add(decA);

					NameValuePair decCF = new NameValuePair();
					decCF.setName("Dec.");
					decCF.setValue(trgtcfa.intValue());
					monthCF.add(decCF);

				}
			}
		}

		actual.setName("Actual");
		actual.setSeries(monthA);
		target.setName("Target");
		target.setSeries(monthT);
		carryForward.setName("Carry Forward");
		carryForward.setSeries(monthCF);

		yearlyValues.add(target);
		yearlyValues.add(actual);
		yearlyValues.add(carryForward);

		return yearlyValues;
	}

	@Override
	public List<Object> getGWPAndGWPC(String userid, String dashpara, String usertype) throws Exception {
		List<Object> GWPAndGWPC = new ArrayList<>();
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		try {
			List<TargetCommitmentActual> targetCommitmentActualGWPList = targetCommitmentActualDao
					.getCurrentYearGWP(dashboardPara);

			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualGWPList) {
				if (dashboardPara.getDashmonth() == targetCommitmentActual.getMonth()) {
					MonthlyTarget monthlyTargetGWP = new MonthlyTarget();
					monthlyTargetGWP.setTarget(targetCommitmentActual.getTarget().intValue());
					monthlyTargetGWP.setActual(targetCommitmentActual.getActual().intValue());
					Double targetExpand = targetCommitmentActual.getTarget() + (targetCommitmentActual.getTarget() / 5);
					monthlyTargetGWP.setTargetExpand(targetExpand.intValue());
					GWPAndGWPC.add(monthlyTargetGWP);
				}
			}

			GWPAndGWPC.add(processTargetCommitmentActualChart(targetCommitmentActualGWPList));
			GWPAndGWPC.add(processTargetCommitmentActualCumulativeChart(targetCommitmentActualGWPList));

			targetCommitmentActualGWPList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		dashboardPara = null;
		calendar = null;

		return GWPAndGWPC;
	}

	@Override
	public List<Object> getMCFPAndMCFPC(String userid, String dashpara, String usertype) throws Exception {
		List<Object> MCFPAndMCFPC = new ArrayList<>();
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		try {
			List<TargetCommitmentActual> targetCommitmentActualMCFPList = targetCommitmentActualDao
					.getCurrentYearMCFP(dashboardPara);

			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualMCFPList) {
				if (dashboardPara.getDashmonth() == targetCommitmentActual.getMonth()) {
					MonthlyTarget monthlyTargetMCFP = new MonthlyTarget();
					monthlyTargetMCFP.setTarget(targetCommitmentActual.getTarget().intValue());
					monthlyTargetMCFP.setActual(targetCommitmentActual.getActual().intValue());
					Double targetExpand = targetCommitmentActual.getTarget()
							+ (targetCommitmentActual.getTarget() / 10);
					monthlyTargetMCFP.setTargetExpand(targetExpand.intValue());
					MCFPAndMCFPC.add(monthlyTargetMCFP);
				}
			}

			MCFPAndMCFPC.add(processTargetCommitmentActualChart(targetCommitmentActualMCFPList));
			MCFPAndMCFPC.add(processTargetCommitmentActualCumulativeChart(targetCommitmentActualMCFPList));
			targetCommitmentActualMCFPList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		dashboardPara = null;
		calendar = null;

		return MCFPAndMCFPC;
	}

	@Override
	public List<Object> getFYPAndFYPC(String userid, String dashpara, String usertype) throws Exception {
		List<Object> FYPAndFYPC = new ArrayList<>();
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		try {
			List<TargetCommitmentActual> targetCommitmentActualFYPAchList = targetCommitmentActualDao
					.getCurrentYearFYPAch(dashboardPara);

			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualFYPAchList) {
				if (dashboardPara.getDashmonth() == targetCommitmentActual.getMonth()) {
					MonthlyTarget monthlyTargetFYPAch = new MonthlyTarget();
					monthlyTargetFYPAch.setTarget(targetCommitmentActual.getTarget().intValue());
					monthlyTargetFYPAch.setActual(targetCommitmentActual.getActual().intValue());
					Double targetExpand = targetCommitmentActual.getTarget()
							+ (targetCommitmentActual.getTarget() / 10);
					monthlyTargetFYPAch.setTargetExpand(targetExpand.intValue());
					FYPAndFYPC.add(monthlyTargetFYPAch);
				}
			}

			FYPAndFYPC.add(processTargetCommitmentActualChart(targetCommitmentActualFYPAchList));
			FYPAndFYPC.add(processTargetCommitmentActualCumulativeChart(targetCommitmentActualFYPAchList));
			targetCommitmentActualFYPAchList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		dashboardPara = null;
		calendar = null;

		return FYPAndFYPC;
	}

	@Override
	public List<Object> getNOPAndNOPC(String userid, String dashpara, String usertype) throws Exception {
		List<Object> NOPAndNOPC = new ArrayList<>();
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		try {
			List<TargetCommitmentActual> targetCommitmentActualNOPList = targetCommitmentActualDao
					.getCurrentYearNOP(dashboardPara);

			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualNOPList) {
				if (dashboardPara.getDashmonth() == targetCommitmentActual.getMonth()) {
					MonthlyTarget monthlyTargetNOP = new MonthlyTarget();
					monthlyTargetNOP.setTarget(targetCommitmentActual.getTarget().intValue());
					monthlyTargetNOP.setActual(targetCommitmentActual.getActual().intValue());
					Double targetExpand = targetCommitmentActual.getTarget()
							+ (targetCommitmentActual.getTarget() / 10);
					monthlyTargetNOP.setTargetExpand(targetExpand.intValue());
					NOPAndNOPC.add(monthlyTargetNOP);
				}
			}

			NOPAndNOPC.add(processTargetCommitmentActualChart(targetCommitmentActualNOPList));
			NOPAndNOPC.add(processTargetCommitmentActualCumulativeChart(targetCommitmentActualNOPList));
			targetCommitmentActualNOPList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		dashboardPara = null;
		calendar = null;

		return NOPAndNOPC;
	}

	@Override
	public List<NameSeriasPair> getRINY(String userid, String dashpara, String usertype) throws Exception {
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));
		List<TargetCommitmentActual> targetCommitmentActualRTNY1List = targetCommitmentActualDao
				.getCurrentYearRTNY1(dashboardPara);
		return processCommitmentActualChart(targetCommitmentActualRTNY1List);
	}

	@Override
	public List<NameValuePair> getPolicySummery(String userid, String dashpara, String usertype) throws Exception {

		ArrayList<NameValuePair> arrayList = new ArrayList<>();
		DashboardPara dashboardPara = new DashboardPara();
		Calendar calendar = Calendar.getInstance();

		dashboardPara.setDashpara(dashpara);
		dashboardPara.setUsertype(usertype);
		dashboardPara.setDashyear(calendar.get(Calendar.YEAR));
		dashboardPara.setDashmonth((calendar.get(Calendar.MONTH) + 1));

		try {
			PolicySummary policySummary = policyDataDao.getPolicySummary(dashboardPara);

			NameValuePair nameValuePair1 = new NameValuePair();
			nameValuePair1.setName("Enfored");
			nameValuePair1.setValue(policySummary.getPlisuCount());

			arrayList.add(nameValuePair1);

			NameValuePair nameValuePair2 = new NameValuePair();
			nameValuePair2.setName("Amount");
			nameValuePair2.setValue(policySummary.getPlisuAmount().intValue());

			arrayList.add(nameValuePair2);

			NameValuePair nameValuePair3 = new NameValuePair();
			nameValuePair3.setName("Lapsed");
			nameValuePair3.setValue(policySummary.getPlapsCount());

			arrayList.add(nameValuePair3);

			NameValuePair nameValuePair4 = new NameValuePair();
			nameValuePair4.setName("Amount");
			nameValuePair4.setValue(policySummary.getPlapsAmount().intValue());

			arrayList.add(nameValuePair4);

			NameValuePair nameValuePair5 = new NameValuePair();
			nameValuePair5.setName("Permanent Lapsed");
			nameValuePair5.setValue(policySummary.getPlappCount());

			arrayList.add(nameValuePair5);

			NameValuePair nameValuePair6 = new NameValuePair();
			nameValuePair6.setName("Amount");
			nameValuePair6.setValue(policySummary.getPlappAmount().intValue());

			arrayList.add(nameValuePair6);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}

}
