package com.eval.coronakit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.CMSUser;
import com.eval.coronakit.service.UserDetailsServiceImpl;

@Controller
public class DefaultController {

	@Autowired
	private UserDetailsServiceImpl userService;

	@GetMapping({ "", "/", "/home" })
	public String showHome() {
		return "index";
	}

	@GetMapping("/login")
	public String gotoLogin() {
		String view = "loginPage";

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			view = "redirect:/home";
		}

		return view;
	}

	@RequestMapping("/header")
	public ModelAndView showHeader() {

		ModelAndView mv = new ModelAndView("header");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			String role = new ArrayList<>(auth.getAuthorities()).get(0).getAuthority();

			mv.addObject("unm", auth.getName());
			mv.addObject("role", role);
		}
		return mv;

	}

	@GetMapping("/register")
	public ModelAndView showRegisterationForm() {
		return new ModelAndView("registerFormPage", "user", new CMSUser());
	}

	@PostMapping("/register")
	public ModelAndView doRegisteration(@ModelAttribute("user") CMSUser user) {
		user.setRole("USER");
		user = userService.save(user);
		return new ModelAndView("index", "msg", "User got registered! with ID#" + user.getUserId());
	}
}
