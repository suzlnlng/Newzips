package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservInfoDTO {
	//visitorReservTab
	private String reservNo;
	private String userId;
	private String itemId;
	private String visitDate;
	private String visitTime;
	private String confirm;
	
	//listingTab
	private String addrDetail;
	private String yearly_monthly;
	
	//junsaeTab
	private int yearlyFee;
	
	//wolseTab
	private int deposit;
	private int monthlyFee;	
	
	//memberTab
	private String userName;
	private String userBirth;
	private String userPhone;
	
}
