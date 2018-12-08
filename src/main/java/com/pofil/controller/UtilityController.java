package com.pofil.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pofil.service.BranchDetailService;
import com.pofil.service.FiscalYearDetailService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pofil.model.Expense;
import com.pofil.model.UtilityBills;
import com.pofil.repository.UtilityBillsRepository;

@Controller
public class UtilityController {
	public static final int ID_LENGTH = 5;
	@Autowired
	private UtilityBillsRepository utilityBillsRepository;

	@Autowired
	private BranchDetailService branchDetailService;

	@Autowired
	private FiscalYearDetailService fiscalYearDetailService;

	public UtilityController(UtilityBillsRepository utilityBillsRepository) {
		this.utilityBillsRepository = utilityBillsRepository;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getForm(Model model) {
		model.addAttribute("branchLists", branchDetailService.getAllBranch());
		model.addAttribute("fiscalYears", fiscalYearDetailService.getAllFiscalYear());
		return "utilityform";
	}
	
	@RequestMapping(value = { "/bill/submit" }, method = RequestMethod.POST)
	public String saveUtilityDetail(Model model, @ModelAttribute("branch") UtilityBills utilityBills,
			@RequestParam("amount") List<Double> amount, @RequestParam("category") List<String> category,
			@RequestParam("description") List<String> description) {
		System.out.println("aayo");
		utilityBills.setId(generateUniqueId());
		List<Expense> expenses = new ArrayList<>();

		for (int i = 0; i < category.size(); i++) {
			Expense e = new Expense();
			e.setCategory(category.get(i));
			e.setAmount(amount.get(i));
			e.setDescription(description.get(i));
			expenses.add(e);
		}
		utilityBills.setExpense(expenses);

		System.out.println(utilityBills.getId() + "  hello  " + utilityBills.getMonth() + ".......");

		utilityBillsRepository.save(utilityBills);
		return "redirect:/utilitylist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/utilitylist", method = RequestMethod.GET)
	public String getUtilityList(Model model) {
		List<UtilityBills> branchList = utilityBillsRepository.findAll();
		model.addAttribute("branchList", branchList);
		return "utilitylist";
	}

	@RequestMapping(value = "/branch/expense/{id}", method = RequestMethod.GET)
	public String getBranchExpense(Model model, @PathVariable(value = "id") String id) {
		UtilityBills utilityBills = utilityBillsRepository.findById(id).get();
		model.addAttribute("branch", utilityBills);
		return "expenselist";
	}
	
	public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}

}
