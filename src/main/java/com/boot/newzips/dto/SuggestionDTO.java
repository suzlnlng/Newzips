package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestionDTO {
	
	private String userId;
	private String userPhone;
	private String addrCity;
	private String addrGu;
	private String yearly_monthly;
	private int deposit;
	private int monthlyFee;
	private int rooms;
	private int area; 
	private int maintenanceCost;

}
