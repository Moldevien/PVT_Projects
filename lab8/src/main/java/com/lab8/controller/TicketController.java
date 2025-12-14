package com.lab8.controller;

import com.lab8.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	private final TicketService ticketService;
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@PostMapping("/buy")
	public String buy(@RequestParam Long ticketId, @RequestParam Long customerId, Model model) {
		try {
			ticketService.add(ticketId, customerId);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/events";
	}
}
