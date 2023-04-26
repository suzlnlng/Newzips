package com.boot.newzips.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidenceReservDTO {
	
	private String userId;
	private String itemId;
	private String availableDate;
	private String availableTime;
	private String available;

}
