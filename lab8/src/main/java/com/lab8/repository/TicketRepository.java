package com.lab8.repository;

import com.lab8.model.Event;
import com.lab8.model.Ticket;
import com.lab8.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByEventNameAndStatus(String eventName, TicketStatus status);
	List<Ticket> findByEventAndStatus(Event event, TicketStatus status);
	List<Ticket> findByStatus(TicketStatus status);
}
