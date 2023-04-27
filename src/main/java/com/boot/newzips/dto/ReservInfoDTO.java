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
	private String realtorId;
	private String visitDate;
	private String visitTime;
	private String confirm;
	
	//listingTab
	private String addrDetail;
	private String yearly_monthly;
	
	//junsaeTab
	private String yearlyFee;
	
	//wolseTab
	private String deposit;
	private String monthlyFee;	
	
	//memberTab
	private String userName;
	private String userBirth;
	private String userPhone;
	
}
