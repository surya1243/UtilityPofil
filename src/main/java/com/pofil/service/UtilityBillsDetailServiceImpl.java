package com.pofil.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pofil.model.UtilityBills;
import com.pofil.repository.UtilityBillsRepository;

@Service
public class UtilityBillsDetailServiceImpl implements UtilityBillsDetailService {

	@Override
	public UtilityBills savePerson(UtilityBills utilityBills) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilityBills> getAllBranch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilityBills> getAllBranchPaginated(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtilityBills findOneByName(String branchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilityBills> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilityBills> findByBirthDateAfter(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtilityBills> findByAgeRange(int lowerBound, int upperBound) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtilityBills updateOneBranch(UtilityBills utilityBills) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBranch(UtilityBills utilityBills) {
		// TODO Auto-generated method stub

	}

}
