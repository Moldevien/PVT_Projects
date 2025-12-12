package com.lab8.controller;

import com.lab8.dto.EventDTO;
import com.lab8.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class EventController {
	private final EventService eventService;
	
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("eventDto", new EventDTO());
		return "event-form";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute EventDTO dto) {
		eventService.add(dto);
		return "redirect:/events";
	}
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("events", eventService.findAll());
		return "event-list";
	}
	
	@GetMapping("/{id}")
	public String details(@PathVariable Long id, Model model) {
		model.addAttribute("event", eventService.findById(id));
		model.addAttribute("freeTickets", eventService.findFreeTickets(eventService.findById(id).getName()));
		return "event-details";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam String q, Model model) {
		model.addAttribute("events", eventService.findByName(q));
		return "event-list";
	}
	
	/*private EventService eventService;
	private CustomerRepository customerRepo;
	
	public EventController(EventService eventService, CustomerRepository customerRepo) {
		this.eventService = eventService;
		this.customerRepo = customerRepo;
	}
	
	@GetMapping("/create")
	public String showEventForm(Model model) {
		model.addAttribute("eventDto", new EventDTO());
		return "event-form"; // Thymeleaf шаблон
	}
	
	@PostMapping("/create")
	public String createEvent(@ModelAttribute EventDTO eventDto) {
		eventService.createEvent(eventDto);
		return "redirect:/events/list";
	}
	
	@GetMapping("/list")
	public String listEvents(Model model) {
		model.addAttribute("events", eventService.findUpcomingEvents());
		return "event-list";
	}
	
	@GetMapping("/free-tickets")
	public String freeTickets(@RequestParam String eventName, Model model) {
		model.addAttribute("tickets", eventService.findFreeTickets(eventName));
		return "ticket-list";
	}
	
	@PostMapping("/assign-ticket")
	public String assignTicket(@RequestParam Long ticketId, @RequestParam Long customerId) {
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		eventService.assignTicketToCustomer(ticketId, customer);
		return "redirect:/events/list";
	}*/
}

