package com.boot.newzips.account;

import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseCustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

	private final AccountUserMapper accountUserMapper;
	
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		// 대리자 생성
		OAuth2UserService<OAuth2UserRequest, OAuth2User> oauthService = new DefaultOAuth2UserService();
		
		OAuth2User oauth2User = oauthService.loadUser(userRequest);
		
		//간편 로그인을 진행하는 플랫폼 코드(google, kakao, naver,...)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		System.out.println(registrationId);
		
		//OAuth2 로그인 진행시 key가 되는 필드값(Primary key역할)
		//구글: sub, 네이버: response, 카카오: id
		String userNameAttributeName = 
				userRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();
		
		System.out.println(userNameAttributeName);
		
		OAuthAttributes attributes =
				OAuthAttributes.of(registrationId, userNameAttributeName, oauth2User.getAttributes());
		
		System.out.println(attributes.getAttributes());
		
		
		MemberDTO authUser = attributes.toEntity();
		
		saveOr(authUser);
		
		httpSession.setAttribute("user", new SessionUser(authUser));
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(authUser.getUserRole())), 
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}
	
	private void saveOr(MemberDTO memberDTO) {
		
		Optional<MemberDTO> _user = null;
		
		try {
			_user = accountUserMapper.getUserByEmail(memberDTO.getUserEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!_user.isPresent()) {
			
			try {
				accountUserMapper.createOauthMember(memberDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
