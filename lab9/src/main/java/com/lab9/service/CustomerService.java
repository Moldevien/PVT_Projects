package com.lab9.service;

import com.lab9.model.Customer;
import com.lab9.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
	private final CustomerRepository repo;
	
	public CustomerService(CustomerRepository repo) {
		this.repo = repo;
	}
	
	public Customer add(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer findById(Long id) {
		return repo.findById(id).orElseThrow();
	}
	
	public List<Customer> findAll() {
		return repo.findAll();
	}
}
