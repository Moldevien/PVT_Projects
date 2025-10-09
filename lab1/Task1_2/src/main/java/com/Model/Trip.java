package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Поїздка
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
	// Водій
	private Driver driver;
	// Автомобіль
	private Car car;
	// Заявка
	private Request request;
	// Оплата
	private double payment;
	// Час поїздки
	private int tripTime;
}
