package com.pofil.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pofil.model.Branch;
import com.pofil.model.InsuranceCompany;
import com.pofil.model.InsuranceCustomer;
import com.pofil.model.InsuranceCustomerRegister;
import com.pofil.model.InsuranceSchema;
import com.pofil.repository.BranchRepository;
import com.pofil.repository.InsuranceCompanyRepository;
import com.pofil.repository.InsuranceCustomerRepository;
import com.pofil.repository.InsuranceSchemaRepository;
import com.pofil.service.InsuranceCompanyDetailService;
import com.pofil.service.InsuranceCustomerRegisterDetailService;
import com.pofil.service.InsuranceSchemaDetailService;

@Controller
public class InsuranceCustomerRegisterController {
	public static final int ID_LENGTH = 5;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private InsuranceCompanyRepository insuranceCompanyRepository;

	@Autowired
	private InsuranceCustomerRepository insuranceCustomerRepository;

	@Autowired
	private InsuranceCustomerRegisterDetailService insCustRegisterDetailService;

	@Autowired
	private InsuranceSchemaRepository schemaRepository;

	@Autowired
	private InsuranceCompanyDetailService insService;

	@Autowired
	private InsuranceSchemaDetailService insuranceSchemaDetailService;

	@GetMapping("/inscustlist")
	public String getInsCustomerList(HttpServletRequest request, Model model) {
		List<InsuranceCustomer> insuranceCustomerList = insuranceCustomerRepository.findAll();
		model.addAttribute("insCustList", insuranceCustomerList);
		return "insurance/insurance_customer_list";
	}

	@GetMapping("/inscustform2/{id}")
	public String getInsCustomerForm(HttpServletRequest request, Model model, @PathVariable String id) {
		Optional<InsuranceCustomer> insCustListById = insuranceCustomerRepository.findById(id);
		List<InsuranceCompany> insuranceCompanyList = insuranceCompanyRepository.findAll();
		List<InsuranceSchema> insuranceSchemaList = schemaRepository.findAll();
		List<Branch> branchList = branchRepository.findAll();
		model.addAttribute("branchList", branchList);
		model.addAttribute("insuranceCompanyList", insuranceCompanyList);
		model.addAttribute("insuranceSchemaList", insuranceSchemaList);
		model.addAttribute("insCustListById", insCustListById.get());
		return "insurance/insurance_customer_form2";
	}

	@PostMapping("/addinscust")
	public ModelAndView saveInsuranceCustomer(InsuranceCustomer insuranceCustomer, BindingResult bindingResult) {
		ModelAndView modelView = new ModelAndView();
		insuranceCustomer.setId(generateUniqueId());
		insuranceCustomerRepository.save(insuranceCustomer);
		modelView.setViewName("register");
		return modelView;
	}

	@PostMapping("/inscustregister")
	public ModelAndView saveInsuranceCustomerRegister(InsuranceCustomerRegister insuranceCustomerRegister) {
		ModelAndView modelView = new ModelAndView();
		insuranceCustomerRegister.setId(generateUniqueId());
		insCustRegisterDetailService.saveInsCustRegister(insuranceCustomerRegister);
		modelView.setViewName("insurance/insurance_customer_list");
		return modelView;
	}

	@GetMapping("/inscustreport")
	public String getInsuranceCustomerReport(Model model) {
		return "insurance/insurance_customer_report";
	}

	@GetMapping("/inscustreportall")
	public String getAllInsuranceCustomerReport(Model model) {
		List<InsuranceCustomerRegister> insuranceCustomerRegisterList = insCustRegisterDetailService
				.getAllInsuranceCustomerRegister();
		model.addAttribute("insCustRegisterList", insuranceCustomerRegisterList);
		return "insurance/insurance_customer_all_list";
	}

	public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}
}
