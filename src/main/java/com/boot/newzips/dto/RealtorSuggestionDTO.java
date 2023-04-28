package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealtorSuggestionDTO {
	
	String realtorId;
	String userId;
	String itemId;
	String created;

	public RealtorSuggestionDTO() {}
	
	public RealtorSuggestionDTO(String userId,String itemId) {
		this.userId = userId;
		this.itemId = itemId;
	}
}

