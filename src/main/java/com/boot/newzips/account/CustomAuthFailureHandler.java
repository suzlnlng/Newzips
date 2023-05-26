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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;


public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
		System.out.println("===============errorhandler");
		//System.out.println(request.getParameter("userPwd"));
		
		
		String errorMessage;

		if (exception instanceof BadCredentialsException) {
			errorMessage = "badcredential";
		}else if (exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "internal-authentication-service";
		}else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "username-not-found";
		}else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			errorMessage = "authentication-credentials-not-found";
		}else {
			errorMessage = "else";
		}
		
		setDefaultFailureUrl("/newzips/login?error=true&exception="+errorMessage);
				
		super.onAuthenticationFailure(request, response, exception);
		
	}

	

}