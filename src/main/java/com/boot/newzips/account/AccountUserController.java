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
@RestController
@RequestMapping("/newzips")
public class AccountUserController {
	
	private final AccountUserService accountUserService;

	@GetMapping("/join")
	public ModelAndView join(MemberDTO memberDTO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/join_user2");
		
		return mav;
		
	}
	
	@PostMapping("/join")
	public ModelAndView join_ok(MemberDTO memberDTO) throws Exception{
		
		System.out.println("회원가입 post");
		
		ModelAndView mav = new ModelAndView();
		
		accountUserService.createMember(memberDTO);
		
		mav.setViewName("redirect:/newzips");
		
		return mav;
		
	}
	
	
	@GetMapping("/login")
	public ModelAndView login(LoginForm loginForm) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/login_user");

		return mav;
		
	}
	
	@PostMapping("/login")
	public ModelAndView login_ok(LoginForm loginForm) throws Exception{
		
		System.out.println("id: " + loginForm.getUserId());
		System.out.println("pwd: " + loginForm.getUserPwd());
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/newzips");
		
		return mav;
		
	}
	
	
	
}
