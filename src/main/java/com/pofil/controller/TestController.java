package com.pofil.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pofil.model.Branch;
import com.pofil.model.FiscalYear;
import com.pofil.model.Utility;
import com.pofil.model.UtilityBills;
import com.pofil.repository.BranchRepository;
import com.pofil.repository.FiscalYearRepository;
import com.pofil.repository.UtilityBillsRepository;
import com.pofil.service.UtilityDetailService;

@RestController
public class TestController {
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private FiscalYearRepository fiscalYearRepository;
		
	@Autowired
	UtilityDetailService utilityDetailService;
	
	@Autowired
	UtilityBillsRepository utilityBillsRepository;
	
	
	@RequestMapping(value="/utilityByMonthTest", method=RequestMethod.POST)
	public UtilityBills getUtilityDetail(@RequestParam("fiscalYear") String fiscalYear , @RequestParam("branchName") String branchName) {
		//UtilityBills utilityBills = utilityBillsRepository.findByBranchNameAndFiscalYear(branchName, fiscalYear);		
		return null;
		}
	
	@RequestMapping(value = "/getbranch", method = RequestMethod.GET)
	public List<Branch>  getBranch() {
		List<Branch> branch = branchRepository.findAll();		
		return branch;
	}
	
	@RequestMapping(value = "/getfiscalyear", method = RequestMethod.GET)
	public List<FiscalYear>  getFiscalYear() {
		List<FiscalYear> fiscalYear = fiscalYearRepository.findAll();		
		return fiscalYear;
	}
	
	
}
