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
	
	// Список всіх подій
	@GetMapping
	public String list(Model model) {
		model.addAttribute("events", eventService.findAll());
		return "event-list";
	}
	
	// Форма створення нової події
	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("eventDto", new EventDTO());
		return "event-form";
	}
	
	// Обробка форми створення нової події
	@PostMapping("/create")
	public String create(@ModelAttribute EventDTO dto) {
		eventService.add(dto);
		return "redirect:/events";
	}
	
	// Деталі події
	@GetMapping("/{id}")
	public String details(@PathVariable Long id, Model model) {
		model.addAttribute("event", eventService.findById(id));
		model.addAttribute("freeTickets", eventService.findFreeTickets(eventService.findById(id).getName()));
		return "event-details";
	}
	
	// Список майбутніх подій
	@GetMapping("/upcoming")
	public String upcomingEvents(Model model) {
		model.addAttribute("events", eventService.findUpcoming());
		return "event-list";
	}
	
	// Пошук подій за назвою
	@GetMapping("/search")
	public String search(@RequestParam String eventName, Model model) {
		model.addAttribute("events", eventName.isEmpty() ? eventService.findAll() : eventService.findByName(eventName));
		return "event-list";
	}
}

