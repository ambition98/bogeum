package com.bogeum.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

	@GetMapping("/signup")
	public String signupView() {
		return "/signup/signup";
	}
	
	@PostMapping("/signup")
	public String signup(
		@RequestParam("email") String email,
		@RequestParam("passwd") String passwd) {
		
		
		return "/signin/signin";
	}
}
