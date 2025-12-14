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
				EventDTO rock = new EventDTO();
				rock.setName("Rock Concert");
				rock.setEventDate(LocalDate.now().plusDays(7));
				rock.setPlace(new PlaceDTO("Big Arena", "Main st 1"));
				rock.setTickets(List.of(new TicketDTO(100, 5)));
				eventService.add(rock);
				
				EventDTO jazz = new EventDTO();
				jazz.setName("Jazz Night");
				jazz.setEventDate(LocalDate.now().plusDays(14));
				jazz.setPlace(new PlaceDTO("Jazz Club", "Blue st 12"));
				jazz.setTickets(List.of(new TicketDTO(80, 10)));
				eventService.add(jazz);
				
				EventDTO theatre = new EventDTO();
				theatre.setName("Theatre Play");
				theatre.setEventDate(LocalDate.now().minusDays(3));
				theatre.setPlace(new PlaceDTO("Drama Hall", "Old st 5"));
				theatre.setTickets(List.of(new TicketDTO(60, 8)));
				eventService.add(theatre);
			}
			
			if (customerService.findAll().isEmpty()) {
				Customer c1 = new Customer();
				c1.setName("Ivan Petrenko");
				c1.setEmail("ivan@example.com");
				c1.setPhone("+380501112233");
				customerService.add(c1);
				
				Customer c2 = new Customer();
				c2.setName("Olena Shevchenko");
				c2.setEmail("olena@example.com");
				c2.setPhone("+380672223344");
				customerService.add(c2);
				
				Customer c3 = new Customer();
				c3.setName("Andrii Kovalenko");
				c3.setEmail("andrii@example.com");
				c3.setPhone("+380931234567");
				customerService.add(c3);
			}
		};
	}
}
