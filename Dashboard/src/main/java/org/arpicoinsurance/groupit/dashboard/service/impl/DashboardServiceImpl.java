package org.arpicoinsurance.groupit.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dao.DashboardTypeDao;
import org.arpicoinsurance.groupit.dashboard.dao.TargetActualDao;
import org.arpicoinsurance.groupit.dashboard.dao.TargetCommitmentActualDao;
import org.arpicoinsurance.groupit.dashboard.dto.AgentAchievement;
import org.arpicoinsurance.groupit.dashboard.dto.DashboardPara;
import org.arpicoinsurance.groupit.dashboard.dto.DevPolicies;
import org.arpicoinsurance.groupit.dashboard.dto.MainRespDto;
import org.arpicoinsurance.groupit.dashboard.dto.MonthlyTarget;
import org.arpicoinsurance.groupit.dashboard.dto.NameSeriasPair;
import org.arpicoinsurance.groupit.dashboard.dto.NameValuePair;
import org.arpicoinsurance.groupit.dashboard.dto.PendingPol;
import org.arpicoinsurance.groupit.dashboard.dto.TargetCommitmentActual;
import org.arpicoinsurance.groupit.dashboard.dto.Top3;
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
	
	@Override
	public MainRespDto getDashboard(Integer id) throws Exception {
		DashboardPara dashboardPara = dashboardTypeDao.getDashboardPara("3857");
		dashboardPara.setDashyear(2017);

		AgentAchievement agentAchievement = targetActualDao.getAgentAchievement(dashboardPara, 4, 2017);
		List<AgentAchievement> agentAchievements = targetActualDao.getAgentAchievements(dashboardPara, 2017);
		
		
		
		dashboardPara.setDashtype("BRANCH");
		dashboardPara.setDashpara("BOR");
		List<TargetCommitmentActual> targetCommitmentActualNOPList = targetCommitmentActualDao.getCurrentYearNOP(dashboardPara);        
		List<TargetCommitmentActual> targetCommitmentActualGWPList = targetCommitmentActualDao.getCurrentYearGWP(dashboardPara);
		List<TargetCommitmentActual> targetCommitmentActualMCFPList = targetCommitmentActualDao.getCurrentYearMCFP(dashboardPara);
		List<TargetCommitmentActual> targetCommitmentActualFYPAchList = targetCommitmentActualDao.getCurrentYearFYPAch(dashboardPara);
		List<TargetCommitmentActual> targetCommitmentActualRTNY1List = targetCommitmentActualDao.getCurrentYearRTNY1(dashboardPara);
		
		MainRespDto mainRespDto = new MainRespDto();

		MonthlyTarget monthlyTarget = new MonthlyTarget();

		monthlyTarget.setActual(agentAchievement.getTrgach().intValue());
		monthlyTarget.setTarget(agentAchievement.getTrgamt().intValue());
		Double targetExpand = agentAchievement.getTrgamt()+(agentAchievement.getTrgamt()/3);
		monthlyTarget.setTargetExpand(targetExpand.intValue());

		
		
		mainRespDto.setYearlyTarget(getYearlyValues(agentAchievement,agentAchievements));
		mainRespDto.setNop(processTargetCommitmentActualChart(targetCommitmentActualNOPList));
		mainRespDto.setGwp(processTargetCommitmentActualChart(targetCommitmentActualGWPList));
		mainRespDto.setMcfp(processTargetCommitmentActualChart(targetCommitmentActualMCFPList));
		mainRespDto.setRiny1(processCommitmentActualChart(targetCommitmentActualRTNY1List));
		mainRespDto.setFyp(processTargetCommitmentActualChart(targetCommitmentActualFYPAchList));
		
		
		///////////////////Need to implement/////////////////////////////////////
		
		mainRespDto.setMonthlyTarget(monthlyTarget);
		mainRespDto.setMonthlyTargetFYP(monthlyTarget);
		mainRespDto.setMonthlyTargetGWP(monthlyTarget);
		mainRespDto.setMonthlyTargetMCFP(monthlyTarget);
		mainRespDto.setMonthlyTargetNOP(monthlyTarget);
		
		mainRespDto.setNopC(processTargetCommitmentActualChart(targetCommitmentActualNOPList));
		mainRespDto.setGwpC(processTargetCommitmentActualChart(targetCommitmentActualGWPList));
		mainRespDto.setMcfpC(processTargetCommitmentActualChart(targetCommitmentActualMCFPList));
		mainRespDto.setFypC(processTargetCommitmentActualChart(targetCommitmentActualFYPAchList));
		mainRespDto.setPolicySummery(new ArrayList<>());
		
		ArrayList<NameValuePair> arrayList=new ArrayList<>();
		
		NameValuePair nameValuePair1=new NameValuePair();
		nameValuePair1.setName("Policy Enfored");
		nameValuePair1.setValue(5);
		
		arrayList.add(nameValuePair1);
		
		
		NameValuePair nameValuePair2=new NameValuePair();
		nameValuePair2.setName("Amount");
		nameValuePair2.setValue(5000);
		
		arrayList.add(nameValuePair2);
		
		NameValuePair nameValuePair3=new NameValuePair();
		nameValuePair3.setName("Policy Lapsed");
		nameValuePair3.setValue(5);
		
		arrayList.add(nameValuePair3);
		
		NameValuePair nameValuePair4=new NameValuePair();
		nameValuePair4.setName("Amount");
		nameValuePair4.setValue(50000);
		
		arrayList.add(nameValuePair4);
		
		
		NameValuePair nameValuePair5=new NameValuePair();
		nameValuePair5.setName("Policy Lapsed P.");
		nameValuePair5.setValue(5);
		
		arrayList.add(nameValuePair5);
		
		
		NameValuePair nameValuePair6=new NameValuePair();
		nameValuePair6.setName("Amount");
		nameValuePair6.setValue(5);
		
		arrayList.add(nameValuePair6);
		
		mainRespDto.setPolicySummery(arrayList);
		
		////////////////////////////////////////////////////////
		
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

	private ArrayList<NameSeriasPair> getYearlyValues(AgentAchievement agentAchievement,List<AgentAchievement> achievements) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		for (AgentAchievement achievement : achievements) {
			
			if(achievement.getMonth() == 1){
				
				NameSeriasPair jan = new NameSeriasPair();

				ArrayList<NameValuePair> janVal = new ArrayList<>();

				NameValuePair janT = new NameValuePair();
				janT.setName("Target");
				janT.setValue(achievement.getTrgamt().intValue());
				janVal.add(janT);
				NameValuePair janA = new NameValuePair();
				janA.setName("Actual");
				janA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				janVal.add(janA);

				jan.setName("Jan.");
				jan.setSeries(janVal);

				yearlyValues.add(jan);
				
			} else if(achievement.getMonth() == 2){
				NameSeriasPair feb = new NameSeriasPair();
				ArrayList<NameValuePair> febVal = new ArrayList<>();
				NameValuePair febT = new NameValuePair();
				febT.setName("Target");
				febT.setValue(achievement.getTrgamt().intValue());
				febVal.add(febT);
				NameValuePair febA = new NameValuePair();
				febA.setName("Actual");
				febA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				febVal.add(febA);
				feb.setName("Feb.");
				feb.setSeries(febVal);
				yearlyValues.add(feb);
				
			} else if(achievement.getMonth() == 3){
				NameSeriasPair mar = new NameSeriasPair();
				ArrayList<NameValuePair> marVal = new ArrayList<>();
				NameValuePair marT = new NameValuePair();
				marT.setName("Target");
				marT.setValue(achievement.getTrgamt().intValue());
				marVal.add(marT);
				NameValuePair marA = new NameValuePair();
				marA.setName("Actual");
				marA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				marVal.add(marA);
				mar.setName("Mar.");
				mar.setSeries(marVal);
				yearlyValues.add(mar);
				
			} else if(achievement.getMonth() == 4){
				NameSeriasPair apr = new NameSeriasPair();
				ArrayList<NameValuePair> aprVal = new ArrayList<>();
				NameValuePair aprT = new NameValuePair();
				aprT.setName("Target");
				aprT.setValue(achievement.getTrgamt().intValue());
				aprVal.add(aprT);
				NameValuePair aprA = new NameValuePair();
				aprA.setName("Actual");
				aprA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				aprVal.add(aprA);
				apr.setName("Apr.");
				apr.setSeries(aprVal);
				yearlyValues.add(apr);
				
			} else if(achievement.getMonth() == 5){
				NameSeriasPair may = new NameSeriasPair();
				ArrayList<NameValuePair> mayVal = new ArrayList<>();
				NameValuePair mayT = new NameValuePair();
				mayT.setName("Target");
				mayT.setValue(achievement.getTrgamt().intValue());
				mayVal.add(mayT);
				NameValuePair mayA = new NameValuePair();
				mayA.setName("Actual");
				mayA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				mayVal.add(mayA);
				may.setName("May");
				may.setSeries(mayVal);
				yearlyValues.add(may);
				
			} else if(achievement.getMonth() == 6){
				NameSeriasPair jun = new NameSeriasPair();
				ArrayList<NameValuePair> junVal = new ArrayList<>();
				NameValuePair junT = new NameValuePair();
				junT.setName("Target");
				junT.setValue(achievement.getTrgamt().intValue());
				junVal.add(junT);
				NameValuePair junA = new NameValuePair();
				junA.setName("Actual");
				junA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				junVal.add(junA);
				jun.setName("Jun.");
				jun.setSeries(junVal);
				yearlyValues.add(jun);
				
			} else if(achievement.getMonth() == 7){
				NameSeriasPair Jul = new NameSeriasPair();
				ArrayList<NameValuePair> JulVal = new ArrayList<>();
				NameValuePair JulT = new NameValuePair();
				JulT.setName("Target");
				JulT.setValue(achievement.getTrgamt().intValue());
				JulVal.add(JulT);
				NameValuePair JulA = new NameValuePair();
				JulA.setName("Actual");
				JulA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				JulVal.add(JulA);
				Jul.setName("Jul.");
				Jul.setSeries(JulVal);
				yearlyValues.add(Jul);
				
			} else if(achievement.getMonth() == 8){
				NameSeriasPair aug = new NameSeriasPair();
				ArrayList<NameValuePair> augVal = new ArrayList<>();
				NameValuePair augT = new NameValuePair();
				augT.setName("Target");
				augT.setValue(achievement.getTrgamt().intValue());
				augVal.add(augT);
				NameValuePair augA = new NameValuePair();
				augA.setName("Actual");
				augA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				augVal.add(augA);
				aug.setName("Aug.");
				aug.setSeries(augVal);
				yearlyValues.add(aug);
				
			} else if(achievement.getMonth() == 9){
				NameSeriasPair sep = new NameSeriasPair();
				ArrayList<NameValuePair> sepVal = new ArrayList<>();
				NameValuePair sepT = new NameValuePair();
				sepT.setName("Target");
				sepT.setValue(achievement.getTrgamt().intValue());
				sepVal.add(sepT);
				NameValuePair sepA = new NameValuePair();
				sepA.setName("Actual");
				sepA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				sepVal.add(sepA);
				sep.setName("Sep.");
				sep.setSeries(sepVal);
				yearlyValues.add(sep);
				
			} else if(achievement.getMonth() == 10){
				NameSeriasPair oct = new NameSeriasPair();
				ArrayList<NameValuePair> octVal = new ArrayList<>();
				NameValuePair octT = new NameValuePair();
				octT.setName("Target");
				octT.setValue(achievement.getTrgamt().intValue());
				octVal.add(octT);
				NameValuePair octA = new NameValuePair();
				octA.setName("Actual");
				octA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				octVal.add(octA);
				oct.setName("Oct.");
				oct.setSeries(octVal);
				yearlyValues.add(oct);
				
			} else if(achievement.getMonth() == 11){
				NameSeriasPair nov = new NameSeriasPair();
				ArrayList<NameValuePair> novVal = new ArrayList<>();
				NameValuePair novT = new NameValuePair();
				novT.setName("Target");
				novT.setValue(achievement.getTrgamt().intValue());
				novVal.add(novT);
				NameValuePair novA = new NameValuePair();
				novA.setName("Actual");
				novA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				novVal.add(novA);
				nov.setName("Nov.");
				nov.setSeries(novVal);
				yearlyValues.add(nov);
				
			} else if(achievement.getMonth() == 12){
				NameSeriasPair dec = new NameSeriasPair();
				ArrayList<NameValuePair> decVal = new ArrayList<>();
				NameValuePair decT = new NameValuePair();
				decT.setName("Target");
				decT.setValue(achievement.getTrgamt().intValue());
				decVal.add(decT);
				NameValuePair decA = new NameValuePair();
				decA.setName("Actual");
				decA.setValue(agentAchievement.getMonth() == achievement.getMonth() ? agentAchievement.getTrgach().intValue() : achievement.getTrgach().intValue());
				decVal.add(decA);
				dec.setName("Dec.");
				dec.setSeries(decVal);
				yearlyValues.add(dec);
				
			}
		}
		
		return yearlyValues;
	}

	private ArrayList<NameSeriasPair> processTargetCommitmentActualChart(List<TargetCommitmentActual> targetCommitmentActualNOPList) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair target = new NameSeriasPair();
		NameSeriasPair commitment = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();
		ArrayList<NameValuePair> monthT = new ArrayList<>();
		ArrayList<NameValuePair> monthC = new ArrayList<>();
		
		if(targetCommitmentActualNOPList != null){
			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualNOPList) {
				
				if(targetCommitmentActual.getMonth() == 1){
					
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
					
				} if(targetCommitmentActual.getMonth() == 2){
					
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
					
				} if(targetCommitmentActual.getMonth() == 3){
	
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
					
				} if(targetCommitmentActual.getMonth() == 4){
	
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
					
				} if(targetCommitmentActual.getMonth() == 5){
	
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
					
				} if(targetCommitmentActual.getMonth() == 6){
	
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
					
				} if(targetCommitmentActual.getMonth() == 7){
	
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
					
				} if(targetCommitmentActual.getMonth() == 8){
	
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
					
				} if(targetCommitmentActual.getMonth() == 9){
	
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
					
				} if(targetCommitmentActual.getMonth() == 10){
					
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
					
				} if(targetCommitmentActual.getMonth() == 11){
	
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
					
				} if(targetCommitmentActual.getMonth() == 12){
	
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
					
				}
			}
		}
		
		actual.setName("Actual");
		actual.setSeries(monthA);
		target.setName("Target");
		target.setSeries(monthT);
		commitment.setName("Commitment");
		commitment.setSeries(monthC);

		yearlyValues.add(target);
		yearlyValues.add(commitment);
		yearlyValues.add(actual);

		return yearlyValues;
	}
	
	private ArrayList<NameSeriasPair> processCommitmentActualChart(List<TargetCommitmentActual> targetCommitmentActualNOPList) {
		ArrayList<NameSeriasPair> yearlyValues = new ArrayList<>();

		NameSeriasPair actual = new NameSeriasPair();
		NameSeriasPair commitment = new NameSeriasPair();

		ArrayList<NameValuePair> monthA = new ArrayList<>();
		ArrayList<NameValuePair> monthC = new ArrayList<>();
		if(targetCommitmentActualNOPList != null) {
			for (TargetCommitmentActual targetCommitmentActual : targetCommitmentActualNOPList) {
				
				if(targetCommitmentActual.getMonth() == 1){
					
					NameValuePair janC = new NameValuePair();
					janC.setName("Jan.");
					janC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(janC);
					
					NameValuePair janA = new NameValuePair();
					janA.setName("Jan.");
					janA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(janA);
					
				} if(targetCommitmentActual.getMonth() == 2){
					
					NameValuePair febC = new NameValuePair();
					febC.setName("Feb.");
					febC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(febC);
					
					NameValuePair febA = new NameValuePair();
					febA.setName("Feb.");
					febA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(febA);
					
				} if(targetCommitmentActual.getMonth() == 3){
	
					NameValuePair marC = new NameValuePair();
					marC.setName("Mar.");
					marC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(marC);
					
					NameValuePair marA = new NameValuePair();
					marA.setName("Mar.");
					marA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(marA);
					
				} if(targetCommitmentActual.getMonth() == 4){
	
					NameValuePair aprC = new NameValuePair();
					aprC.setName("Apr.");
					aprC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(aprC);
					
					NameValuePair aprA = new NameValuePair();
					aprA.setName("Apr.");
					aprA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(aprA);
					
				} if(targetCommitmentActual.getMonth() == 5){
	
					NameValuePair mayC = new NameValuePair();
					mayC.setName("May.");
					mayC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(mayC);
					
					NameValuePair mayA = new NameValuePair();
					mayA.setName("May.");
					mayA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(mayA);
					
				} if(targetCommitmentActual.getMonth() == 6){
	
					NameValuePair junC = new NameValuePair();
					junC.setName("Jun.");
					junC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(junC);
					
					NameValuePair junA = new NameValuePair();
					junA.setName("Jun.");
					junA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(junA);
					
				} if(targetCommitmentActual.getMonth() == 7){
	
					NameValuePair julC = new NameValuePair();
					julC.setName("Jul.");
					julC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(julC);
					
					NameValuePair julA = new NameValuePair();
					julA.setName("Jul.");
					julA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(julA);
					
				} if(targetCommitmentActual.getMonth() == 8){
	
					NameValuePair augC = new NameValuePair();
					augC.setName("Aug.");
					augC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(augC);
					
					NameValuePair augA = new NameValuePair();
					augA.setName("Aug.");
					augA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(augA);
					
				} if(targetCommitmentActual.getMonth() == 9){
	
					NameValuePair sepC = new NameValuePair();
					sepC.setName("Sep.");
					sepC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(sepC);
					
					NameValuePair sepA = new NameValuePair();
					sepA.setName("Sep.");
					sepA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(sepA);
					
				} if(targetCommitmentActual.getMonth() == 10){
					
					NameValuePair octC = new NameValuePair();
					octC.setName("Oct.");
					octC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(octC);
					
					NameValuePair octA = new NameValuePair();
					octA.setName("Oct.");
					octA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(octA);
					
				} if(targetCommitmentActual.getMonth() == 11){
	
					NameValuePair novC = new NameValuePair();
					novC.setName("Nov.");
					novC.setValue(targetCommitmentActual.getCommitment().intValue());
					monthC.add(novC);
					
					NameValuePair novA = new NameValuePair();
					novA.setName("Nov.");
					novA.setValue(targetCommitmentActual.getActual().intValue());
					monthA.add(novA);
					
				} if(targetCommitmentActual.getMonth() == 12){
	
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

}
