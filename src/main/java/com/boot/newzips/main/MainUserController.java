package com.boot.newzips.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainUserController {
	
	@GetMapping("/newzips")
	public ModelAndView main() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/home_user");

		return mav;
		
	}
	

	
	
	
	@GetMapping("/login_test")
	public String login_test() {
		
		return "user/404";
	}
	
	

}
