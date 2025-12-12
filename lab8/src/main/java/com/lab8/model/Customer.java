package com.lab8.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	
	@OneToMany(mappedBy = "customer")
	private List<Ticket> tickets;
	
	public Customer(String name, String email, String phone, List<Ticket> tickets) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.tickets = tickets;
	}
}
