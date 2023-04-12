package com.boot.newzips.reservation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.ResidenceReservDTO;
import com.boot.newzips.service.ResidentService;

@RestController
public class ReservationUserController {
	
	@Resource
	private ResidentService residentService;
	

	
	@RequestMapping(value = "/reservation_resident1.action",method = RequestMethod.GET)
	@GetMapping("/reservation_resident1.action")
	public ModelAndView reservation_resident() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/reservation_resident1");
		
		return mav;
				
	}
	
	@RequestMapping(value = "/reservation_user1.action")
	@GetMapping("/reservation_user1.action")
	public ModelAndView reservation_user() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/reservation_user1");
		
		return mav;
	}
	
	
	
	
	
	
	
	

}
