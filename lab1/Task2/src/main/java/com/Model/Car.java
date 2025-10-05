package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Автомобіль на рейс визначається
// з принципу оптимізації ваги, що перевозиться,
// вантажопідйомності автомобіля.

// Автомобіль
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	// Модель
	private String model;
	// Вантажопідйомність
	private int capacity;
	// Складність управління
	private Difficulty difficulty;
	// Доступний
	private boolean available = true;
}
