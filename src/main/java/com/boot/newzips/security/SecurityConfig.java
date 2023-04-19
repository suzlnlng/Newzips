package com.boot.newzips.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.boot.newzips.account.BaseCustomOAuth2UserService;
import com.boot.newzips.account.UserSecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthenticationFailureHandler customFailureHandler;
	
	private final UserSecurityService userSecurityService;
	private final BaseCustomOAuth2UserService baseCustomOAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		.formLogin()
			.loginPage("/newzips/login")
			.usernameParameter("userId")
			.passwordParameter("userPwd")
			.defaultSuccessUrl("/newzips")
			.failureUrl("/newzips/join")
			.failureHandler(customFailureHandler)
		.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/newzips")
			.deleteCookies("JSESSIONID").
			invalidateHttpSession(true)
		.and()
		.oauth2Login()
			.loginPage("/newzips/login")
			.defaultSuccessUrl("/newzips")
			.userInfoEndpoint()
			.userService(baseCustomOAuth2UserService)
		;

		return http.build();
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception{

		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	

	
//	//사용자 조회를 UserSecurityService가 담당하도록 추가해줌
//	@Bean
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		
//		auth.userDetailsService(userSecurityService)
//		.and()
//		.authenticationProvider(authenticationProvider())
//		;
//		
//	}
	
	
/*	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userSecurityService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	*/

}