package com.boot.newzips.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String errorMessage;
		
		System.out.println("===============errorhandler");
		System.out.println(request.getParameter("Pwd"));
		
		if (exception instanceof BadCredentialsException) {
			errorMessage = "아이디 또는 비밀번호 오류";
		}else if (exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "내부적 시스템 문제";
		}else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "계정 존재X!!";
		}else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			errorMessage = "인증 요청 거부";
		}else {
			errorMessage = "알 수 없는 이유";
		}
		
		setDefaultFailureUrl("/newzips/login?error=true&exception="+errorMessage);
				

	super.onAuthenticationFailure(request, response, exception);
		
	}

	

}