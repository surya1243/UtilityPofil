package com.pofil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import com.pofil.model.UtilityBills;
import com.pofil.repository.UtilityBillsRepository;

@Service
public class UtilityDetailServiceImpl implements UtilityDetailService {
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Autowired
	UtilityBillsRepository utilityBillsRepository;

	@Override
	public Optional<UtilityBills> findById(String id) {
		return utilityBillsRepository.findById(id);
	}

	@Override
	public List<UtilityBills> findByFiscalYear(Sort sort) {
		return utilityBillsRepository.findAll(Sort.by(Sort.Direction.DESC, "fiscalYear"));
	}


	@Override
	public Optional<UtilityBills> findByBranchNameAndFiscalYear(String branchName, String fiscalYear) {
		return utilityBillsRepository.findByBranchNameAndFiscalYear(branchName, fiscalYear);
	}

	@Override
	public Optional<UtilityBills> findByMonthAndFiscalYear(String month, String fiscalYear) {
		return utilityBillsRepository.findByMonthAndFiscalYear(month, fiscalYear);
	}

	@Override
	public UtilityBills findByBranchNameAndFiscalYearAndMonth(String branchName, String fiscalYear, String month) {
		UtilityBills exists = utilityBillsRepository.findByBranchNameAndFiscalYearAndMonth(branchName, fiscalYear, month);
		if(exists==null) return null;
		return exists;
	}

	@Override
	public List<UtilityBills> findUtilityBillsGroupByFiscalYear() {
		TypedAggregation<UtilityBills> studentAggregation = Aggregation.newAggregation(UtilityBills.class,
	               Aggregation.group("fiscalYear").
	               push("$$ROOT").as("utilityBills"));
		AggregationResults<UtilityBills> results = mongoTemplate.aggregate(studentAggregation, UtilityBills.class);
	        List<UtilityBills> utilityBillsResultsList = results.getMappedResults();
	        System.out.println(utilityBillsResultsList);
	        return utilityBillsResultsList;
	}

}
