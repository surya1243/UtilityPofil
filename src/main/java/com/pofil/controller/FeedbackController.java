package com.pofil.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pofil.model.Branch;
import com.pofil.model.Feedback;
import com.pofil.model.FiscalYear;
import com.pofil.repository.BranchRepository;
import com.pofil.service.BranchDetailServiceImpl;
import com.pofil.service.FeedbackDetailServiceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FeedbackController {
	public static final int ID_LENGTH = 5;
	@Autowired
	private BranchDetailServiceImpl branchService;
	
	@Autowired
	private FeedbackDetailServiceImpl feedbackService;
	
	@Autowired
	private BranchRepository branchRepository;

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String getInsCompanyForm(HttpServletRequest request, Model model) {    	
        return "feedbacks/feedback_form";
    }
    
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public String saveFiscalYear(Feedback feedback, BindingResult bindingResult) {
		
    	feedback.setId(generateUniqueId());
    	feedbackService.saveFeedback(feedback);			
		return "feedbacks/feedback_form";
	}
    
    public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}
}
