package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Про газети відомо таке:
//− номер газети;
//− назва газети;
//− дата виходу поточного номера газети;
//− перелік колонок газети та відповідних журналістів за наповнення колонки.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Newspaper implements Publication {
	// Номер газети
	private int number;
	// Назва газети
	private String title;
	// Дата виходу поточного номера газети
	private String date;
	// Перелік колонок газети та відповідних журналістів за наповнення колонки
	private String columns;
}
