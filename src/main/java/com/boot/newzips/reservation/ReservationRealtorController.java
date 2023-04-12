package com.boot.newzips.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationRealtorController {

	@GetMapping("/newzips/reservationRequestRealtor")
	public String reservationRequestRealtor() {
		
		return "realtor/reservationRequestRealtor";
	}

	@GetMapping("/newzips/reservationStateRealtor")
	public String reservationStateRealtor() {
		
		return "realtor/reservationStateRealtor";
	}

}

