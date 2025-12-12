package com.lab8.config;

import com.lab8.dto.EventDTO;
import com.lab8.dto.PlaceDTO;
import com.lab8.dto.TicketDTO;
import com.lab8.model.Customer;
import com.lab8.service.CustomerService;
import com.lab8.service.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataConfig {
	private final EventService eventService;
	private final CustomerService customerService;
	
	public DataConfig(EventService eventService, CustomerService customerService) {
		this.eventService = eventService;
		this.customerService = customerService;
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			if (eventService.findAll().isEmpty()) {
				EventDTO dto = new EventDTO();
				dto.setName("Rock Concert");
				dto.setEventDate(LocalDate.now().plusDays(7));
				dto.setPlace(new PlaceDTO("Big Arena", "Main st 1"));
				dto.setTickets(List.of(new TicketDTO(100, 5, "A-"), new TicketDTO(50, 10, "B-")));
				eventService.add(dto);
			}
			
			if (customerService.findAll().isEmpty()) {
				Customer customer = new Customer();
				customer.setName("Ivan");
				customer.setEmail("ivan@example.com");
				customer.setPhone("+380501112233");
				customerService.add(customer);
			}
		};
	}
}
