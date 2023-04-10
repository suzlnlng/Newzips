package com.boot.newzips.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewDTO {
	
	private String userId;
	private String itemId;
	private String created;
	private int total;
	private int transport;
	private int convenience;
	private int environment;
	private int maintenance;
	private String content;
	private int like;
	

}
