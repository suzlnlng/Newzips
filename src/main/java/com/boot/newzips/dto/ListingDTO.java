package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingDTO {
	
	private String itemId;
	private String addrCity;
	private String addrGu;
	private String addrDetail;
	private String yearly_monthly;
	private int maintenanceCost;
	private String maintenanceList;
	private String ectList;
	private String parking;
	private int parkingCost;
	private String contract;

}
