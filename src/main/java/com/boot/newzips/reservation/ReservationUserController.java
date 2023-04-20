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

import com.boot.newzips.dto.ResidenceReservDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;

import com.boot.newzips.service.ReservationUserService;
import com.boot.newzips.service.ResidentService;

import oracle.jdbc.proxy.annotation.GetProxy;

@RestController
public class ReservationUserController {
	
	@Resource 
	private ResidentService residentService; 
	
	@Resource
	private ReservationUserService reservationUserService;
	
	
	@GetMapping("/newzips/reservation_resident1")
	public ModelAndView reservation_resident(ResidenceReservDTO residenceReservDTO, HttpServletRequest request) throws Exception {

		//임의로 설정
		String userId = request.getParameter("userId");
		userId = "123";
		
		//유저아이디 기준으로 데이터 불러오기
		ResidenceReservDTO dtoRR = residentService.selectResidenceReservUserId(userId);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dtoRR",dtoRR);
		
		mav.setViewName("user/reservation_resident1");
		
		return mav;
		
	}
	
	@PostMapping("/newzips/reservation_resident1")
	public ModelAndView reservation_resident_ok(ResidenceReservDTO residentReservDTO,
												HttpServletRequest request) throws Exception{
		
		System.out.println("되긴 하는거니?");
		
		ModelAndView mav = new ModelAndView();
		
		String date = request.getParameter("date");
		String[] times = request.getParameterValues("time");
		
		System.out.println("되긴 하는거니?");
		System.out.println(date);
		System.out.println(times);
		
		//리다이렉트시켜쥬기
		
		return mav;
		
	}
	
	
	
	@GetMapping("/newzips/reservation_user1")
	public ModelAndView reservation_user(VisitorReservDTO visitorReservDTO, 
										 HttpServletRequest request,
										 Model model) throws Exception{
		
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
	public ModelAndView reservation_user_ok(VisitorReservDTO visitorReservDTO,HttpServletRequest request,
			Model model) throws Exception{

		ModelAndView mav = new ModelAndView();
		
		String visitDate = request.getParameter("visitDate");
		String visitTime = request.getParameter("visitTime");
		
		System.out.println(visitDate);
		System.out.println(visitTime);
		
		System.out.println("post 방식!!");
		System.out.println("=====================");
		//reservation_user메소드랑 동일하게 작성하는데,
		//reservation_user1페이지에서 넘어온 날짜와 시간을 포함한
		//예약 정보를 데이터베이스에 넣는과정을 추가해서 작성!!
		
//		String itemId = request.getParameter("itemId");
//		itemId = "32906223";
//		
//		dto.setReservNo("12345");
//		dto.setUserId("user1");
//		dto.setRealtorId("realtor1");
//		
////		model.addAttribute("visitDate",visitDate);
////		model.addAttribute("visitTime",visitTime);
//		
//		dto.setItemId(itemId);
//        dto.setVisitDate(visitDate);
//        dto.setVisitTime(visitTime);
//        
//
//        reservationUserService.insertReservation(dto);
//
//        ModelAndView mav = new ModelAndView();
//
//        // mav에 데이터 담기
//        mav.addObject("dtoV",dto);
//        mav.addObject("dtoR",reservationUserService.getReservationRoomInfo(itemId));
//        mav.addObject("visitDate",visitDate);
//        mav.addObject("visitTime",visitTime);
//        mav.setViewName("user/reservation_user_complete1");

        return mav;
		
	}
	
	
	
	@GetMapping("/newzips/reservation_user_complete1")
	public ModelAndView reservation_user_complete(HttpServletRequest request,
												  @RequestParam("itemId") String itemId) throws Exception{
		
		//주소에서 itemId 받아오기
		//해당 id에 대한 데이터 불러오기
		
		//mav에 담기
		
		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dtoV",dtoV);
		mav.addObject("dtoR",dtoR);
		mav.addObject("itemId",itemId);
		mav.addObject("visitDate",request.getParameter("visitDate"));
		mav.addObject("visitTime",request.getParameter("visitTime"));
		
		mav.setViewName("user/reservation_user_complete1");
		
		return mav;
	}
	
}
