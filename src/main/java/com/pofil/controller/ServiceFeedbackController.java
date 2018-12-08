package com.pofil.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServiceFeedbackController {
	@RequestMapping(value = "/servicefeedback", method = RequestMethod.GET)
	public String getDemo(HttpServletRequest request, Model model) {		
		return "null";
	}

}
