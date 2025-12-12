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
	
	public Book add(Book book) {
		return repo.save(book);
	}
	
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
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Book findById(Long id) {
		return repo.findById(id).orElseThrow();
	}
	
	public List<Book> findAll() {
		return repo.findAll();
	}
	
	// Методи пошуку
	public List<Book> findByTitle(String title) {
		return repo.findByTitleContainingIgnoreCase(title);
	}
	
	public List<Book> findByAuthor(String author) {
		return repo.findByAuthorContainingIgnoreCase(author);
	}
	
	public List<Book> findByYear(int year) {
		return repo.findByYear(year);
	}
	
	public List<Book> findByPages(int pages) {
		return repo.findByPages(pages);
	}
	
	public List<Book> findByPublisherThisYear(String publisher) {
		return repo.findByPublisherThisYear(publisher);
	}
	
	public List<Book> findInDescription(String word) {
		return repo.findByDescriptionContainingIgnoreCase(word);
	}
	
	public Book latestByAuthor(String author) {
		return repo.findLatestBookByAuthor(author).stream().findFirst().orElse(null);
	}
	
	public List<Book> findByGenreAuthorYear(String genre, String author, int year) {
		return repo.findByGenreAndAuthorAndYear(genre, author, year);
	}
	
	public List<BookDto> bestsellers() {
		return repo.findBestsellers(PageRequest.of(0, 10));
	}
}
