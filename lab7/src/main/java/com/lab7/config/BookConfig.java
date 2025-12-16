package com.lab7.config;

import com.lab7.model.Book;
import com.lab7.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
	
	@Bean
	public CommandLineRunner commandLineRunner(BookRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				repo.save(new Book(
						null,
						"Dune",
						"Frank Herbert",
						1965,
						"Chilton Books",
						"Sci-Fi",
						412,
						"Epic science fiction saga",
						500000
				));
				
				repo.save(new Book(
						null,
						"The Hobbit",
						"J.R.R. Tolkien",
						1937,
						"George Allen & Unwin",
						"Fantasy",
						310,
						"Adventure of Bilbo Baggins",
						1200000
				));
				
				repo.save(new Book(
						null,
						"Neuromancer",
						"William Gibson",
						1984,
						"Ace",
						"Cyberpunk",
						271,
						"Foundation of cyberpunk genre",
						320000
				));
			}
		};
	}
}
