package com.boot.newzips.contract;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractUserController {
	
	@GetMapping("newzips/contract_user")
	public String contractUser() {
		
		return "/user/contract_user";
	}
	
	//아 존나 모르겠다
	
	@GetMapping("newzips/reservation_user_contract1")
	public String usercontract1() {
		
		return "/user/reservation_user_contract1";
	}

}
