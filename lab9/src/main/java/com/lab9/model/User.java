package com.lab9.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username; // email або логін
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToOne
	private Customer customer; // зв’язок з покупцем
}

