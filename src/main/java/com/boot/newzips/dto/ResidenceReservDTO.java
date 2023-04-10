package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidenceReservDTO {
	
	private String userId;
	private String itemId;
	private String date;
	private String time;
	private String available;

}
