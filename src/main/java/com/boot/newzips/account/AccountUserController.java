package com.boot.newzips.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/newzips", method= {RequestMethod.GET, RequestMethod.POST} )
public class AccountUserController {

	@PostMapping("/join")
	public ModelAndView join() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/join_user");
		
		return mav;
		
	}
	
	
	@GetMapping("/login")
	public ModelAndView login() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/login_user");

		return mav;
		
	}
	
	@PostMapping("/newzips/login_ok")
	public ModelAndView login_ok() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/newzips");
		
		return mav;
		
	}
	
	
	
}
