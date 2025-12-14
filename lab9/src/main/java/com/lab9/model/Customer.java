package com.lab9.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@OneToOne(mappedBy = "customer")
	private User user;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Ticket> tickets;
}
