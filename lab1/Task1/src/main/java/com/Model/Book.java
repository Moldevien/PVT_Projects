package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Мінімальний пропонований набір характеристик книги:
//− автор книги;
//− назва книги;
//− жанр;
//− рік видання;
//− видавництво;
//− кількість сторінок.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Publication {
	// Автор книги
	private String author;
	// Назва книги
	private String title;
	// Жанр
	private String genre;
	// Рік видання
	private int year;
	// Видавництво
	private String publisher;
	// Кількість сторінок
	private int pages;
}
