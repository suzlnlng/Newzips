package com.boot.newzips.reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
import com.boot.newzips.dto.ResidenceReservDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.mapper.ReservationUserMapper;
import com.boot.newzips.service.ReservationUserService;
import com.boot.newzips.service.ResidentService;



@RestController
public class ReservationUserController {
	

	@Resource 
	private ResidentService residentService; 
	
	@Resource
	private ReservationUserService reservationUserService;
	
	//거주자~~~
	@GetMapping("/newzips/reservation_resident1")
	public ModelAndView reservation_resident(ResidenceReservDTO residenceReservDTO) throws Exception {

		String userId = "qwerty";
		String itemId = "35325508";
		
		ResidenceReservDTO dtoRR = residentService.selectResidenceReservUserId(userId);
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dtoRR",dtoRR);
		
		mav.setViewName("user/reservation_resident1");
		
		return mav;
		
	}
	
	@PostMapping("/newzips/reservation_resident1")
	public ModelAndView reservation_resident_ok(ResidenceReservDTO residenceReservDTO,
												@RequestParam("date") String availableDate,
												@RequestParam("time") String[] availableTime) throws Exception{
		
		String userId = "qwerty";
		String itemId = "35325508";
		
		System.out.println("되긴 하는거니?");

		
		residenceReservDTO.setItemId(itemId);
		residenceReservDTO.setUserId(userId);
		residenceReservDTO.setAvailableDate(availableDate);
		residenceReservDTO.setAvailableTime(availableTime[0]);
		
		//residentService.insertResidentReserv(residenceReservDTO);
		residentService.updateResidentReserv(residenceReservDTO);

		System.out.println("되긴 하는거니???");
		System.out.println("날짜" + availableDate);
		System.out.println("시간" + availableTime);


		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/newzips/reservation_user1");
		return mav;
		
	}
	
	
	//방문자~~~
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
	public ModelAndView reservation_user_complete(HttpServletRequest request) throws Exception{
		
		//주소에서 itemId 받아오기
		//해당 id에 대한 데이터 불러오기
		
		//mav에 담기
		
				
		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("user/reservation_user_complete1");
		
		return mav;
	}
	
	
	@GetMapping("/newzips/reservation_status")
	public ModelAndView reservation_status() throws Exception{
		
		System.out.println();
		System.out.println();
		System.out.println("여기여기");
		
		String userId = "karina";
		
		ModelAndView mav = new ModelAndView(); 
		
		List<ReservationStatusDTO> reservationList = 
				reservationUserService.getReservationList(userId);
		
		
		mav.addObject("reservationList",reservationList);
		System.out.println("============================");
		System.out.println(reservationList);

		
		
		//VisitorReservDTO dtoV = reservationUserService.selectReservationUserId(userId);
//		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);	
		
		
//		mav.addObject("dtoV",dtoV);

		
		mav.setViewName("user/reservation_status");
		return mav;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
