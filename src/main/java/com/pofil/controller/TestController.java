package com.pofil.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import com.pofil.Util.Response;
import com.pofil.model.Branch;
import com.pofil.model.FiscalYear;
import com.pofil.model.Utility;
import com.pofil.model.UtilityBills;
import com.pofil.repository.BranchRepository;
import com.pofil.repository.FiscalYearRepository;
import com.pofil.repository.UtilityBillsRepository;
import com.pofil.service.BranchDetailService;
import com.pofil.service.FiscalYearDetailService;
import com.pofil.service.UtilityDetailService;

@RestController
public class TestController {
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private FiscalYearRepository fiscalYearRepository;
		
	@Autowired
	UtilityDetailService utilityDetailService;
	
	@Autowired
	UtilityBillsRepository utilityBillsRepository;
	@Autowired
	private FiscalYearDetailService fiscalYearDetailService;
	@Autowired
	private BranchDetailService branchDetailService;
	
	@RequestMapping(value="/utilitySubmitTest", method=RequestMethod.POST)
	public Response getUtilityDetail(@RequestParam("branchName") String branchName, @RequestParam("fiscalYear") String fiscalYear , @RequestParam("month") String month) {
		UtilityBills exists = utilityDetailService.findByBranchNameAndFiscalYearAndMonth(branchName, fiscalYear, month);
		if (exists != null) {
            return new Response(
                    "There is already registered with the expenses provided",
                    true,
                    exists
            );
        }
        else {
            return new Response("Empty",false, null);
		}	
		}
	
	@RequestMapping(value = "/getbranch", method = RequestMethod.GET)
	public List<Branch>  getBranch() {
		List<Branch> branch = branchDetailService.getAllBranch();		
		return branch;
	}
	
	@RequestMapping(value = "/getfiscalyear", method = RequestMethod.GET)
	public List<FiscalYear>  getFiscalYear() {
		List<FiscalYear> fiscalYear = fiscalYearDetailService.getAllFiscalYear();	
		return fiscalYear;
	}
	
	@RequestMapping(value = "/api/getutility", method = RequestMethod.GET)
	public List<UtilityBills>  getApiUtility() {
		List<UtilityBills> utilityListApi = utilityDetailService.findByFiscalYear(Sort.by(Sort.Direction.ASC, "fiscalYear"));
		return utilityListApi;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api/getutilitycount", method = RequestMethod.GET)
	public Map<String, Long> getUniqueGenericClassesWithCount() {
		Map map = new HashMap<String, Integer>();
		DistinctIterable<String> coll = mongoTemplate.getCollection("utilitybills").distinct("fiscalYear", String.class);
		MongoCursor<String> cursor = coll.iterator();
		while (cursor.hasNext()) {
			String field = (String) cursor.next();
			map.put(field, utilityBillsRepository.countOrderOf("fiscalYear", field));
		}
		return map;
	}
		
		
		
	
	
	
}
