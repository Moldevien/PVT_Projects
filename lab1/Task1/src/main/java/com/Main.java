package com;

//Завдання 1. Розробити програму «Каталог бібліотеки».
//У бібліотеці можуть зберігатися книги, газети та альманахи (збірки творів кількох авторів).

import com.Model.Almanac;
import com.Model.Book;
import com.Model.Newspaper;
import com.Model.Publication;
import com.Service.Catalog;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(in);
		
		Catalog catalog = new Catalog();
		
		while (true) {
			out.println("\nМеню:");
			out.println("1. Додати об'єкт");
			out.println("2. Додати випадковий об'єкт");
			out.println("3. Видалити об'єкт");
			out.println("4. Коригувати об'єкт");
			out.println("5. Показати каталог");
			out.println("6. Пошук за назвою");
			out.println("7. Пошук за видавництвом");
			out.println("8. Пошук за роком випуску");
			out.println("9. Пошук за автором");
			out.println("0. Вихід");
			out.print("Виберіть опцію: ");
			
			int choice = s.nextInt();
			s.nextLine();
			switch (choice) {
				//Додати об'єкт
				case 1: {
					out.println("Що саме ви хочете додати?");
					out.println("1. Альманах");
					out.println("2. Книгу");
					out.println("3. Газету");
					out.print("Виберіть опцію: ");
					
					int type = s.nextInt();
					s.nextLine();
					
					switch (type) {
						//Додати альманах
						case 1: {
							out.println("\nУведіть дані альманаху:");
							
							out.print("Назва: ");
							String title = s.nextLine();
							
							out.print("Назва книги: ");
							String bookTitle = s.nextLine();
							
							out.print("Жанр: ");
							String genre = s.nextLine();
							
							out.print("Рік: ");
							int year = s.nextInt();
							s.nextLine();
							
							out.print("Видавництво: ");
							String publisher = s.nextLine();
							
							out.print("Кількість сторінок: ");
							int pages = s.nextInt();
							s.nextLine();
							
							out.print("Перелік творів (книг), які у ньому надруковані: ");
							String works = s.nextLine();
							
							if (catalog.add(new Almanac(title, bookTitle, genre, year, publisher, pages, works)))
								out.println("Альманах додано.");
							else
								out.println("Альманах з такою назвою вже існує.");
							break;
						}
						//Додати книгу
						case 2: {
							out.println("\nУведіть дані книги:");
							
							out.print("Автор: ");
							String author = s.nextLine();
							
							out.print("Назва: ");
							String title = s.nextLine();
							
							out.print("Жанр: ");
							String genre = s.nextLine();
							
							out.print("Рік: ");
							int year = s.nextInt();
							s.nextLine();
							
							out.print("Видавництво: ");
							String publisher = s.nextLine();
							
							out.print("Кількість сторінок: ");
							int pages = s.nextInt();
							s.nextLine();
							
							if (catalog.add(new Book(author, title, genre, year, publisher, pages)))
								out.println("Книгу додано.");
							else
								out.println("Книга з такою назвою вже існує.");
							break;
						}
						//Додати газету
						case 3: {
							out.println("\nУведіть дані газети:");
							
							out.print("Номер газети: ");
							int number = s.nextInt();
							s.nextLine();
							
							out.print("Назва: ");
							String title = s.nextLine();
							
							out.print("Дата випуску: ");
							String date = s.nextLine();
							
							out.print("Перелік колонок газети: ");
							String columns = s.nextLine();
							
							if (catalog.add(new Newspaper(number, title, date, columns)))
								out.println("Газету додано.");
							else
								out.println("Газета з такою назвою вже існує.");
							break;
						}
					}
					break;
				}
				//Додати випадковий об'єкт
				case 2: {
					catalog.addRandom();
					out.println("Випадковий об'єкт додано.");
					break;
				}
				//Видалити об'єкт
				case 3: {
					out.print("Введіть назву: ");
					if (catalog.remove(catalog.getPublication(s.nextLine())))
						out.println("Об'єкт видалено.");
					else
						out.println("Об'єкт не знайдено.");
					break;
				}
				//Коригувати об'єкт
				case 4: {
					out.print("Введіть назву публікації для коригування: ");
					Publication item1 = catalog.getPublication(s.nextLine());
					Publication item2 = null;
					
					if (item1 == null) {
						out.println("Публікацію не знайдено.");
						break;
					}
					
					out.println("Уведіть нові дані:");
					switch (item1.getClass().getName()) {
						//Додати альманах
						case "Almanac": {
							out.println("\nУведіть дані альманаху:");
							
							out.print("Назва: ");
							String title = s.nextLine();
							
							out.print("\nНазва книги: ");
							String bookTitle = s.nextLine();
							
							out.print("\nЖанр: ");
							String genre = s.nextLine();
							
							out.print("\nРік: ");
							int year = s.nextInt();
							
							out.print("\nВидавництво: ");
							String publisher = s.nextLine();
							
							out.print("\nКількість сторінок: ");
							int pages = s.nextInt();
							
							out.print("\nПерелік творів (книг), які у ньому надруковані: ");
							String works = s.nextLine();
							
							item2 = new Almanac(title, bookTitle, genre, year, publisher, pages, works);
							break;
						}
						//Додати книгу
						case "Book": {
							out.println("\nУведіть дані книги:");
							
							out.print("Автор: ");
							String author = s.nextLine();
							
							out.print("\nНазва: ");
							String title = s.nextLine();
							
							out.print("\nЖанр: ");
							String genre = s.nextLine();
							
							out.print("\nРік: ");
							int year = s.nextInt();
							
							out.print("\nВидавництво: ");
							String publisher = s.nextLine();
							
							out.print("\nКількість сторінок: ");
							int pages = s.nextInt();
							
							item2 = new Book(author, title, genre, year, publisher, pages);
							break;
						}
						//Додати газету
						case "Newspaper": {
							out.println("\nУведіть дані газети:");
							
							out.print("Номер газети: ");
							int number = s.nextInt();
							
							out.print("\nНазва: ");
							String title = s.nextLine();
							
							out.print("\nДата випуску: ");
							String date = s.nextLine();
							
							out.print("\nПерелік колонок газети: ");
							String columns = s.nextLine();
							
							item2 = new Newspaper(number, title, date, columns);
							break;
						}
					}
					
					if (catalog.update(item1, item2))
						out.println("Об'єкт відкориговано.");
					else
						out.println("Об'єкт не знайдено.");
					break;
				}
				//Показати каталог
				case 5: {
					catalog.displayCatalog(catalog.getPublications());
					break;
				}
				//Пошук за назвою
				case 6: {
					out.print("Введіть назву: ");
					List<Publication> results = catalog.searchByTitle(s.nextLine());
					
					if (results.equals(null)) {
						out.println("Об'єкти не знайдені.");
						break;
					}
					results.forEach(x -> out.println(x));
					break;
				}
				//Пошук за видавництвом
				case 7: {
					out.print("Введіть видавництво: ");
					List<Publication> results = catalog.searchByPublisher(s.nextLine());
					
					if (results.equals(null)) {
						out.println("Об'єкти не знайдені.");
						break;
					}
					results.forEach(x -> out.println(x));
					break;
				}
				//Пошук за роком випуску
				case 8: {
					out.print("Введіть рік випуску: ");
					List<Publication> results = catalog.searchByYear(s.nextInt());
					
					if (results.equals(null)) {
						out.println("Об'єкти не знайдені.");
						break;
					}
					results.forEach(x -> out.println(x));
					break;
				}
				//Пошук за автором
				case 9: {
					out.print("Введіть автора: ");
					List<Publication> results = catalog.searchByAuthor(s.nextLine());
					
					if (results.equals(null)) {
						out.println("Об'єкти не знайдені.");
						break;
					}
					results.forEach(x -> out.println(x));
					break;
				}
				//Вихід
				case 0: {
					return;
				}
			}
		}
	}
}