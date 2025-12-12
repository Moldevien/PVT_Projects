package com.lab8.service;

import com.lab8.model.Customer;
import com.lab8.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	private final CustomerRepository repo;
	
	public CustomerService(CustomerRepository repo) {
		this.repo = repo;
	}
	
	public Customer add(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer update(Long id, Customer newCustomer) {
		Customer old = repo.findById(id).orElseThrow();
		old.setName(newCustomer.getName());
		old.setEmail(newCustomer.getEmail());
		old.setPhone(newCustomer.getPhone());
		return repo.save(old);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Customer findById(Long id) {
		return repo.findById(id).orElseThrow();
	}
	
	public List<Customer> findAll() {
		return repo.findAll();
	}
	
	public Optional<Customer> findByEmail(String email) {
		return repo.findByEmail(email);
	}
}
