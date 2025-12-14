package com.lab9.controller;

import com.lab9.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
	@PreAuthorize("hasRole('USER')")
	public String buy(@RequestParam Long ticketId, Authentication auth, Model model) {
		try {
			ticketService.add(ticketId, auth.getName());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/events";
	}
}
