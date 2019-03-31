package com.pofil.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pofil.model.Branch;
import com.pofil.model.FiscalYear;
import com.pofil.repository.BranchRepository;
import com.pofil.repository.FiscalYearRepository;
import com.pofil.service.BranchDetailServiceImpl;
import com.pofil.service.FiscalYearDetailServiceImpl;

@RestController
public class BranchController {
	public static final int ID_LENGTH = 5;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private FiscalYearRepository fiscalYearRepository;
	
	@Autowired
	private BranchDetailServiceImpl branchService;
	
	@Autowired
	private FiscalYearDetailServiceImpl fiscalYearService;
	
	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String getDemo(HttpServletRequest request, Model model) {		
		return "updateDemo";
	}

	
	@RequestMapping(value = "/addbranch", method = RequestMethod.GET)
	public String getDefault(HttpServletRequest request, Model model) {
		List<Branch> branchList = branchRepository.findAll();
		List<FiscalYear> fiscalYearList = fiscalYearRepository.findAll();
		model.addAttribute("branchList", branchList);
		model.addAttribute("fiscalYearList", fiscalYearList);
		return "addbranch_fiscalyear";
	}
	
	@RequestMapping(value = "/getbranch", method = RequestMethod.GET)
	public List<Branch>  getBranch() {
		List<Branch> branch = branchRepository.findAll();		
		return branch;
	}
	
	@RequestMapping(value = "/addbranch", method = RequestMethod.POST)
	public ModelAndView saveBranchDetail(Branch branch, BindingResult bindingResult) {
		ModelAndView modelView = new ModelAndView();
		Branch branchExists = branchService.getBranchByName(branch.getBranchName());
		if (branchExists != null) {
			bindingResult.rejectValue("branchName", "error.branch",
					"There is already a branch registered with the brnach name provided");
		}
		if (bindingResult.hasErrors()) {
			modelView.addObject("message", "Could not Register the new Branch");
			modelView.setViewName("register");
		} else {
			branch.setId(generateUniqueId());
			branchService.saveBranch(branch);
			modelView.setViewName("register");
		}
		return modelView;
	}
	
	@RequestMapping(value = "/addfiscalyear", method = RequestMethod.POST)
	public ModelAndView saveFiscalYear(FiscalYear fiscalYears, BindingResult bindingResult) {
		ModelAndView modelView = new ModelAndView();
		FiscalYear fiscalYearExists = fiscalYearService.getFiscalYearName(fiscalYears.getFiscalYear());
		if (fiscalYearExists != null) {
			bindingResult.rejectValue("fiscalYear", "error.fiscalYear",
					"There is already a fiscal year registered with the year provided");
		}
		if (bindingResult.hasErrors()) {
			modelView.addObject("message", "Could not Register the new Fiscal Year");
			modelView.setViewName("register");
		} else {
			fiscalYears.setId(generateUniqueId());
			fiscalYearService.saveFiscalYear(fiscalYears);
			modelView.setViewName("register");
		}
		return modelView;
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/updatebranch/{id}") 
	public String updateBranch(Model model,@PathVariable String id){
		System.out.println("in console Id: " + id);
		Optional<Branch> branch = branchRepository.findById(id);
		model.addAttribute("branch", branch.get());
		model.addAttribute("statusCode", "branchUpdate");
		return "updatebranch_fiscalyear";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/updatebranch")
	public String saveUpdatedBranch(@Valid @ModelAttribute("branch") Branch branch, @RequestParam("branchName") String branchName, @RequestParam("branchAddress") String branchAddress,  @RequestParam("branchPhone") String branchPhone, @RequestParam("remark") String remark) {		
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
		branch.setBranchPhone(branchPhone);
		branch.setRemark(remark);	       
		branchRepository.save(branch);		
		return "redirect:/addbranch";
		}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/updatefiscalyear/{id}") 
	public String updateFiscal(Model model,@PathVariable String id){
		System.out.println("in console Id: " + id);
		Optional<FiscalYear> fiscalYears = fiscalYearRepository.findById(id);
		model.addAttribute("fiscalYears", fiscalYears.get());
		model.addAttribute("count", fiscalYearRepository.count());
		model.addAttribute("statusCode", "updateFiscal");
		//model.addAttribute("addStatus", false);
		return "updatebranch_fiscalyear";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/updatefiscalyear")
	public String saveUpdatedUser(@Valid @ModelAttribute("fiscalYears") FiscalYear fiscalYears, @RequestParam("fiscalYear") String fiscalYear,  @RequestParam("remark") String remark) {		
		fiscalYears.setFiscalYear(fiscalYear);
		fiscalYears.setRemark(remark);	       
		fiscalYearRepository.save(fiscalYears);		
		return "redirect:/addbranch";
		}
	
	public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}

}
