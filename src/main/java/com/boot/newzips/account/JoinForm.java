package com.boot.newzips.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {
	
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String userPwd;
	
	@NotEmpty
	private String userPwd2;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String userBirth;
	
	@NotEmpty
	private String userPhone;
	
	@NotEmpty
	private String userZipCode;
	
	@NotEmpty
	private String userAddr;
	
	@NotEmpty
	private String userDetailedAddr;
	
	@NotEmpty
	@Email
	private String userEmail;
	
	
	private String emailReceive;
	private String marketing;

}
