package com.pofil.controller;

import com.pofil.model.Branch;
import com.pofil.model.InsuranceCustomer;
import com.pofil.model.InsuranceSchema;
import com.pofil.repository.BranchRepository;
import com.pofil.repository.InsuranceCustomerRepository;
import com.pofil.repository.InsuranceSchemaRepository;
import com.pofil.service.InsuranceCustomerDetailService;
import com.pofil.service.InsuranceSchemaDetailService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pofil.model.InsuranceCompany;
import com.pofil.repository.InsuranceCompanyRepository;
import com.pofil.service.InsuranceCompanyDetailService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class InsuranceCompanyController {
	public static final int ID_LENGTH = 5;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private InsuranceCompanyRepository repo;

	@Autowired
	private InsuranceCustomerRepository insuranceCustomerRepository;

	@Autowired
	private InsuranceCustomerDetailService insuranceCustomerDetailService;

	@Autowired
	private InsuranceSchemaRepository schemaRepository;
	
	@Autowired
	private InsuranceCompanyDetailService insService;

	@Autowired
	private InsuranceSchemaDetailService insuranceSchemaDetailService;

    @RequestMapping(value = "/inscustform", method = RequestMethod.GET)
    public String getInsCompanyForm(HttpServletRequest request, Model model) {
        return "insurance/insurance_customer_form";
    }
    
    @RequestMapping(value = "/insSchema", method = RequestMethod.GET)
	public String getInsSchemaForm(HttpServletRequest request, Model model) {
		List<InsuranceCompany> insuranceCompanyList = repo.findAll();
		List<InsuranceSchema> insuranceSchemaList = schemaRepository.findAll();
		model.addAttribute("branchList", insuranceCompanyList);
		model.addAttribute("fiscalYearList", insuranceSchemaList);
		model.addAttribute("companyCode", insService.getAllInsCompany());
		return "insurance/insurance_and_schema_form";
	}

	@RequestMapping(value = "/inscustlist", method = RequestMethod.GET)
	public String getInsCustomerList(HttpServletRequest request, Model model) {
		List<InsuranceCustomer> insuranceCustomerList = insuranceCustomerRepository.findAll();
		model.addAttribute("insCustList", insuranceCustomerList);
		return "insurance/insurance_customer_list";
	}

	@RequestMapping(value = "/inscustform2/{id}", method = RequestMethod.GET)
	public String getInsCustomerForm(HttpServletRequest request, Model model, @PathVariable String id) {
		List<InsuranceCompany> insuranceCompanyList = repo.findAll();
		List<InsuranceSchema> insuranceSchemaList = schemaRepository.findAll();
		List<InsuranceCustomer> insuranceCustomerList = insuranceCustomerRepository.findAll();
		List<Branch> branchList = branchRepository.findAll();
		model.addAttribute("branchList", branchList);
		model.addAttribute("insuranceCompanyList", insuranceCompanyList);
		model.addAttribute("insuranceSchemaList", insuranceSchemaList);
		model.addAttribute("insCustList", insuranceCustomerList);
		return "insurance/insurance_customer_form2";
	}

	/*@RequestMapping(value = "/singleview/{id}", method = RequestMethod.GET)
	public String getPlant(@PathVariable String id, Model model) {
		Optional<Plant> selectedPlant = repository.findById(id);
		model.addAttribute("plantDetails", selectedPlant.get());
		return "webusers/singleview";
	}
*/

	@RequestMapping(value = "/addinscompany", method = RequestMethod.POST)
	public ModelAndView saveInsuranceCompany(InsuranceCompany insCompany, BindingResult bindingResult) {
    	List<String> insCType = new ArrayList<>();
    	for (int i=0;i<insCompany.getInsCompanyType().size();i++){
    		insCType.add(insCompany.getInsCompanyType().get(i));
		};
		ModelAndView modelView = new ModelAndView();
		InsuranceCompany branchExists = insService.getInsCompanyByCode(insCompany.getInsCompanyCode());
		if (branchExists != null) {
			bindingResult.rejectValue("branchName", "error.branch",
					"There is already a branch registered with the brnach name provided");
		}
		if (bindingResult.hasErrors()) {
			modelView.addObject("message", "Could not Register the new Branch");
			modelView.setViewName("register");
		} else {
			insCompany.setInsCompanyType(insCType);
			insCompany.setId(generateUniqueId());
			repo.save(insCompany);
			modelView.setViewName("register");
		}
		return modelView;
	}

	@RequestMapping(value = "/addinsschema", method = RequestMethod.POST)
	public ModelAndView saveInsuranceSchema(InsuranceSchema insSchema, BindingResult bindingResult) {
		ModelAndView modelView = new ModelAndView();
		InsuranceSchema branchExists = insuranceSchemaDetailService.getInsuranceSchemaByInsSchemaCode(insSchema.getInsSchemaCode());
		if (branchExists != null) {
			bindingResult.rejectValue("insSchemaCode", "error.insSchema",
					"There is already a code registered with the Schema Code provided");
		}
		if (bindingResult.hasErrors()) {
			modelView.addObject("message", "Could not Register the new Schema Product");
			modelView.setViewName("register");
		} else {
			insSchema.setId(generateUniqueId());
			schemaRepository.save(insSchema);
			modelView.setViewName("register");
		}
		return modelView;
	}

	@RequestMapping(value = "/addinscust", method = RequestMethod.POST)
	public ModelAndView saveInsuranceCustomer(InsuranceCustomer insuranceCustomer, BindingResult bindingResult) {
		ModelAndView modelView = new ModelAndView();
		insuranceCustomer.setId(generateUniqueId());
			insuranceCustomerRepository.save(insuranceCustomer);
			modelView.setViewName("register");

		return modelView;
	}

    public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}

}
