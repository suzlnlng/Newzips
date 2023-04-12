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
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.service.ReservationUserService;
import com.boot.newzips.service.ResidentService;

@RestController
public class ReservationUserController {
	
	@Resource //의존성 주입을 함으로써 Service 안에 있는 ResidentServiceImpl도 딸려옴
	private ResidentService residentService; 
	private ReservationUserService reservationUserService;
	
	

	@GetMapping("/")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
		
	}
	
	
	@GetMapping("/reservation_resident1.action")
	public ModelAndView reservation_resident() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/reservation_resident1"); //jsp(html)로 갈때는 setViewName / class는 setView
		
		return mav;
				
	}
	
	
	
	
	@GetMapping("/reservation_user1.action")
	public ModelAndView reservation_user() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/reservation_user1");
		
		return mav;
	}
	
	@PostMapping("/reservation_user1.action")
	public ModelAndView reservation_user_ok(VisitorReservDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/reservation_user_complete1.action");
		
		return mav;
	}
	
	
	
	
	
	
	
	

}
