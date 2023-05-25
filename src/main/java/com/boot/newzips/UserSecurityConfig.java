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
import com.boot.newzips.account.UserSecurityService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity(debug=true)
@Configuration
public class UserSecurityConfig {
	
	private final AuthenticationFailureHandler customFailureHandler;

	private final BaseCustomOAuth2UserService baseCustomOAuth2UserService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserSecurityService();
	}
	
	@Bean
	public PasswordEncoder userPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public DaoAuthenticationProvider userAuthenticationProvier() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(userPasswordEncoder());
		
		return provider;
		
	}
	
	@Bean
	public AuthenticationManager userAuthenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception{

		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	@Bean
	public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests(authorize -> authorize.anyRequest().permitAll())
	        .formLogin(form -> form
	            .loginPage("/newzips/login")
	            .usernameParameter("userId")
	            .passwordParameter("userPwd")
	            .failureUrl("/newzips/login?error=true")
	            .failureHandler(customFailureHandler)
	            .defaultSuccessUrl("/newzips")
	            .permitAll())
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/newzips/logout"))
				.logoutSuccessUrl("/newzips")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
			.and()
	        .oauth2Login()
	            .loginPage("/newzips/login")
	            .defaultSuccessUrl("/newzips")
	            .userInfoEndpoint()
	            .userService(baseCustomOAuth2UserService);
	    
	    return http.build();
	}
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType("application/json;chaset=UTF-8");
		return jsonView;
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
 * 
 * 	
	
 * 
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userSecurityService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	*/

}