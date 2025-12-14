package com.lab8.service;

import com.lab8.model.Customer;
import com.lab8.model.Ticket;
import com.lab8.model.TicketStatus;
import com.lab8.repository.CustomerRepository;
import com.lab8.repository.EventRepository;
import com.lab8.repository.PlaceRepository;
import com.lab8.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
	private final TicketRepository ticketRepo;
	private final CustomerRepository customerRepo;
	
	public TicketService(TicketRepository ticketRepo, CustomerRepository customerRepo) {
		this.ticketRepo = ticketRepo;
		this.customerRepo = customerRepo;
	}
	
	//public Ticket assignTicketToCustomer(Long ticketId, Long customerId) {
	@Transactional
	public Ticket add(Long ticketId, Long customerId) {
		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow();
		
		if (ticket.getStatus() == TicketStatus.SOLD) {
			throw new IllegalStateException("Квиток вже проданий");
		}
		
		Customer customer = customerRepo.findById(customerId).orElseThrow();
		ticket.setCustomer(customer);
		ticket.setStatus(TicketStatus.SOLD);
		return ticketRepo.save(ticket);
	}
	
	public Ticket findById(Long id) {
		return ticketRepo.findById(id).orElseThrow();
	}
	
	public List<Ticket> findAll() {
		return ticketRepo.findAll();
	}
}
