package org.arpicoinsurance.groupit.dashboard.dao;

import java.util.List;

import org.arpicoinsurance.groupit.dashboard.dto.Top3;

public interface Top3Dao {
	
	List<Top3> getTopIC() throws Exception;
	
	List<Top3> getTopIS() throws Exception;
	
	List<Top3> getTopUL() throws Exception;
	
	List<Top3> getTopBranch() throws Exception;
	
	List<Top3> getTopRegion() throws Exception;
	
	List<Top3> getTopZone() throws Exception;
}
