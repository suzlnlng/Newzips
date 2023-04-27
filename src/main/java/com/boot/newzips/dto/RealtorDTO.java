package com.boot.newzips.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RealtorDTO {
	
	@NotEmpty(message = "아이디는 필수 항목입니다.")
	private String realtorId;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String realtorPwd;
	
	private String realtorPwd2;
	
	@NotEmpty(message = "이름은 필수 항목입니다.")
	private String realtorName;
	
	@NotEmpty(message = "핸드폰 번호는 필수 항목입니다.")
	private String realtorPhone;
	
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	private String realtorEmail;
	
	@NotEmpty(message = "공인중개사 등록번호는 필수 항목입니다.")
	private String realtorCerti;
	
	@NotEmpty(message = "우편번호는 필수 항목입니다.")
	private String realtorZipCode;
	
	@NotEmpty(message = "주소는 필수 항목입니다.")
	private String RealtorAddr;
	
	@NotEmpty(message = "상세 주소를 입력해주세요.")
	private String RealtorDetailedAddr;
	
	private String userRole;

}
