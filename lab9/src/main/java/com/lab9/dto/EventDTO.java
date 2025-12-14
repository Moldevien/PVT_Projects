package com.lab9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
	//private Long id;
	private String name;
	private LocalDate eventDate;
	private PlaceDTO place;
	private List<TicketDTO> tickets;
}

//public record EventDTO(
//		//Long id,
//		String name,
//		LocalDate eventDate,
//		//PlaceDTO place,
//		List<TicketDTO> tickets
//) {
//}