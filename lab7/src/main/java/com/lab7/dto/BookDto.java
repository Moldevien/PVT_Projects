package com.lab7.dto;

public record BookDto(
		Long id,
		String title,
		String author,
		int year,
		String publisher,
		String genre,
		int pages
) {
}
