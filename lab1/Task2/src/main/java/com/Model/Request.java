package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Заявка на рейс повинна містити
//- пункт призначення,
//- кількість вантажу
//- тип вантажу.

// Запрос
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
	// Пункт призначення
	private String location;
	// Відстань
	private int distance;
	// Вага вантажу
	private int productMass;
	// Тип вантажу
	private Difficulty productType;
}
