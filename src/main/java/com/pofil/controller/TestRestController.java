package com.pofil.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pofil.model.Branch;
import com.pofil.model.FiscalYear;
import com.pofil.service.BranchDetailService;
import com.pofil.service.FiscalYearDetailService;
@Controller
public class TestRestController {
	public static final int ID_LENGTH = 5;
	@Autowired
	private BranchDetailService fiscalYearService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getUtilityList() {		
		return "addbranchform";
	}
	
	
	public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}
}
