package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Характеристиками альманаху:
//- назва альманаху,
//- назва книги,
//- жанр,
//- рік видання,
//- видавництво,
//- кількість сторінок
//- перелік творів (книг), які у ньому надруковані.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almanac implements Publication {
	// Назва альманаху
	private String title;
	// Назва книги
	private String bookTitle;
	// Жанр
	private String genre;
	// Рік видання
	private int year;
	// Видавництво
	private String publisher;
	// Кількість сторінок
	private int pages;
	// Перелік творів (книг), які у ньому надруковані
	private String works;
}
