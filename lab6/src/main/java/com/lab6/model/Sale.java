package com.lab6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private LocalDate saleDate;
	
	public Sale(Seller seller, Client client, Product product, LocalDate saleDate) {
		this.seller = seller;
		this.client = client;
		this.product = product;
		this.saleDate = saleDate;
	}
}
