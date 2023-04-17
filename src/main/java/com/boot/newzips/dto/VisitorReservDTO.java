package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitorReservDTO {
	
	private String reservNo;
	private String userId;
	private String itemId;
	private String realtorId;
	private String visitDate;
	private String visitTime;
	private String confirm;

	private ListingDTO listing;
	private JunsaeListingDTO junsae;
	private WolseListingDTO wolse;
	private MemberDTO member;
	
	
}
