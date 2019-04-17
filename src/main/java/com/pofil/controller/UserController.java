package com.pofil.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pofil.model.AppUser;
import com.pofil.model.UtilityBills;
import com.pofil.model.Expense;
import com.pofil.model.Role;
import com.pofil.model.Utility;
import com.pofil.repository.UtilityBillsRepository;
import com.pofil.repository.RoleRepository;
import com.pofil.repository.UserRepository;
import com.pofil.service.UserDetailServiceImpl;

@Controller
public class UserController {
	public static final int ID_LENGTH = 5;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserDetailServiceImpl userService;	

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String getDefault(HttpServletRequest request) {
		return "layouts/default";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getHello(HttpServletRequest request) {
		return "hello";
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String getLoginForm(HttpServletRequest request) {
		if (request.getRemoteUser() != null) {
			return "redirect:/form";
		}
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterForm(Model model) {
		// model.addAttribute("count", repository.count());
		return "register";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public String getUserList(Model model) {
		List<AppUser> userList = userRepository.findAll();
		System.out.println(userList);
		model.addAttribute("userList", userList);
		model.addAttribute("count", userRepository.count());
		return "userlist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/updateuser/{id}")
	public String updateUser(Model model,@PathVariable String id){
		System.out.println("in console Id: " + id);
		Optional<AppUser> user = userRepository.findById(id);
		model.addAttribute("user", user.get());
		model.addAttribute("count", userRepository.count());
		//model.addAttribute("addStatus", false);
		return "updateuser";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/updateuser")
	public String saveUpdatedUser(@Valid @ModelAttribute("user") AppUser user, @RequestParam("password") String password, @RequestParam("chpassword") String chpassword) {
		if(!((password == null)||(password.isEmpty()))) {
			userService.saveUser(user);
		}
		else {
			user.setPassword(chpassword);
			user.setEnabled(true);
	        Role userRole = roleRepository.findByRole("ADMIN");
	        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
			userRepository.save(user);
		}
		
		return "redirect:/userlist";
		}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView saveUserDetail(AppUser user, BindingResult bindingResult) {
		ModelAndView modelView = new ModelAndView();
		AppUser userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelView.addObject("message", "Could not Register the new User");
			modelView.setViewName("register");
		} else {
			user.setId(generateUniqueId());
			userService.saveUser(user);
			modelView.setViewName("register");
		}

		return modelView;
	}

	public String generateUniqueId() {
		return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
	}

}
