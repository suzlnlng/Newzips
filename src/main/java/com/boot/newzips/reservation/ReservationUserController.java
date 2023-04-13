package com.boot.newzips.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationUserController {
	
	
	@GetMapping("newzips/reservation_user1")
	public String user1() {
		
		return "/user/reservation_user1";
	}

	@GetMapping("newzips/reservation_resident1")
	public String resident1() {
		
		return "/user/reservation_resident1";
	}
	
	@GetMapping("newzips/reservation_user_complete1")
	public String usercomplete1() {
		
		return "/user/reservation_user_complete1";
	}
	
	

}
