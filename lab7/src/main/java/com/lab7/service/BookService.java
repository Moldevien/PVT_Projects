package com.lab7.service;

import com.lab7.dto.BookDto;
import com.lab7.model.Book;
import com.lab7.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	private final BookRepository repo;
	
	public BookService(BookRepository repo) {
		this.repo = repo;
	}
	
	// Додавання книги
	public Book add(Book book) {
		return repo.save(book);
	}
	
	// Оновлення книги
	public Book update(Long id, Book newBook) {
		Book old = repo.findById(id).orElseThrow();
		old.setTitle(newBook.getTitle());
		old.setAuthor(newBook.getAuthor());
		old.setYear(newBook.getYear());
		old.setPublisher(newBook.getPublisher());
		old.setGenre(newBook.getGenre());
		old.setPages(newBook.getPages());
		old.setDescription(newBook.getDescription());
		old.setCirculation(newBook.getCirculation());
		return repo.save(old);
	}
	
	// Видалення книги
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	// Книга за id
	public Book findById(Long id) {
		return repo.findById(id).orElseThrow();
	}
	
	// Всі книги
	public List<Book> findAll() {
		return repo.findAll();
	}
	
	// Пошук за назвою
	public List<Book> findByTitle(String title) {
		return repo.findByTitleContainingIgnoreCase(title);
	}
	
	// Пошук за автором
	public List<Book> findByAuthor(String author) {
		return repo.findByAuthorContainingIgnoreCase(author);
	}
	
	// Пошук за роком
	public List<Book> findByYear(int year) {
		return repo.findByYear(year);
	}
	
	// Пошук за кількістю сторінок
	public List<Book> findByPages(int pages) {
		return repo.findByPages(pages);
	}
	
	// Книги, видані видавництвом протягом поточного року
	public List<Book> findByPublisherThisYear(String publisher) {
		return repo.findByPublisherThisYear(publisher);
	}
	
	// Пошук слова в описі
	public List<Book> findInDescription(String word) {
		return repo.findByDescriptionContainingIgnoreCase(word);
	}
	
	// Жанр + автор + рік
	public List<Book> findByGenreAuthorYear(String genre, String author, int year) {
		return repo.findByGenreAndAuthorAndYear(genre, author, year);
	}
	
	// Бестселери
	public List<BookDto> bestsellers() {
		return repo.findBestsellers(PageRequest.of(0, 10));
	}
	
	// Остання книга автора
	public Book latestByAuthor(String author) {
		return repo.findLatestBookByAuthor(author).stream().findFirst().orElse(null);
	}
}
