package com;

import com.Model.Car;
import com.Model.Driver;
import com.Model.ProductType;
import com.Model.Request;
import com.Service.CarBase;

import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// Створення водіїв
		List<Driver> drivers = Arrays.asList(
				new Driver("Іван", 2, 0, true),
				new Driver("Петро", 5, 0, true),
				new Driver("Олена", 7, 0, true)
		);
		
		// Створення автомобілів
		List<Car> cars = Arrays.asList(
				new Car("Газель", 1000, 1, true),
				new Car("Камаз", 500, 3, true),
				new Car("MAN", 10000, 5, true)
		);
		
		// Ініціалізація бази
		CarBase carBase = new CarBase(drivers, cars);
		
		// Створення заявок
		List<Request> requests = Arrays.asList(
				new Request("Київ", 800, 200, ProductType.LIGHT),
				new Request("Львів", 4000, 600, ProductType.MEDIUM),
				new Request("Одеса", 9500, 500, ProductType.HEAVY),
				new Request("Харків", 1200, 400, ProductType.LIGHT)
		);
		
		// Обробка заявок
		for (Request request : requests) {
			carBase.addRequest(request).ifPresentOrElse(
					trip -> System.out.println("Успішно призначено рейс: " + trip + '\n'),
					() -> System.out.println("Не вдалося призначити рейс для заявки: " + request)
			);
		}
	}
}