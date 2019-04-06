package com.pofil.service;

import java.util.List;
import java.util.Optional;

import com.pofil.model.UtilityBills;
import com.pofil.model.Utility;

public interface UtilityDetailService {
	
	Optional<UtilityBills> getBranchById(long id);

	//Utility findUtilityByMonth(String month);

	List<UtilityBills> getAllBranch();

	//Utility saveUtility(Utility utility);
	
	Optional<UtilityBills> findByBranchNameAndFiscalYear(String branchName, String fiscalYear);
	Optional<UtilityBills> findByMonthAndFiscalYear(String month, String fiscalYear);
	
}
