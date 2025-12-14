package com.lab8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
	//private Long id;
	private String name;
	private String address;
}

//public record PlaceDTO(
//		//Long id,
//		String name,
//		String address
//) {
//}