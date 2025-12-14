package com.lab9.service;

import com.lab9.model.Customer;
import com.lab9.model.Ticket;
import com.lab9.model.TicketStatus;
import com.lab9.model.User;
import com.lab9.repository.CustomerRepository;
import com.lab9.repository.TicketRepository;
import com.lab9.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
	private final TicketRepository ticketRepo;
	private final UserRepository userRepo;
	
	public TicketService(TicketRepository ticketRepo, UserRepository userRepository) {
		this.ticketRepo = ticketRepo;
		this.userRepo = userRepository;
	}
	
	//public Ticket assignTicketToCustomer(Long ticketId, Long customerId) {
	@Transactional
	public Ticket add(Long ticketId, String username) {
		User user = userRepo.findByUsername(username).orElseThrow();
		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow();
		
		if (ticket.getStatus() == TicketStatus.SOLD) {
			throw new IllegalStateException("Квиток вже проданий");
		}
		
		ticket.setCustomer(user.getCustomer());
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
