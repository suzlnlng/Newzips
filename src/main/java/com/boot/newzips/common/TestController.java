package com.boot.newzips.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/newzips/test")
	public String test() {
		
		return "test";
		
	}

}