package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WolseListingDTO {
	
	private String itemId;
	private int deposit;
	private int deposit_web;
	private int monthlyFee;	
	private int monthlyFee_web;
	private int contractPeriod;

}
