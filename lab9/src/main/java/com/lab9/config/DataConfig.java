package com.lab9.config;

import com.lab9.dto.EventDTO;
import com.lab9.dto.PlaceDTO;
import com.lab9.dto.RegisterDTO;
import com.lab9.dto.TicketDTO;
import com.lab9.model.Customer;
import com.lab9.model.Role;
import com.lab9.model.User;
import com.lab9.repository.UserRepository;
import com.lab9.service.CustomerService;
import com.lab9.service.EventService;
import com.lab9.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataConfig {
	private final EventService eventService;
	private final CustomerService customerService;
	private final UserRepository userRepository;
	private final PasswordEncoder encoder;
	
	public DataConfig(EventService eventService, CustomerService customerService, UserRepository userRepository, PasswordEncoder encoder) {
		this.eventService = eventService;
		this.customerService = customerService;
		this.userRepository = userRepository;
		this.encoder = encoder;
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
				
				User u1 = new User();
				u1.setUsername("ivan@example.com");
				u1.setPassword(encoder.encode("1234"));
				u1.setRole(Role.ROLE_USER);
				u1.setCustomer(c1);
				userRepository.save(u1);
				
				Customer c2 = new Customer();
				c2.setName("Olena Shevchenko");
				c2.setEmail("olena@example.com");
				c2.setPhone("+380672223344");
				customerService.add(c2);
				
				User u2 = new User();
				u2.setUsername("olena@example.com");
				u2.setPassword(encoder.encode("1234"));
				u2.setRole(Role.ROLE_USER);
				u2.setCustomer(c2);
				userRepository.save(u2);
				
				Customer c3 = new Customer();
				c3.setName("Andrii Kovalenko");
				c3.setEmail("andrii@example.com");
				c3.setPhone("+380931234567");
				customerService.add(c3);
				
				User u3 = new User();
				u3.setUsername("andrii@example.com");
				u3.setPassword(encoder.encode("1234"));
				u3.setRole(Role.ROLE_USER);
				u3.setCustomer(c3);
				userRepository.save(u3);
				
				Customer adminCustomer = new Customer();
				adminCustomer.setName("Admin");
				adminCustomer.setEmail("admin@system");
				adminCustomer.setPhone("0000000000");
				customerService.add(adminCustomer);
				
				User adminUser = new User();
				adminUser.setUsername("admin");
				adminUser.setPassword(encoder.encode("admin"));
				adminUser.setRole(Role.ROLE_ADMIN);
				adminUser.setCustomer(adminCustomer);
				userRepository.save(adminUser);
			}
		};
	}
}
