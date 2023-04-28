package com.boot.newzips.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/newzips")
public class MyPageUserController {
	
	@GetMapping("/mypage")
	public ModelAndView myPage() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/mypage_user");
		
		return mav;
		
	}
	

}
