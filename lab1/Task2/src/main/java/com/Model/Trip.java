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
	// Чи завершена поїздка
	private boolean completed;
	// Чи зламалась машина
	private boolean carBroken;
	// Оплата
	private double payment;
}
