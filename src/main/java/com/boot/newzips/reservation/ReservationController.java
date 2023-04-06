package com.boot.newzips.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

	@GetMapping("/newzips/reservationStateRealtor")
	public String reservationStateRealtor(){
		
		return "reservationStateRealtor";
	}
	
	@GetMapping("/newzips/reservationListRealtor")
	public String reservationListRealtor(){
		
		return "reservationListRealtor";
	}
	
}
