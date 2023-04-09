package com.boot.newzips.contract;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractUserController {
	
	@GetMapping("newzips/contract_user")
	public String contractUser() {
		
		return "/user/contract_user";
	}

}
