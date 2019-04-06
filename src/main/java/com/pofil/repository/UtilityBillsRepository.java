package com.pofil.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pofil.model.UtilityBills;

public interface UtilityBillsRepository extends MongoRepository<UtilityBills, String> {
	
	Optional<UtilityBills> findById(String id);
	Optional<UtilityBills> findByBranchNameAndFiscalYear(String branchName, String fiscalYear);
	Optional<UtilityBills> findByMonthAndFiscalYear(String month, String fiscalYear);
	
}
