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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.boot.newzips.account.BaseCustomOAuth2UserService;
import com.boot.newzips.account.CustomAuthFailureHandler;
import com.boot.newzips.account.RealtorSecurityService;
import com.boot.newzips.account.UserSecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Order(1)
public class RealtorSecurityConfig {
	
	@Bean
	public UserDetailsService realtorDetailsService() {
		return new RealtorSecurityService();
	}
	
	@Bean
	public PasswordEncoder realtorPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider realtorAuthenticationProvier() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(realtorDetailsService());
		provider.setPasswordEncoder(realtorPasswordEncoder());
		
		return provider;
		
	}
	
	
	@Bean
	public SecurityFilterChain realtorFilterChain(HttpSecurity http) throws Exception{

		http
		.authenticationProvider(realtorAuthenticationProvier())
		.antMatcher("/newzips/realtor/**")
		.authorizeHttpRequests(authorize -> authorize
				.anyRequest().permitAll())
		.formLogin(form -> form
			.loginPage("/newzips/realtor/login")
			.usernameParameter("realtorId")
			.passwordParameter("realtorPwd")
			.defaultSuccessUrl("/newzips/realtor")
            .failureHandler(new CustomAuthFailureHandler())
			)
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/newzips/realtor/logout"))
			.logoutSuccessUrl("/newzips/realtor")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)


		;
		//.failureHandler(customFailureHandler)
		return http.build();
		
	}

	


}