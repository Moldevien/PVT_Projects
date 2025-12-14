package com.lab9.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "places")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Event> events = new ArrayList<>();
}