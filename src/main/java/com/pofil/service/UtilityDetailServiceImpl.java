package com.pofil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pofil.model.UtilityBills;
import com.pofil.repository.UtilityBillsRepository;

@Service
public class UtilityDetailServiceImpl implements UtilityDetailService {

	@Autowired
	UtilityBillsRepository utilityBillsRepository;
	
	@Override
	public Optional<UtilityBills> getBranchById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilityBills> getAllBranch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UtilityBills> findByBranchNameAndFiscalYear(String branchName, String fiscalYear) {
		return utilityBillsRepository.findByBranchNameAndFiscalYear(branchName, fiscalYear);
	}

	@Override
	public Optional<UtilityBills> findByMonthAndFiscalYear(String month, String fiscalYear) {
		return utilityBillsRepository.findByMonthAndFiscalYear(month, fiscalYear);
	}

}
