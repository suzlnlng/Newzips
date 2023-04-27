package com.boot.newzips.account;

import com.boot.newzips.dto.MemberDTO;

import lombok.Getter;

@Getter
public class SessionUser {
	
	private String userId;
	private String userName;
	private String userEmail;
	
	public SessionUser(MemberDTO memberDTO) {
		
		this.userId = memberDTO.getUserId();
		this.userName = memberDTO.getUserName();
		this.userEmail = memberDTO.getUserEmail();
		
	}

}
