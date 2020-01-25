package com.example.securitydemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.securitydemo.model.UserDto;
import com.example.securitydemo.service.UserRegistrationService;

@Controller
public class HomeController {
	
	@Autowired
	UserRegistrationService service;

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelAndView.addObject("userName", "Welcome " + auth.getName() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new UserDto());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") UserDto user, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		service.createUser(user);
		return "redirect:register?success";
	}
	
}
