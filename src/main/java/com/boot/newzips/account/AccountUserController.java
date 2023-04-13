package com.boot.newzips.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/newzips")
@RestController
public class AccountUserController {
	
	//private final AccountUserService accountUserService;

	@GetMapping("/join")
	public ModelAndView join(MemberDTO memberDTO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/join_user2");
		
		return mav;
		
	}
	
	@PostMapping("/join")
	public ModelAndView join_ok(MemberDTO memberDTO) throws Exception{
		
		System.out.println("join_ok");
		
		ModelAndView mav = new ModelAndView();
		
		
		return mav;
		
	}
	
	@GetMapping("/login")
	public ModelAndView login(LoginForm loginForm) throws Exception{
		
		System.out.println("login");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/login_user");

		return mav;
		
	}
	
	@PostMapping("/login")
	public ModelAndView login_ok(LoginForm loginForm) throws Exception{
		
		System.out.println("login_ok");
		
		ModelAndView mav = new ModelAndView();
				
		System.out.println(loginForm.getUserId());
		System.out.println(loginForm.getUserPwd());
		
		mav.setViewName("redirect:/newzips");
		
		return mav;
		
	}
	
	
	
}
