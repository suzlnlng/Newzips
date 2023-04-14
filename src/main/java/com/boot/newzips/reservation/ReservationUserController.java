package com.boot.newzips.reservation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;

import com.boot.newzips.service.ReservationUserService;
import com.boot.newzips.service.ResidentService;

@RestController
public class ReservationUserController {
	
	@Resource 
	private ResidentService residentService; 
	
	@Resource
	private ReservationUserService reservationUserService;
	
	
	
	@GetMapping("/newzips/reservation_user1")
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
	
	
	@PostMapping("/newzips/reservation_user1")
	public ModelAndView reservation_user_ok(VisitorReservDTO dto,HttpServletRequest request,
			@RequestParam("visitDate") String visitDate, 
			@RequestParam("visitTime") String visitTime,
			Model model) throws Exception{
		
		System.out.println("post 방식!!");
		//reservation_user메소드랑 동일하게 작성하는데,
		//reservation_user1페이지에서 넘어온 날짜와 시간을 포함한
		//예약 정보를 데이터베이스에 넣는과정을 추가해서 작성!!
		
		String itemId = request.getParameter("itemId");
		itemId = "32906223";
		
		dto.setReservNo("12345");
		dto.setUserId("user1");
		dto.setRealtorId("realtor1");
		
		model.addAttribute("visitDate",visitDate);
		model.addAttribute("visitTime",visitTime);
		
		dto.setItemId(itemId);
		System.out.println(dto.getVisitDate());
		System.out.println(dto.getVisitTime());
		
		
		reservationUserService.insertReservation(dto);
		
		System.out.println(request.getParameter("visitDate"));
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/newzips/reservation_user_complete1");
		
		return mav;
		
	}
	
	
	
	@GetMapping("/newzips/reservation_user_complete1")
	public ModelAndView reservation_user_complete(HttpServletRequest request) throws Exception{
		
		//주소에서 itemId 받아오기
		//해당 id에 대한 데이터 불러오기
		
		//mav에 담기
		String itemId = request.getParameter("itemId");
		itemId = "32906223";
		
		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dtoV",dtoV);
		mav.addObject("dtoR",dtoR);
		
		mav.setViewName("user/reservation_user_complete1");
		
		return mav;
	}
		
	

}
