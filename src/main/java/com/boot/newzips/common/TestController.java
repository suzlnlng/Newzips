package com.boot.newzips.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@GetMapping("/newzips/test")
	public String test() {
		
		return "01_Home";
		
	}
	
	
	
	@GetMapping("/newzips/itemDetail")
	public String itemDetail() {
		
		return "home1";
		
	}
	
	@GetMapping("/newzips/01_Home")
	public String Home() {
		
		return "01_Home";
		
	}

}