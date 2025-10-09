package com;

import com.Model.*;
import com.Service.CarBase;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
	public static CarBase carBase;
	public static Random random = new Random();
	public static Scanner s = new Scanner(in);
	
	public static void main(String[] args) {
		carBase = new CarBase();
		
		createRandomBase(carBase);
		
		// Створення заявок
		for (int i = 1; i <= 10; i++) {
			carBase.addRequest(new Request(
					"Місто" + random.nextInt(1000),
					random.nextInt(9000) + 1000,
					random.nextInt(9500) + 500,
					Difficulty.values()[random.nextInt(Difficulty.values().length)]));
		}
		
		out.println("Надійшли нові заявки, виберіть заявку для розпреділення");
		out.println("Наявні заявки:");
		out.println("№\tЛокація\tДистанція\tМаса\tСкладність");
		
		int i = 1;
		for (Request request : carBase.getRequests()) {
			out.println(i + ".\t" +
					request.getLocation() + "\t" +
					request.getDistance() + "\t" +
					request.getProductMass() + "\t" +
					request.getProductType());
			i++;
		}
		
		/*out.println("Виберіть заявку для виконання: ");
		int idx = s.nextInt();
		s.nextLine();
		
		if (idx < 1 || idx > carBase.getRequests().size()) {
			out.println("Некоректний номер заявки");
			return;
		}*/
		
		// Розподіл заявок
		i = 1;
		for (Request request : carBase.getRequests()) {
			Optional<Trip> tripOpt = carBase.assignRequest(request);
			if (tripOpt.isPresent()) {
				Trip trip = tripOpt.get();
				out.println("\nЗаявка " + i + " успішно розподілена:");
				out.println("Водій: " + trip.getDriver());
				out.println("Автомобіль: " + trip.getCar());
				out.println("Заявка: " + trip.getRequest());
				out.printf("Оплата: %.2f\n", trip.getPayment());
			} else {
				err.println("Не вдалося розподілити заявку " + i + ". Немає доступних водіїв або автомобілів.");
			}
			i++;
		}
		
		for (Request request : carBase.getRequests()) {
			Optional<Trip> tripOpt = carBase.assignRequest(request);
			if (tripOpt.isPresent()) {
				Trip trip = tripOpt.get();
				trip.getDriver().setAvailable(true);
				trip.getCar().setAvailable(true);
			}
		}
	}
	
	public static void createRandomBase(CarBase carBase) {
		// Створення водіїв
		for (int i = 1; i <= 101; i++) {
			carBase.addDriver(new Driver(
					"Водій " + i,
					random.nextInt(11),
					true));
		}
		
		// Створення автомобілів
		for (int i = 1; i <= 101; i++) {
			carBase.addCar(new Car(
					"Марка " + i,
					random.nextInt(9500) + 500,
					Difficulty.values()[random.nextInt(Difficulty.values().length)],
					true));
		}
	}
}