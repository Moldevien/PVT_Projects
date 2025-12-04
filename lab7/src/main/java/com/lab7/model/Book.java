package com.lab7.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// Назва книги
	private String title;
	// ПІБ автора
	private String author;
	// Рік випуску
	private int year;
	// Видавництво
	private String publisher;
	// Жанр
	private String genre;
	// Кількість сторінок
	private int pages;
	
	// Короткий опис
	@Column(length = 2000)
	private String description;
	
	// Тираж
	private int circulation;
	
	public Book(String title, String author, int year, String publisher, String genre, int pages, String description) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.publisher = publisher;
		this.genre = genre;
		this.pages = pages;
		this.description = description;
	}
}

