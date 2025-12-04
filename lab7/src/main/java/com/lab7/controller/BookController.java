package com.lab7.controller;

import com.lab7.dto.BestsellerDto;
import com.lab7.model.Book;
import com.lab7.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@PostMapping
	public Book insert(@RequestBody Book book) {
		return service.add(book);
	}
	
	@PutMapping("/{id}")
	public Book update(@PathVariable Long id, @RequestBody Book book) {
		return service.update(id, book);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping("/{id}")
	public Book byId(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<Book> all() {
		return service.findAll();
	}
	
	// Пошуки
	@GetMapping("/find/title")
	public List<Book> byTitle(@RequestParam String title) {
		return service.findByTitle(title);
	}
	
	@GetMapping("/find/author")
	public List<Book> byAuthor(@RequestParam String author) {
		return service.findByAuthor(author);
	}
	
	@GetMapping("/find/pages")
	public List<Book> byPages(@RequestParam int pages) {
		return service.findByPages(pages);
	}
	
	@GetMapping("/find/latest-by-author")
	public Book latest(@RequestParam String author) {
		return service.latestByAuthor(author);
	}
	
	@GetMapping("/find/publisher-this-year")
	public List<Book> publisherYear(@RequestParam String publisher) {
		return service.findByPublisherThisYear(publisher);
	}
	
	@GetMapping("/find/year")
	public List<Book> year(@RequestParam int year) {
		return service.findByYear(year);
	}
	
	@GetMapping("/find/complex")
	public List<Book> complex(
			@RequestParam String genre,
			@RequestParam String author,
			@RequestParam int year) {
		return service.byGenreAuthorYear(genre, author, year);
	}
	
	@GetMapping("/find/description")
	public List<Book> inDescription(@RequestParam String word) {
		return service.findInDescription(word);
	}
	
	@GetMapping("/bestsellers")
	public List<BestsellerDto> bestsellers() {
		return service.bestsellers();
	}
}
