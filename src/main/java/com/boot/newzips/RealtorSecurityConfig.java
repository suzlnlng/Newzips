package com.boot.newzips;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.boot.newzips.account.BaseCustomOAuth2UserService;
import com.boot.newzips.account.UserSecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RealtorSecurityConfig {
	
	@Bean
	public PasswordEncoder realtorPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain realtorFilterChain(HttpSecurity http) throws Exception{

		http
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		.formLogin()
			.loginPage("/newzips/realtor/login")
			.usernameParameter("realtorId")
			.passwordParameter("realtorPwd")
			.defaultSuccessUrl("/newzips/realtor")
			.failureUrl("/newzips/realtor/login")
		.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/newzips/realtor/logout"))
			.logoutSuccessUrl("/newzips/realtor")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)


		;
		//.failureHandler(customFailureHandler)
		return http.build();
		
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