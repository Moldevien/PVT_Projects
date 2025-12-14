package com.lab9.controller;

import com.lab9.dto.RegisterDTO;
import com.lab9.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new RegisterDTO());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute RegisterDTO dto) {
		userService.register(dto);
		return "redirect:/login";
	}
}
