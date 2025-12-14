package com.lab9.service;

import com.lab9.dto.RegisterDTO;
import com.lab9.model.Customer;
import com.lab9.model.Role;
import com.lab9.model.User;
import com.lab9.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final CustomerService customerService;
	private final PasswordEncoder encoder;
	
	public UserService(UserRepository userRepository, CustomerService customerService, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.customerService = customerService;
		this.encoder = encoder;
	}
	
	public void register(RegisterDTO dto) {
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setEmail(dto.getUsername());
		customer.setPhone(dto.getPhone());
		customerService.add(customer);
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setRole(Role.ROLE_USER);
		user.setCustomer(customer);
		
		userRepository.save(user);
	}
}

