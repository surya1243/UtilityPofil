package com.pofil.service;

import java.util.Date;
import java.util.List;

import com.pofil.model.UtilityBills;

public interface UtilityBillsDetailService {
	UtilityBills savePerson(UtilityBills utilityBills);
	   List<UtilityBills> getAllBranch();
	   List<UtilityBills> getAllBranchPaginated(int pageNumber, int pageSize);
	   UtilityBills findOneByName(String branchName);
	   List<UtilityBills> findByName(String name);
	   List<UtilityBills> findByBirthDateAfter(Date date);
	   List<UtilityBills> findByAgeRange(int lowerBound, int upperBound);
	   UtilityBills updateOneBranch(UtilityBills utilityBills);
	   void deleteBranch(UtilityBills utilityBills);

}
