package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractUserDTO {
	
	private String userId;
	private String itemId;
	private String addrDetail;
    private int deposit;
    private int deposit_web;
    private int parking;
    private int parkingCost;
    private String realtorName;
    private String realtorPhone;
    private String realtorEmail;
    private int rooms;
    private int bathrooms;
    private String yearly_monthly;
    private String yearlyFee;
    private String yearlyFee_web;
    private String monthlyFee;
    private String monthlyFee_web;
    


}
