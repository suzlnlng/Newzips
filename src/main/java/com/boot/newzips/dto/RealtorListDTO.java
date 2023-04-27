package com.boot.newzips.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealtorListDTO {

	//중개인 매물 리스트 DTO
	String itemId;
	String addrDetail;
	String yearly_monthly;
	String deposit;
	String monthlyFee;
	int maintenanceCost;
	String area;
	String roomType;
	int rooms;
	
	String userId;
}
