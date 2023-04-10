package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingInfoDTO {
	
	private String itemId;
	private String buildingName;
	private String totalFloors;
	private int totalHouseholds;
	private int totalParking;
	private String buildingUse;
	private String secureFacility;
	private String approvalDate;

}
