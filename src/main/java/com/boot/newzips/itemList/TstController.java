package com.boot.newzips.itemList;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TstController {
	
	@GetMapping("/user/ItemList_user")
	public String test() {
	    return "user/ItemList_user";
	}

	
	
	
}