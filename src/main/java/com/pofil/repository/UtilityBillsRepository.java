package com.pofil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pofil.model.UtilityBills;

public interface UtilityBillsRepository extends MongoRepository<UtilityBills, String> {
	
	Optional<UtilityBills> findById(String id);
	List<UtilityBills> findByFiscalYear(Sort sort);
	Optional<UtilityBills> findByBranchNameAndFiscalYear(String branchName, String fiscalYear);
	Optional<UtilityBills> findByMonthAndFiscalYear(String month, String fiscalYear);
	UtilityBills findByBranchNameAndFiscalYearAndMonth(String branchName, String fiscalYear, String month);
	List<UtilityBills> findUtilityBillsGroupByFiscalYear();
	
	
	@Query(value = "{?0: ?1}", count = true)
	public Long countOrderOf(String field, String value);
}
