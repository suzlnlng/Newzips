package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestionDTO {
	
	private String userId;
	private String addrCity;
	private String addrGu;
	private int price;
	private String groundType;
	private int area; 
	private String parking;
	private String pet;
	private String transport;
	private int maintenanceCost;

}
