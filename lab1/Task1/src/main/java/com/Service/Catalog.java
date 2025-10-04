package com.Service;

import com.Model.Almanac;
import com.Model.Book;
import com.Model.Newspaper;
import com.Model.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

//Для каталогу передбачити таке:
//− можливість тестової ініціалізації;
//− додавання об'єкта конкретного типу;
//− додавання об'єкта випадкового типу;
//− видалення об'єкта;
//− коригування об'єкта;
//− виведення всього каталогу на екран з можливістю групувати об'єкти за їх типом;
//− пошук за назвою/ видавництвом/ роком_випуску/ автором.

@Data
@AllArgsConstructor
public class Catalog {
	private List<Publication> publications;
	
	public Catalog() {
		publications = new ArrayList<>();
	}
	
	/**
	 * Додавання об'єкта конкретного типу
	 */
	public boolean add(Publication item) {
		if (publications.contains(item)) {
			return false;
		}
		
		publications.add(item);
		return true;
	}
	
	/**
	 * Додавання об'єкта випадкового типу
	 */
	public void addRandom() {
		Random r = new Random();
		int choice = r.nextInt(3);
		switch (choice) {
			case 0:
				publications.add(new Book(
						"Автор " + r.nextInt(100),
						"Книга " + r.nextInt(1000),
						"Жанр " + r.nextInt(100),
						1965 + r.nextInt(60),
						"Видавництво " + r.nextInt(100),
						200 + r.nextInt(300)));
				break;
			case 1:
				publications.add(new Newspaper(
						r.nextInt(1000),
						"Газета " + r.nextInt(50),
						LocalDate.now().minusDays(r.nextInt(30)).toString(),
						"Колонка 1: Журналіст 1; Колонка 2: Журналіст 2"));
				break;
			case 2:
				publications.add(new Almanac(
						"Альманах " + r.nextInt(20),
						"Книга " + r.nextInt(1000),
						"Жанр " + r.nextInt(100),
						1965 + r.nextInt(60),
						"Видавництво " + r.nextInt(100),
						200 + r.nextInt(300),
						"Твір " + r.nextInt(100) + "Твір " + r.nextInt(100)));
				break;
		}
	}
	
	/**
	 * Видалення об'єкта
	 */
	public boolean remove(Publication item) {
		if (item == null) {
			return false;
		}
		
		publications.remove(item);
		return true;
	}
	
	/**
	 * Коригування об'єкта
	 */
	public boolean update(Publication oldItem, Publication newItem) {
		int idx = publications.indexOf(oldItem);
		if (idx != -1) {
			publications.set(idx, newItem);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Виведення всього каталогу на екран з можливістю групувати об'єкти за їх типом
	 */
	public void displayCatalog(List<Publication> publications) {
		if (publications.isEmpty()) {
			out.println("Каталог порожній.");
			return;
		}
		
		out.println("Каталог:");
		
		out.println("\nАльманахи:");
		publications.stream().filter(item -> item instanceof Almanac).forEach(x -> out.println(x));
		
		out.println("\nКниги:");
		publications.stream().filter(item -> item instanceof Book).forEach(x -> out.println(x));
		
		out.println("\nГазети:");
		publications.stream().filter(item -> item instanceof Newspaper).forEach(x -> out.println(x));
	}
	
	/**
	 * Пошук за назвою
	 */
	public List<Publication> searchByTitle(String text) {
		return publications.stream()
				.filter(publication -> publication.getTitle().contains(text.toLowerCase()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Пошук за видавництвом
	 */
	public List<Publication> searchByPublisher(String text) {
		List<Publication> results = new ArrayList<>();
		
		for (Publication item : publications) {
			if (item instanceof Book) {
				Book book = (Book) item;
				if (book.getPublisher().toLowerCase().contains(text.toLowerCase())) {
					results.add(book);
				}
			} else if (item instanceof Almanac) {
				Almanac almanac = (Almanac) item;
				if (almanac.getPublisher().toLowerCase().contains(text.toLowerCase())) {
					results.add(almanac);
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Пошук за роком_випуску
	 */
	public List<Publication> searchByYear(int year) {
		List<Publication> results = new ArrayList<>();
		
		for (Publication item : publications) {
			if (item instanceof Book) {
				Book book = (Book) item;
				if (book.getYear() == year) {
					results.add(book);
				}
			} else if (item instanceof Almanac) {
				Almanac almanac = (Almanac) item;
				if (almanac.getYear() == year) {
					results.add(almanac);
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Пошук за автором
	 */
	public List<Publication> searchByAuthor(String text) {
		return publications.stream()
				.filter(item -> item instanceof Book)
				.map(item -> (Book) item)
				.filter(book -> book.getAuthor().toLowerCase().contains(text.toLowerCase()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Повернути будь-яку публікацію за назвою (спрощений варіант)
	 */
	public Publication getPublication(String title) {
		return publications.stream()
				.filter(publication -> publication.getTitle().equalsIgnoreCase(title))
				.findFirst()
				.orElse(null);
	}
}
