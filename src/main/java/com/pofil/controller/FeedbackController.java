package com.pofil.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pofil.model.Feedback;
import com.pofil.repository.BranchRepository;
import com.pofil.repository.FeedbackRepository;
import com.pofil.service.BranchDetailService;
import com.pofil.service.FeedbackDetailService;

@Controller
public class FeedbackController {
	public static final int ID_LENGTH = 5;
	@Autowired
	private BranchDetailService branchService;

	@Autowired
	private FeedbackDetailService feedbackService;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;

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

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/feedbacklist", method = RequestMethod.GET)
	public String getFeedbackList(Model model) {
		List<Feedback> feedbackList = feedbackService.findByDate(Sort.by(Sort.Direction.ASC, "date"));
		model.addAttribute("message", feedbackList);
		return "feedbacks/feedback_list";
	}

	public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}
}
