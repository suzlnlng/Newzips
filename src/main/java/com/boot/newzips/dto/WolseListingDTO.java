package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WolseListingDTO {
	
	private String itemId;
	private int deposit;
	private String deposit_web;
	private int monthlyFee;	
	private String monthlyFee_web;
	private int contractPeriod;

}
