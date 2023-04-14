package com.boot.newzips.reservation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.RoomInfoDTO;
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

	
	
	
	@GetMapping("/newzips/reservation_user1.action")
	public ModelAndView reservation_user(HttpServletRequest request) throws Exception{
		
		//매물번호를 주소에서 받아오기
		//임의로 설정
		String itemId = request.getParameter("itemId");
		itemId = "32906223";
		
		
		//매물번호 기준으로 데이터 불러오기
		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
		
		ModelAndView mav = new ModelAndView();
		
		//mav에 데이터 담기
		mav.addObject("dtoV",dtoV);
		mav.addObject("dtoR",dtoR);
		
		mav.setViewName("user/reservation_user1");
		
		return mav;
		
	}
	
	
	@PostMapping("/newzips/reservation_user1.action")
	public ModelAndView reservation_user_ok(VisitorReservDTO dto,HttpServletRequest request) throws Exception{
		
		System.out.println("post 방식!!");
		//reservation_user메소드랑 동일하게 작성하는데,
		//reservation_user1페이지에서 넘어온 날짜와 시간을 포함한
		//예약 정보를 데이터베이스에 넣는과정을 추가해서 작성!!
		
		ModelAndView mav = new ModelAndView();
		
				
		mav.setViewName("redirect:/reservation_user_complete1.action");
		
		return mav;
	}
	
	@GetMapping("/newzips/reservation_user_complete1.action")
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
