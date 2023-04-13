package com.boot.newzips.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.MemberDTO;

@RestController
@RequestMapping(value="/newzips")
public class AccountUserController {

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
		
		//mav.setViewName("user/join_user2");
		
		return mav;
		
	}
	
	
	@GetMapping("/login")
	public ModelAndView login(LoginForm loginForm) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/login_user");

		return mav;
		
	}
	
	@PostMapping("/newzips/login")
	public ModelAndView login_ok(LoginForm loginForm) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/newzips");
		
		return mav;
		
	}
	
	
	
}
