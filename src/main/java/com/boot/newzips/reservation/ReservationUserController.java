package com.boot.newzips.reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
import com.boot.newzips.dto.ResidenceReservDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.boot.newzips.itemDetail.ItemDetailService;
import com.boot.newzips.mapper.ReservationUserMapper;
import com.boot.newzips.service.ReservationUserService;
import com.boot.newzips.service.ResidentService;

@RestController
public class ReservationUserController {

	@Resource
	private ResidentService residentService;
	
	@Resource 
	private ItemDetailService itemDetailService;

	@Resource
	private ReservationUserService reservationUserService;

	@GetMapping("/newzips/reservation_resident")
	public ModelAndView reservation_resident(HttpServletRequest request,
			Principal principal) throws Exception {

		ModelAndView mav = new ModelAndView();
		String userId = null;

		// 로그인 정보 존재하면 관심목록띄우고 아니면 로그인 진행
		try {
			userId = principal.getName();

		} catch (Exception e) {
			mav.setViewName("redirect:/newzips/login");
			return mav;
		}

		mav.setViewName("user/reservation_resident");

		return mav;

	}
	
	@GetMapping("/newzips/reservation_resident_data")
	public ModelAndView reservation_resident_data(HttpServletRequest request,
			Principal principal) throws Exception {
		
		ModelAndView mav = new ModelAndView("jsonView");
		String userId = principal.getName();
		String selectedDate = request.getParameter("selectedDate");
	
		// 유저아이디 기준으로 데이터 불러오기
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("availableDate", selectedDate);
		
		System.out.println("=====================컨트롤러");
		System.out.println(params.get("availableDate"));
		
		List<ResidenceReservDTO> dtoRR = residentService.selectAvailableTimes(params);
	
		System.out.println();
		System.out.println("===============");
		System.out.println(dtoRR.size());
		System.out.println();

		mav.addObject("dtoRR", dtoRR);

		return mav;
		
	}

	@PostMapping("/newzips/reservation_resident")
	public ModelAndView reservation_resident_ok(ResidenceReservDTO residentReservDTO,
			HttpServletRequest request, Principal principal) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/reservation_resident");

		String date = request.getParameter("selectedDate");
		String[] selectedTimes = request.getParameterValues("selectedTimes[]");
		String[] unselectedTimes = request.getParameterValues("unselectedTimes[]");
		
		String userId = principal.getName();
		System.out.println(userId);
		String itemId = "14669020";

		for (String time : selectedTimes) {

			ResidenceReservDTO dto = new ResidenceReservDTO();

			dto.setUserId(userId);
			dto.setItemId(itemId);
			dto.setAvailableDate(date);
			dto.setAvailableTime(time);
			dto.setAvailable("T");

			residentService.insertResidentReserv(dto);

		}
		
		for (String time : unselectedTimes) {
			
			ResidenceReservDTO dto = new ResidenceReservDTO();

			dto.setUserId(userId);
			dto.setItemId(itemId);
			dto.setAvailableDate(date);
			dto.setAvailableTime(time);
			dto.setAvailable("F");

			residentService.updateResidentReserv(dto);
			
		}
		

		return mav;

	}



	// 방문자~~~
	@GetMapping("/newzips/reservation_user/{itemId}")
	public ModelAndView reservation_user(
			VisitorReservDTO visitorReservDTO,
			HttpServletRequest request,
			Model model,
			@PathVariable("itemId") String itemId) throws Exception {

		// 매물번호 기준으로 데이터 불러오기
		
		//String itemId = request.getParameter("itemId");
		
		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
		ListingDTO dtoL = itemDetailService.getReadData_listing(itemId);
		WolseListingDTO dtoW = itemDetailService.getReadData_wol(itemId);
		JunsaeListingDTO dtoJ = itemDetailService.getReadData_jun(itemId);
		

		ModelAndView mav = new ModelAndView();

		// mav에 데이터 담기
		mav.addObject("dtoV", dtoV);
		mav.addObject("dtoR", dtoR);
		mav.addObject("dtoL",dtoL);
		mav.addObject("dtoW",dtoW);
		mav.addObject("dtoJ",dtoJ);

		mav.setViewName("user/reservation_user");

		return mav;

	}

	@PostMapping("/newzips/reservation_user/")
	public ModelAndView reservation_user_ok(VisitorReservDTO visitorReservDTO, HttpServletRequest request) throws Exception {

		String itemId = request.getParameter("itemId");
		
		ModelAndView mav = new ModelAndView();

		String visitDate = request.getParameter("visitDate");
		String visitTime = request.getParameter("visitTime");

		System.out.println(visitDate);
		System.out.println(visitTime);

		System.out.println("post 방식!!");
		System.out.println("=====================");
		
		
		// reservation_user메소드랑 동일하게 작성하는데,
		// reservation_user1페이지에서 넘어온 날짜와 시간을 포함한
		// 예약 정보를 데이터베이스에 넣는과정을 추가해서 작성!!

		// String itemId = request.getParameter("itemId");
		// itemId = "32906223";
		//
		// dto.setReservNo("12345");
		// dto.setUserId("user1");
		// dto.setRealtorId("realtor1");
		//
		//// model.addAttribute("visitDate",visitDate);
		//// model.addAttribute("visitTime",visitTime);
		//
		// dto.setItemId(itemId);
		// dto.setVisitDate(visitDate);
		// dto.setVisitTime(visitTime);
		//
		//
		// reservationUserService.insertReservation(dto);
		//
		// ModelAndView mav = new ModelAndView();
		//
		// // mav에 데이터 담기
		// mav.addObject("dtoV",dto);
		// mav.addObject("dtoR",reservationUserService.getReservationRoomInfo(itemId));
		// mav.addObject("visitDate",visitDate);
		// mav.addObject("visitTime",visitTime);
		// mav.setViewName("user/reservation_user_complete1");

		return mav;

	}

	@GetMapping("/newzips/reservation_user_complete")
	public ModelAndView reservation_user_complete(HttpServletRequest request,
			@RequestParam("itemId") String itemId) throws Exception {

		// 주소에서 itemId 받아오기
		// 해당 id에 대한 데이터 불러오기

		// mav에 담기

		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);

		ModelAndView mav = new ModelAndView();

		mav.addObject("dtoV", dtoV);
		mav.addObject("dtoR", dtoR);
		mav.addObject("itemId", itemId);
		mav.addObject("visitDate", request.getParameter("visitDate"));
		mav.addObject("visitTime", request.getParameter("visitTime"));

		mav.setViewName("user/reservation_user_complete1");

		return mav;
	}

	@GetMapping("/newzips/reservation_status")
	public ModelAndView reservation_status(Principal principal) throws Exception {

		ModelAndView mav = new ModelAndView();
		String userId = null;

		// 로그인 정보 존재하면 관심목록띄우고 아니면 로그인 진행
		try {
			userId = principal.getName();

		} catch (Exception e) {
			mav.setViewName("redirect:/newzips/login");
			return mav;
		}

		List<ReservationStatusDTO> reservationList = reservationUserService.getReservationList(userId);

		mav.addObject("reservationList", reservationList);
		System.out.println("============================");
		System.out.println(reservationList);

		// VisitorReservDTO dtoV =
		// reservationUserService.selectReservationUserId(userId);
		// RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);

		// mav.addObject("dtoV",dtoV);

		mav.setViewName("user/reservation_status");
		return mav;

	}

}
