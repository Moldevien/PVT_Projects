package com.lab8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
	//private Long id;
	private double cost;
	private int count;
	//private EventDTO event; // додано поле для зв'язку з подією
}

//public record TicketDTO(
//		//Long id,
//		double cost,
//		int count
//		//int seatNumber
//		//EventDTO event
//) {
//}