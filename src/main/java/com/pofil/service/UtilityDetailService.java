package com.pofil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.pofil.model.UtilityBills;
import com.pofil.model.Utility;

public interface UtilityDetailService {
	
	Optional<UtilityBills> findById(String id);
	List<UtilityBills> findByFiscalYear(Sort sort);
	Optional<UtilityBills> findByBranchNameAndFiscalYear(String branchName, String fiscalYear);
	Optional<UtilityBills> findByMonthAndFiscalYear(String month, String fiscalYear);
	UtilityBills findByBranchNameAndFiscalYearAndMonth(String branchName, String fiscalYear, String month);
	List<UtilityBills> findUtilityBillsGroupByFiscalYear();
	
}
