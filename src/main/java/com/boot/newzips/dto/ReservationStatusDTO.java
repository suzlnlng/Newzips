package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationStatusDTO {
	
	private String reservNo;
	private String itemId;
	private String userId;
	private String realtorId;
	private String visitDate;
	private String visitTime;
	private String confirm;
	private String area;
	private int rooms;
	private int bathrooms;
	private String realtorName;
	private String realtorPhone;
	private String addrDetail;

}
