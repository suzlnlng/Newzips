package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomInfoDTO {
	
	private String itemId;
	private String roomType;
	private String roomFloor;
	private String area;
	private String supplyArea;
	private int rooms;
	private int bathrooms;
	private String roomDirection;
	private String heating;
	private String balcony;
	private String availableDate;
	private String options;	

}
