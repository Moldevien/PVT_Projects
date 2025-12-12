package com.lab7.repository;

import com.lab7.dto.BookDto;
import com.lab7.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	// Пошук за назвою
	List<Book> findByTitleContainingIgnoreCase(String title);
	
	// Пошук за автором
	List<Book> findByAuthorContainingIgnoreCase(String author);
	
	// Пошук за кількістю сторінок
	List<Book> findByPages(int pages);
	
	// Остання книга автора
	@Query("""
			SELECT book
			FROM Book book
			WHERE book.author = :author
			ORDER BY book.year DESC
			""")
	List<Book> findLatestBookByAuthor(@Param("author") String author);
	
	// Книги, видані видавництвом протягом поточного року
	@Query("""
			SELECT book
			FROM Book book
			WHERE book.publisher = :publisher AND book.year = YEAR(CURRENT_DATE)
			""")
	List<Book> findByPublisherThisYear(@Param("publisher") String publisher);
	
	// Пошук за роком
	List<Book> findByYear(int year);
	
	// Жанр + автор + рік
	List<Book> findByGenreAndAuthorAndYear(String genre, String author, int year);
	
	// Пошук слова в описі
	List<Book> findByDescriptionContainingIgnoreCase(String word);
	
	// Бестселери – наприклад, top 10 по тиражу
	@Query("""
			SELECT new com.lab7.dto.BookDto(
				book.id,
				book.title,
				book.author,
				book.year,
				book.publisher,
				book.genre,
				book.pages
			)
			FROM Book book
			ORDER BY book.circulation DESC
			""")
	List<BookDto> findBestsellers(Pageable pageable);
}
