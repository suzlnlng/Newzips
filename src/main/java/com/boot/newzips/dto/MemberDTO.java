package com.boot.newzips.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	@NotEmpty(message = "아이디는 필수 항목입니다.")
	private String userId;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String userPwd;
	
	private String userPwd2;
	
	@NotEmpty(message = "이름은 필수 항목입니다.")
	private String userName;
	
	@NotEmpty(message = "생년월일은 필수 항목입니다.")
	private String userBirth;
	
	@NotEmpty(message = "핸드폰 번호는 필수 항목입니다.")
	private String userPhone;
	
	@NotEmpty(message = "우편번호는 필수 항목입니다.")
	private String userZipCode;
	
	@NotEmpty(message = "주소는 필수 항목입니다.")
	private String userAddr;
	
	@NotEmpty(message = "상세 주소를 입력해주세요.")
	private String userDetailedAddr;
	
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	private String userEmail;
	
	
	private String userRole;
	
	
	private String emailReceive;
	
	
	private String marketing;
	
}

