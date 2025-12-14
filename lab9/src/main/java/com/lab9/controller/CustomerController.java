package com.lab9.controller;

import com.lab9.model.Customer;
import com.lab9.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute Customer customer) {
		customerService.add(customer);
		return "redirect:/events";
	}
}
