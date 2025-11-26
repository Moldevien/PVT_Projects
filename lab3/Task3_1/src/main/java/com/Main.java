package com;

import com.Service.DB;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(in);
		DB db = new DB();
		
		while (true) {
			out.println("\nМеню:");
			out.println("1. Додати об'єкт");
			out.println("2. Видалити об'єкт");
			out.println("3. Коригувати об'єкт (WIP)");
			out.println("4. Показати усі об'єкти");
			out.println("5. Показати усі напої");
			out.println("6. Показати 3 найулюбленіші напої за попередній місяць");
			out.println("7. Показати усі десерти");
			out.println("8. Показати 5 найулюбленіших десертів за попередні 10 днів");
			out.println("9. Показати інформацію про всіх барист");
			out.println("10. Показати інформацію про всіх офіціантів");
			out.println("11. Підрахувати середню суму замовлення на конкретний день");
			out.println("12. Показати інформацію про найбільше або найбільші (якщо таких замовлень декілька) замовлення на конкретну дату");
			out.println("13. Показати інформацію про постійного клієнта або клієнтів(якщо таких декілька)");
			out.println("14. Показати розклад роботи для усіх працівників кав’ярні сьогодні/завтра/довільна дата");
			out.println("0. Вихід");
			out.print("Виберіть опцію: ");
			
			try {
				String choice = s.nextLine();
				
				switch (choice) {
					//Додати об'єкт
					case "1": {
						out.println("Що саме ви хочете додати?");
						out.println("1. Клієнт");
						out.println("2. Товар-замовлення");
						out.println("3. Замовлення");
						out.println("4. Позиція");
						out.println("5. Тип товару");
						out.println("6. Товар");
						out.println("7. Персонал");
						out.println("8. Розклад");
						out.println("9. Випадковий");
						
						out.print("Виберіть опцію: ");
						
						try {
							String type = s.nextLine();
							
							switch (type) {
								// Додати клієнта
								//- id
								//- дата народження
								//- телефон
								//- пошта
								//- знижка
								case "1": {
									out.println("\nУведіть дані клієнта:");
									
									out.print("Прізвище: ");
									String last_name = s.nextLine();
									
									out.print("Ім'я: ");
									String first_name = s.nextLine();
									
									out.print("Дата народження: ");
									String birth_date = s.nextLine();
									
									out.print("Телефон: ");
									String phone = s.nextLine();
									
									out.print("Пошта: ");
									String mail = s.nextLine();
									
									out.print("Знижка: ");
									double discount = s.nextDouble();
									s.nextLine();
									
									String query = "insert into clients " +
											"(first_name, last_name, birth_date, phone, mail, discount)" +
											"values (" +
											first_name + ", " + last_name + ", " + birth_date + ", " + phone + ", " + mail + ", " + discount + ")";
									
									if (db.QueryExecute(query))
										out.println("Клієнта додано.");
									else
										out.println("Виникла помилка під час додавання клієнта.");
									break;
								}
								// Додати товар-замовлення
								//- id
								//- id замовлення
								//- id товару
								//- кількість
								case "2": {
									out.println("\nУведіть дані товару-замовлення:");
									
									out.print("ID замовлення: ");
									int order_id = s.nextInt();
									s.nextLine();
									
									out.print("ID товару: ");
									int product_id = s.nextInt();
									s.nextLine();
									
									out.print("Кількість: ");
									int quantity = s.nextInt();
									s.nextLine();
									
									String query = "insert into order_products " +
											"(order_id, product_id, quantity)" +
											"values (" +
											order_id + ", " + product_id + ", " + quantity + ")";
									
									if (db.QueryExecute(query))
										out.println("Товар-замовлення додано.");
									else
										out.println("Виникла помилка під час додавання товар-замовлення.");
									break;
								}
								// Додати замовлення
								//- id
								//- id клієнта
								//- id офіціанта
								//- дата і час
								case "3": {
									out.println("\nУведіть дані замовлення:");
									
									out.print("ID клієнта: ");
									int client_id = s.nextInt();
									s.nextLine();
									
									out.print("ID офіціанта: ");
									int staff_id = s.nextInt();
									s.nextLine();
									
									out.print("Дата і час (YYYY-MM-DD HH:MM:SS): ");
									String order_date = s.nextLine();
									
									String query = "insert into orders " +
											"(client_id, staff_id, order_date)" +
											"values (" +
											client_id + ", " + staff_id + ", " + order_date + ")";
									
									if (db.QueryExecute(query))
										out.println("Замовлення додано.");
									else
										out.println("Виникла помилка під час додавання замовлення.");
									break;
								}
								// Додати позицію
								//- id
								//- назва (Бариста, офіціант, кондитер)
								case "4": {
									out.println("\nУведіть дані позиції:");
									
									out.print("Назва: ");
									String name = s.nextLine();
									
									String query = "insert into positions " +
											"(name)" +
											"values (" +
											name + ")";
									
									if (db.QueryExecute(query))
										out.println("Позицію додано.");
									else
										out.println("Виникла помилка під час додавання позиції.");
									break;
								}
								// Додати тип товару
								//- id
								//- назва (напій, десерт)
								case "5": {
									out.println("\nУведіть дані типу товару:");
									
									out.print("Назва: ");
									String name = s.nextLine();
									
									String query = "insert into product_types " +
											"(name)" +
											"values (" +
											name + ")";
									
									if (db.QueryExecute(query))
										out.println("Тип товару додано.");
									else
										out.println("Виникла помилка під час додавання типу товару.");
									break;
								}
								// Додати товар
								//- id
								//- назва українською
								//- назва англійською
								//- ціна
								//- id типу товару
								case "6": {
									out.println("\nУведіть дані товару:");
									
									out.print("Назва українською: ");
									String name_ua = s.nextLine();
									
									out.print("Назва англійською: ");
									String name_en = s.nextLine();
									
									out.print("Ціна: ");
									double price = s.nextDouble();
									s.nextLine();
									
									out.print("ID типу товару: ");
									int type_id = s.nextInt();
									s.nextLine();
									
									String query = "insert into products " +
											"(name_ua, name_en, price, type_id)" +
											"values (" +
											name_ua + ", " + name_en + ", " + price + ", " + type_id + ")";
									
									if (db.QueryExecute(query))
										out.println("Товар додано.");
									else
										out.println("Виникла помилка під час додавання товару.");
									break;
								}
								// Додати персонал
								//- id
								//- ім'я
								//- прізвище
								//- дата народження
								//- телефон
								//- адреса
								//- id позиції
								case "7": {
									out.println("\nУведіть дані персоналу:");
									
									out.print("Ім'я: ");
									String first_name = s.nextLine();
									
									out.print("Прізвище: ");
									String last_name = s.nextLine();

//							out.print("Дата народження: ");
//							String birth_date = s.nextLine();
									
									out.print("Телефон: ");
									String phone = s.nextLine();
									
									out.print("Пошта: ");
									String mail = s.nextLine();
									
									out.print("ID позиції: ");
									int position_id = s.nextInt();
									s.nextLine();
									
									String query = "insert into staff " +
											"(first_name, last_name, phone, mail, position_id)" +
											"values (" +
											first_name + ", " + last_name + ", " + phone + ", " + mail + ", " + position_id + ")";
									
									if (db.QueryExecute(query))
										out.println("Персонал додано.");
									else
										out.println("Виникла помилка під час додавання персоналу.");
									break;
								}
								// Додати розклад
								//- id
								//- id працівника
								//- дата
								case "8": {
									out.println("\nУведіть дані розкладу:");
									
									out.print("ID працівника: ");
									int staff_id = s.nextInt();
									s.nextLine();
									
									out.print("Дата (YYYY-MM-DD): ");
									String work_date = s.nextLine();
									
									String query = "insert into staff_schedule " +
											"(staff_id, work_date)" +
											"values (" +
											staff_id + ", " + work_date + ")";
									
									if (db.QueryExecute(query))
										out.println("Розклад додано.");
									else
										out.println("Виникла помилка під час додавання розкладу.");
									break;
								}
								// Додати випадковий об'єкт
								case "9": {
									Random r = new Random();
									
									out.println("Який випадковий об'єкт ви хочете додати?");
									out.println("1. Клієнт");
									out.println("2. Товар-замовлення");
									out.println("3. Замовлення");
									out.println("4. Позиція");
									out.println("5. Тип товару");
									out.println("6. Товар");
									out.println("7. Персонал");
									out.println("8. Розклад");
									out.print("Виберіть опцію: ");
									
									int randomType = s.nextInt();
									s.nextLine();
									
									out.println("Скільки випадкових об'єктів ви хочете додати?");
									int count = s.nextInt();
									s.nextLine();
									
									for (int i = 0; i < count; i++) {
										switch (randomType) {
											// Додати клієнта
											case 1: {
												String query = "insert into clients " +
														"(first_name, last_name, birth_date, phone, mail, discount)" +
														"values ( " +
														"'Ім`я " + r.nextInt(1000) +
														"', 'Прізвище " + r.nextInt(1000) +
														"', '" + (1970 + r.nextInt(35)) + "-" + (1 + r.nextInt(12)) + "-" + (1 + r.nextInt(28)) +
														"', '+38050" + (1000000 + r.nextInt(9000000)) +
														"', '" + (1 + r.nextInt(1000000000)) + "@mail.com" +
														"', " + (r.nextDouble() * 0.5) + ")";
												
												if (db.QueryExecute(query))
													out.println("Клієнта додано.");
												else
													out.println("Виникла помилка під час додавання клієнта.");
												break;
											}
											// Додати товар-замовлення
											case 2: {
												String query = "insert into order_products " +
														"(order_id, product_id, quantity)" +
														"values (" +
														(1 + r.nextInt(20)) + ", " +
														(1 + r.nextInt(20)) + ", " +
														(1 + r.nextInt(5)) + ")";
												
												if (db.QueryExecute(query))
													out.println("Товар-замовлення додано.");
												else
													out.println("Виникла помилка під час додавання товар-замовлення.");
												break;
											}
											// Додати замовлення
											case 3: {
												String query = "insert into orders " +
														"(client_id, staff_id, order_date)" +
														"values (" +
														(1 + r.nextInt(20)) + ", " +
														(1 + r.nextInt(20)) + ", '" +
														(2023 + r.nextInt(2)) + "-" + (1 + r.nextInt(12)) + "-" + (1 + r.nextInt(28)) + " " +
														(r.nextInt(24)) + ":" + (r.nextInt(60)) + ":" + (r.nextInt(60)) + "')";
												
												if (db.QueryExecute(query))
													out.println("Замовлення додано.");
												else
													out.println("Виникла помилка під час додавання замовлення.");
												break;
											}
											// Додати позицію
											case 4: {
												String query = "insert into positions " +
														"(name)" +
														"values (" +
														"'Позиція " + r.nextInt(1000) + "')";
												
												if (db.QueryExecute(query))
													out.println("Позицію додано.");
												else
													out.println("Виникла помилка під час додавання позиції.");
												break;
											}
											// Додати тип товару
											case 5: {
												String query = "insert into product_types " +
														"(name)" +
														"values (" +
														"'Тип товару " + r.nextInt(1000) + "')";
												
												if (db.QueryExecute(query))
													out.println("Тип товару додано.");
												else
													out.println("Виникла помилка під час додавання типу товару.");
												break;
											}
											// Додати товар
											case 6: {
												String query = "insert into products " +
														"(name_ua, name_en, price, type_id)" +
														"values (" +
														"'Товар " + r.nextInt(1000) + " UA', 'Product " + r.nextInt(1000) + " EN', " +
														(10 + r.nextInt(100)) + ", " +
														(1 + r.nextInt(2)) + ")";
												
												if (db.QueryExecute(query))
													out.println("Товар додано.");
												else
													out.println("Виникла помилка під час додавання товару.");
												break;
											}
											// Додати персонал
											case 7: {
												String query = "insert into staff " +
														"(first_name, last_name, phone, mail, position_id)" +
														"values (" +
														"'Ім`я " + r.nextInt(1000) +
														"', 'Прізвище " + r.nextInt(1000) +
														"', '+38050" + (1000000 + r.nextInt(9000000)) +
														"', '" + (1 + r.nextInt(100)) + "@mail.com" +
														"', " + (1 + r.nextInt(3)) + ")";
												
												if (db.QueryExecute(query))
													out.println("Персонал додано.");
												else
													out.println("Виникла помилка під час додавання персоналу.");
												break;
											}
											// Додати розклад
											case 8: {
												String query = "insert into staff_schedule " +
														"(staff_id, work_date)" +
														"values (" +
														(1 + r.nextInt(20)) + ", '" +
														(2023 + r.nextInt(2)) + "-" + (1 + r.nextInt(12)) + "-" + (1 + r.nextInt(28)) + "')";
												
												if (db.QueryExecute(query))
													out.println("Розклад додано.");
												else
													out.println("Виникла помилка під час додавання розкладу.");
												break;
											}
											// Невірний вибір
											default: {
												out.println("Невірний вибір. Спробуйте ще раз.");
												break;
											}
										}
									}
									break;
								}
								// Невірний вибір
								default: {
									out.println("Невірний вибір. Спробуйте ще раз.");
									break;
								}
							}
						} catch (Exception e) {
							err.println("Некоректний ввід даних. Спробуйте ще раз.\n" + e.getMessage());
							s.nextLine();
						}
						break;
					}
					//Видалити об'єкт
					case "2": {
						out.println("Що саме ви хочете видалити?");
						out.println("1. Клієнт");
						out.println("2. Товар-замовлення");
						out.println("3. Замовлення");
						out.println("4. Позиція");
						out.println("5. Тип товару");
						out.println("6. Товар");
						out.println("7. Персонал");
						out.println("8. Розклад");
						out.print("Виберіть опцію: ");
						
						try {
							String type = s.nextLine();
							
							switch (type) {
								// Видалити клієнта
								case "1": {
									out.print("Введіть ID клієнта: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from clients where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Клієнта видалено.");
									else
										out.println("Клієнта не знайдено.");
									break;
								}
								// Видалити товар-замовлення
								case "2": {
									out.print("Введіть ID товару-замовлення: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from order_products where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Товар-замовлення видалено.");
									else
										out.println("Товар-замовлення не знайдено.");
									break;
								}
								// Видалити замовлення
								case "3": {
									out.print("Введіть ID замовлення: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from orders where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Замовлення видалено.");
									else
										out.println("Замовлення не знайдено.");
									break;
								}
								// Видалити позицію
								case "4": {
									out.print("Введіть ID позиції: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from positions where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Позицію видалено.");
									else
										out.println("Позицію не знайдено.");
									break;
								}
								// Видалити тип товару
								case "5": {
									out.print("Введіть ID типу товару: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from product_types where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Тип товару видалено.");
									else
										out.println("Тип товару не знайдено.");
									break;
								}
								// Видалити товар
								case "6": {
									out.print("Введіть ID товару: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from products where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Товар видалено.");
									else
										out.println("Товар не знайдено.");
									break;
								}
								// Видалити персонал
								case "7": {
									out.print("Введіть ID персоналу: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from staff where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Персонал видалено.");
									else
										out.println("Персонал не знайдено.");
									break;
								}
								// Видалити розклад
								case "8": {
									out.print("Введіть ID розкладу: ");
									int id = s.nextInt();
									s.nextLine();
									
									String query = "delete from staff_schedule where id = " + id;
									
									if (db.QueryExecute(query))
										out.println("Розклад видалено.");
									else
										out.println("Розклад не знайдено.");
									break;
								}
								// Невірний вибір
								default: {
									out.println("Невірний вибір. Спробуйте ще раз.");
									break;
								}
							}
						} catch (Exception e) {
							err.println("Некоректний ввід даних. Спробуйте ще раз.\n" + e.getMessage());
							s.nextLine();
						}
						break;
					}
					//Коригувати об'єкт (WIP)
					case "3": {
					/*out.print("Введіть назву публікації для коригування: ");
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
					break;*/
					}
					//Показати усі об'єкти
					case "4": {
						out.println("\nЩо саме бажаєте вивести?");
						out.println("1. Клієнти");
						out.println("2. Товари-замовлення");
						out.println("3. Замовлення");
						out.println("4. Позиції");
						out.println("5. Типи товарів");
						out.println("6. Товари");
						out.println("7. Персонал");
						out.println("8. Розклади");
						out.print("Виберіть опцію: ");
						
						try {
							String type = s.nextLine();
							
							switch (type) {
								// Показати усіх клієнтів
								case "1": {
									String query = "select * from clients";
									db.QueryExecute(query);
									break;
								}
								// Показати усі товари-замовлення
								case "2": {
									String query = "select * from order_products";
									db.QueryExecute(query);
									break;
								}
								// Показати усі замовлення
								case "3": {
									String query = "select * from orders";
									db.QueryExecute(query);
									break;
								}
								// Показати усі позиції
								case "4": {
									String query = "select * from positions";
									db.QueryExecute(query);
									break;
								}
								// Показати усі типи товарів
								case "5": {
									String query = "select * from product_types";
									db.QueryExecute(query);
									break;
								}
								// Показати усі товари
								case "6": {
									String query = "select * from products";
									db.QueryExecute(query);
									break;
								}
								// Показати усіх працівників
								case "7": {
									String query = "select * from staff";
									db.QueryExecute(query);
									break;
								}
								// Показати усі розклади
								case "8": {
									String query = "select * from staff_schedule";
									db.QueryExecute(query);
									break;
								}
								// Невірний вибір
								default: {
									out.println("Невірний вибір. Спробуйте ще раз.");
									break;
								}
							}
						} catch (Exception e) {
							err.println("Некоректний ввід даних. Спробуйте ще раз.\n" + e.getMessage());
							s.nextLine();
						}
						break;
					}
					//Показати усі напої
					case "5": {
						String query = "select * from products where type_id = (select id from product_types where name = 'Напій')";
						db.QueryExecute(query);
						break;
					}
					//Показати 3 найулюбленіші напої за попередній місяць
					case "6": {
						String query = "select p.id, p.name_ua, p.name_en, p.price, p.type_id, sum(oi.quantity) as total_quantity " +
								"from products p " +
								"join order_products oi on p.id = oi.product_id " +
								"join orders o on oi.order_id = o.id " +
								"where p.type_id = (select id from product_types where name = 'Напій') " +
								"and o.order_date >= current_date - interval '1 month' " +
								"group by p.id " +
								"order by total_quantity desc " +
								"limit 3;";
						db.QueryExecute(query);
						break;
					}
					//Показати усі десерти
					case "7": {
						String query = "select * from products where type_id = (select id from product_types where name = 'Десерт')";
						db.QueryExecute(query);
						break;
					}
					//Показати 5 найулюбленіших десертів за попередні 10 днів
					case "8": {
						String query = "select p.id, p.name_ua, p.name_en, p.price, p.type_id, sum(oi.quantity) as total_quantity " +
								"from products p " +
								"join order_products oi on p.id = oi.product_id " +
								"join orders o on oi.order_id = o.id " +
								"where p.type_id = (select id from product_types where name = 'Десерт') " +
								"and o.order_date >= current_date - interval '10 days' " +
								"group by p.id " +
								"order by total_quantity desc " +
								"limit 5;";
						db.QueryExecute(query);
						break;
					}
					//Показати інформацію про всіх барист
					case "9": {
						String query = "select * from staff where position_id = (select id from positions where name = 'Бариста')";
						db.QueryExecute(query);
						break;
					}
					//Показати інформацію про всіх офіціантів
					case "10": {
						String query = "select * from staff where position_id = (select id from positions where name = 'Офіціант')";
						db.QueryExecute(query);
						break;
					}
					//Підрахувати середню суму замовлення на конкретний день
					case "11": {
						out.print("Введіть дату (YYYY-MM-DD): ");
						String date = s.nextLine();
						
						String query = "select avg(total_sum) as average_order_sum " +
								"from ( " +
								"    select o.id, sum(p.price * oi.quantity) as total_sum " +
								"    from orders o " +
								"    join order_products oi on o.id = oi.order_id " +
								"    join products p on oi.product_id = p.id " +
								"    where o.order_date::date = '" + date + "' " +
								"    group by o.id " +
								") as order_sums;";
						db.QueryExecute(query);
						break;
					}
					//Показати інформацію про найбільше або найбільші(якщо таких замовлень декілька) замовлення на конкретну дату
					case "12": {
						out.print("Введіть дату (YYYY-MM-DD): ");
						String date = s.nextLine();
						
						String query = "with order_sums as ( " +
								"    select o.id, sum(p.price * oi.quantity) as total_sum " +
								"    from orders o " +
								"    join order_products oi on o.id = oi.order_id " +
								"    join products p on oi.product_id = p.id " +
								"    where o.order_date::date = '" + date + "' " +
								"    group by o.id " +
								") " +
								"select * " +
								"from order_sums " +
								"where total_sum = (select max(total_sum) from order_sums);";
						db.QueryExecute(query);
						break;
					}
					//Показати інформацію про постійного клієнта або клієнтів(якщо таких декілька).
					// Постійним будемо вважати клієнта, який за останній тиждень не менше 3-4 раз обслуговувався у кав’ярні
					case "13": {
						String query = "select c.id, c.first_name, c.last_name, c.birth_date, c.phone, c.mail, c.discount, count(o.id) as visit_count " +
								"from clients c " +
								"join orders o on c.id = o.client_id " +
								"where o.order_date >= current_date - interval '7 days' " +
								"group by c.id " +
								"having count(o.id) >= 3;";
						db.QueryExecute(query);
						break;
					}
					//Показати розклад роботи для усіх працівників кав’ярні сьогодні/завтра/довільна дата
					case "14": {
						out.print("Введіть дату (YYYY-MM-DD) або 'сьогодні'/'завтра': ");
						String input = s.nextLine();
						String date;
						if (input.equals("сьогодні")) {
							date = "current_date";
						} else if (input.equals("завтра")) {
							date = "current_date + interval '1 day'";
						} else {
							date = "'" + input + "'";
						}
						
						String query = "select s.id, s.staff_id, st.first_name, st.last_name, s.work_date " +
								"from staff_schedule s " +
								"join staff st on s.staff_id = st.id " +
								"where s.work_date = " + date + ";";
						db.QueryExecute(query);
						break;
					}
					//Вихід
					case "0": {
						return;
					}
					//Невірний вибір
					default: {
						out.println("Невірний вибір. Спробуйте ще раз.");
						break;
					}
				}
			} catch (Exception e) {
				err.println("Некоректний ввід даних. Спробуйте ще раз.\n" + e.getMessage());
				s.nextLine();
			}
		}
	}
}