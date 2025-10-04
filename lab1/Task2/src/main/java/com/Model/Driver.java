package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Водій на рейс визначається виходячи зі
// стажу, необхідного для перевезення зазначеного у заявці товару,
// складності керування Автомобілем
// Довжини шляху.

// Водій
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
	// Ім'я водія
	private String name;
	// Стаж водія
	private int experience;
	// Зарплатня
	private double money;
	// Доступний
	private boolean available = true;
}
