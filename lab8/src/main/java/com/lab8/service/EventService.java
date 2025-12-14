package com.lab8.service;

import com.lab8.dto.TicketDTO;
import com.lab8.model.*;
import com.lab8.repository.EventRepository;
import com.lab8.repository.PlaceRepository;
import com.lab8.repository.TicketRepository;
import com.lab8.dto.EventDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
	private final EventRepository eventRepo;
	private final PlaceRepository placeRepo;
	private final TicketRepository ticketRepo;
	
	public EventService(EventRepository eventRepo, PlaceRepository placeRepo, TicketRepository ticketRepo) {
		this.eventRepo = eventRepo;
		this.placeRepo = placeRepo;
		this.ticketRepo = ticketRepo;
	}
	
	@Transactional
	public Event add(EventDTO dto) {
		// 1. знайти або створити Place
		Place place = placeRepo.findByName(dto.getPlace().getName())
				.orElseGet(() -> {
					Place p = new Place();
					p.setName(dto.getPlace().getName());
					p.setAddress(dto.getPlace().getAddress());
					return placeRepo.save(p);
				});
		
		// 2. перевірка: чи уже є event в цьому place у ту ж дату
		if (eventRepo.existsByPlaceAndEventDate(place, dto.getEventDate())) {
			throw new IllegalStateException("У цьому місці вже є подія на цю дату");
		}
		
		// 3. створити Event
		Event event = new Event();
		event.setName(dto.getName());
		event.setEventDate(dto.getEventDate());
		event.setPlace(place);
		
		// 4. створити tickets (генерація номерів)
		List<Ticket> tickets = new ArrayList<>();
		int seatNumber = 1;
		for (TicketDTO pack : dto.getTickets()) {
			for (int i = 0; i < pack.getCount(); i++) {
				Ticket ticket = new Ticket();
				ticket.setCost(pack.getCost());
				ticket.setSeatNumber(seatNumber++);
				ticket.setStatus(TicketStatus.FREE);
				ticket.setEvent(event);
				tickets.add(ticket);
			}
		}
		event.setTickets(tickets);
		
		// 5. зберегти Event (cascade зберігає tickets)
		return eventRepo.save(event);
	}
	
	public Event findById(Long id) {
		return eventRepo.findById(id).orElseThrow();
	}
	
	public List<Event> findAll() {
		return eventRepo.findAll();
	}
	
	public List<Event> findByName(String name) {
		return eventRepo.findByName(name);
	}
	
	public List<Event> findUpcoming() {
		return eventRepo.findByEventDateAfter(LocalDate.now());
	}
	
	public List<Ticket> findFreeTickets(String eventName) {
		return ticketRepo.findByEventNameAndStatus(eventName, TicketStatus.FREE);
	}
}

