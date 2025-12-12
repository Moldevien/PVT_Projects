package com.lab8.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double cost;
	private String number; // номер місця або унікальний код
	
	@Enumerated(EnumType.STRING)
	private TicketStatus status = TicketStatus.FREE;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Event event;
	
	public Ticket(double cost, String number, TicketStatus status, Customer customer, Event event) {
		this.cost = cost;
		this.number = number;
		this.status = status;
		this.customer = customer;
		this.event = event;
	}
}
