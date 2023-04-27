package com.boot.newzips.account;

import java.util.Map;

import com.boot.newzips.dto.MemberDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	
	private String nameAttributeKey;
	private String userId;
	private String userName;
	private String userEmail;

	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, 
			String nameAttributeKey, String userName, String userEmail) {
		
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.userId = userEmail;
		this.userName = userName;
		this.userEmail = userEmail;
		
	}
	

	//of라는 메소드 이름은 정해진것! 사용자가 정의할 수 없음!
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		
		//이곳에서 구글,카카오,네이버 등을 구분(ofGoogle, ofNaver, ofKakao)
		//userNameAttributeName : sub
		//attributes <- 나머지
		//**넘어오는 데이터**
//		{
//			sub=107123782853678978866,
//			name=배수지, 
//			given_name=수지, 
//			family_name=배, 
//			picture=https://lh3.googleusercontent.com/a-/AFdZucppLJTanskdjfbksjdhfoisdfXSA=s96-c, 
//			email=digulx2@gmail.com, 
//			email_verified=true, 
//			locale=ko
//			}
		
		if(registrationId.equals("kakao")) {//registrationId : id
			return ofKakao(userNameAttributeName, attributes);
		}
		
		if(registrationId.equals("naver")) {//registrationId : response
			return ofNaver("id", attributes);
		}
		
		//registrationId : sub 
		return ofGoogle(userNameAttributeName, attributes);
			
	}
	
	
	//OAuth2User에서 반환하는 사용자 정보는 MAP형태이기 때문에 
	//ofGoogle 메소드에서 변환작업 함
	private static OAuthAttributes ofGoogle(String userNameAttributeName,
			Map<String, Object> attributes) {
		
		return OAuthAttributes.builder()
				.userName((String)attributes.get("name"))
				.userEmail((String)attributes.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
			
	}
	
	private static OAuthAttributes ofKakao(String userNameAttributeName,
			Map<String, Object> attributes) {
		
		//kakao_account내에 email이 있고 profile이 있음
		
		//kakao_account에 사용자 정보(email)가 있음
		Map<String, Object> kakaoAccount =
				(Map<String, Object>)attributes.get("kakao_account");
		
		//kakao_account안에 profile이라는 json객체가 있음
		Map<String, Object> kakaoProfile =
				(Map<String, Object>)kakaoAccount.get("profile");
		
		return OAuthAttributes.builder()
				.userName((String)kakaoProfile.get("nickname"))
				.userEmail((String)kakaoAccount.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();

	}
	
	private static OAuthAttributes ofNaver(String userNameAttributeName,
			Map<String, Object> attributes) {
		
		//kakao_account내에 email이 있고 profile이 있음
		
		//kakao_account에 사용자 정보(email)가 있음
		Map<String, Object> response =
				(Map<String, Object>)attributes.get("response");

		
		return OAuthAttributes.builder()
				.userName((String)response.get("name"))
				.userEmail((String)response.get("email"))
				.attributes(response)
				.nameAttributeKey(userNameAttributeName)
				.build();

	}
	
	public MemberDTO toEntity() {
		
		return MemberDTO.builder()
				.userId(userId)
				.userName(userName)
				.userEmail(userEmail)
				.userRole(UserRole.USER.getRole())
				.build();
		
	}
	
}
