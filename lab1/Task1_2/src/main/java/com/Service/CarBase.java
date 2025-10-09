package com.Service;

import com.Model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Data
@AllArgsConstructor
public class CarBase {
	private List<Driver> drivers;
	private List<Car> cars;
	private List<Request> requests;
	
	public CarBase() {
		drivers = new ArrayList<>();
		cars = new ArrayList<>();
		requests = new ArrayList<>();
	}
	
	public boolean addCar(Car car) {
		if (cars.contains(car))
			return false;
		
		return cars.add(car);
	}
	
	public boolean addDriver(Driver driver) {
		if (drivers.contains(driver))
			return false;
		
		return drivers.add(driver);
	}
	
	public boolean addRequest(Request request) {
		if (requests.contains(request))
			return false;
		
		return requests.add(request);
	}
	
	public Optional<Trip> assignRequest(Request request) {
		Random random = new Random();
		
		// Пошук доступної машини з достатньою вантажопідйомністю
		Optional<Car> carOpt = cars.stream()
				.filter(car -> car.isAvailable() && car.getCapacity() >= request.getProductMass())
				.findFirst();
		
		if (!carOpt.isPresent()) {
			return Optional.empty();
		}
		
		Car car = carOpt.get();
		
		// Визначення складності заявки
		int difficulty;
		switch (request.getProductType()) {
			case EASY:
				difficulty = 1;
				break;
			case MEDIUM:
				difficulty = 3;
				break;
			case HARD:
				difficulty = 5;
				break;
			default:
				difficulty = 7;
				break;
		}
		
		// Пошук доступного водія з достатнім досвідом
		Optional<Driver> driverOpt = drivers.stream()
				.filter(driver -> driver.isAvailable() && driver.getExperience() >= difficulty)
				.findFirst();
		
		if (!driverOpt.isPresent()) {
			return Optional.empty();
		}
		
		Driver driver = driverOpt.get();
		
		// Позначаємо машину та водія як зайнятих
		car.setAvailable(false);
		driver.setAvailable(false);
		
		// Створюємо поїздку
		Trip trip = new Trip(driver, car, request,
				request.getDistance() * (1 + car.getDifficulty().values().length * 0.1) * (1 + request.getProductType().values().length * 0.1),
				request.getDistance() / 1000);
		
		// Додаємо поїздку до списку (якщо є)
		// trips.add(trip);
		
		return Optional.of(trip);
	}

	
	/*public Optional<Trip> addRequest(Request request) {
		// Пошук опціонального автомобіля
		Optional<Car> carOpt = cars.stream()
				.filter(car -> car.isAvailable() && car.getCapacity() >= request.getProductMass())
				.sorted((car1, car2) -> Double.compare(car1.getCapacity(), car2.getCapacity()))
				.findFirst();
		
		if (carOpt.isEmpty()) {
			return Optional.empty();
		}
		
		Car car = carOpt.get();
		
		// Визначення складності перевезення товару
		int difficulty;
		switch (request.getProductType()) {
			case HARD: {
				difficulty = 5;
				break;
			}
			case MEDIUM: {
				difficulty = 3;
				break;
			}
			default: {
				difficulty = 1;
				break;
			}
		}
		
		//Визначення опціонального водія
		int requiredExp = Math.max(difficulty, Math.max(car.getDifficulty() * 2, (int) (request.getDistance() / 1000)));
		Optional<Driver> driverOpt = drivers.stream()
				.filter(driver -> driver.isAvailable() && driver.getExperience() >= requiredExp)
				.sorted((driver1, driver2) -> Integer.compare(driver1.getExperience(), driver2.getExperience()))
				.findFirst();
		
		if (driverOpt.isEmpty()) {
			return Optional.empty();
		}
		
		Driver driver = driverOpt.get();
		
		// Початок подорожі
		car.setAvailable(false);
		driver.setAvailable(false);
		
		// Подорож
		Trip trip = new Trip(driver, car, request, false, false, 0);
		
		// Подія ламання автомобіля
		boolean isBroken = random.nextDouble() < 0.1;
		trip.setCarBroken(isBroken);
		trip.setCompleted(!isBroken);
		
		// Визначення зарплатні
		double baseRate = 0.5 * request.getDistance();
		switch (request.getProductType()) {
			case HEAVY: {
				baseRate *= 1.5;
				break;
			}
			case MEDIUM: {
				baseRate *= 1.3;
				break;
			}
		}
		
		// Результат подорожі
		if (trip.isCompleted()) {
			trip.setPayment(baseRate);
			trips.add(trip);
			driver.setAvailable(true);
			car.setAvailable(true);
			driver.setMoney(driver.getMoney() + trip.getPayment());
		}
		
		// Логування
		String log = String.format("[%s] Driver: %s, Vehicle: %s, Destination: %s, Status: %s%n",
				LocalDateTime.now(),
				trip.getDriver().getName(),
				trip.getCar().getModel(),
				trip.getRequest().getLocation(),
				trip.isCompleted() ? "Завершено" : "Не вдалося");
		
		return Optional.of(trip);
	}*/
}
