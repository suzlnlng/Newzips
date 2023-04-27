package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestionDTO {
	
	//사용자에게 받은 제안 조건 DTO
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
