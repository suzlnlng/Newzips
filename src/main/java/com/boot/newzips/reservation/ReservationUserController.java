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
	
	@Resource
	private ReservationUserService reservationUserService;
	
	
	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
	}

	
	
	
	@GetMapping("/reservation_user1.action")
	public ModelAndView reservation_user() throws Exception{
		
		//매물번호를 주소에서 받아오기
		//임의로 설정
		String itemId = "";
		
		//매물번호 기준으로 데이터 불러오기
		
		
		ModelAndView mav = new ModelAndView();
		
		//mav에 데이터 담기
		
		mav.setViewName("user/reservation_user1");
		
		return mav;
		
	}
	
	
	@PostMapping("/reservation_user1.action")
	public ModelAndView reservation_user_ok(VisitorReservDTO dto,HttpServletRequest request) throws Exception{
		
		System.out.println("post 방식!!");
		
		ModelAndView mav = new ModelAndView();
		
		
		
		mav.setViewName("redirect:/reservation_user_complete1.action");
		
		return mav;
	}
	
	@GetMapping("/reservation_user_complete1.action")
	public ModelAndView reservation_user_complete() throws Exception{
		
		//주소에서 itemId 받아오기
		//해당 id에 대한 데이터 불러오기
		
		//mav에 담기
		
		ModelAndView mav = new ModelAndView();
		
		//
		
		mav.setViewName("user/reservation_user_complete1");
		
		return mav;
	}
		
	

}
