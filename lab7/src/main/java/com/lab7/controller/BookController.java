package com.lab7.controller;

import com.lab7.dto.BookDto;
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
	
	// Додавання книги
	@PostMapping
	public Book insert(@RequestBody Book book) {
		return service.add(book);
	}
	
	// Оновлення книги
	@PutMapping("/{id}")
	public Book update(@PathVariable Long id, @RequestBody Book book) {
		return service.update(id, book);
	}
	
	// Видалення книги
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	// Книга за id
	@GetMapping("/{id}")
	public Book byId(@PathVariable Long id) {
		return service.findById(id);
	}
	
	// Всі книги
	@GetMapping
	public List<Book> all() {
		return service.findAll();
	}
	
	// Пошук за назвою
	@GetMapping("/find/title")
	public List<Book> byTitle(@RequestParam String title) {
		return service.findByTitle(title);
	}
	
	// Пошук за автором
	@GetMapping("/find/author")
	public List<Book> byAuthor(@RequestParam String author) {
		return service.findByAuthor(author);
	}
	
	// Пошук за кількістю сторінок
	@GetMapping("/find/pages")
	public List<Book> byPages(@RequestParam int pages) {
		return service.findByPages(pages);
	}
	
	// Остання книга автора
	@GetMapping("/find/latest-by-author")
	public Book latest(@RequestParam String author) {
		return service.latestByAuthor(author);
	}
	
	// Книги, видані видавництвом протягом поточного року
	@GetMapping("/find/publisher-this-year")
	public List<Book> publisherYear(@RequestParam String publisher) {
		return service.findByPublisherThisYear(publisher);
	}
	
	// Пошук за роком
	@GetMapping("/find/year")
	public List<Book> year(@RequestParam int year) {
		return service.findByYear(year);
	}
	
	// Жанр + автор + рік
	@GetMapping("/find/complex")
	public List<Book> complex(
			@RequestParam String genre,
			@RequestParam String author,
			@RequestParam int year) {
		return service.findByGenreAuthorYear(genre, author, year);
	}
	
	// Пошук слова в описі
	@GetMapping("/find/description")
	public List<Book> inDescription(@RequestParam String word) {
		return service.findInDescription(word);
	}
	
	// Бестселери
	@GetMapping("/bestsellers")
	public List<BookDto> bestsellers() {
		return service.bestsellers();
	}
}
