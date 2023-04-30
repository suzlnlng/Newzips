package com.boot.newzips.reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.security.Principal;
import java.text.SimpleDateFormat;

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

import com.boot.newzips.account.AccountUserService;
import com.boot.newzips.contract.ContractUserService;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
import com.boot.newzips.dto.ResidenceReservDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.boot.newzips.itemDetail.ItemDetailService;

@RestController
public class ReservationUserController {

	@Resource
	private ResidentService residentService;
	
	@Resource 
	private ItemDetailService itemDetailService;

	@Resource
	private ReservationUserService reservationUserService;
	
	@Resource
	private AccountUserService accountUserService;
	
	@Resource
	private ContractUserService contractUserService;

	@GetMapping("/newzips/reservation_resident")
	public ModelAndView reservation_resident(HttpServletRequest request,
			Principal principal) throws Exception {

		ModelAndView mav = new ModelAndView();
		String userId = null;
		List<ResidenceReservDTO> dtoRR = null;

		// 로그인 정보 존재하면 관심목록띄우고 아니면 로그인 진행
		try {
			userId = principal.getName();
			System.out.println("======reservation에서 받은 principal.getName()=========");
			System.out.println(userId);

		} catch (Exception e) {
			mav.setViewName("redirect:/newzips/login");
			return mav;
		}
		
		dtoRR = residentService.selectResidenceReservUserId(userId);
		
		//거주자예약 정보가 존재하지 않으면 매물등록 먼저 진행할 수 있도록 창 띄워줌
		if (dtoRR.size() == 0) {
			Optional<MemberDTO> _user = accountUserService.getUserById(userId);
			MemberDTO user = _user.get();
			mav.addObject("user", user);
			mav.setViewName("user/reservation_resident_error");
			return mav;
		}
	
		mav.setViewName("user/reservation_resident");

		return mav;

	}
	
	
	//거주자 예약화면에서 띄워줄 데이터 load
	@GetMapping("/newzips/reservation_resident_data")
	public ModelAndView reservation_resident_data(HttpServletRequest request,
			Principal principal) throws Exception {	

		String userId = principal.getName();
		String selectedDate = request.getParameter("selectedDate");
	
		// 유저아이디 기준으로 데이터 불러오기
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("availableDate", selectedDate);
		
		List<ResidenceReservDTO> dtoRR = residentService.selectAvailableTimes(params);

		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("dtoRR", dtoRR);

		return mav;
		
	}

	@PostMapping("/newzips/reservation_resident")
	public ModelAndView reservation_resident_ok(ResidenceReservDTO residentReservDTO,
			HttpServletRequest request, Principal principal) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/reservation_resident");

		//view에서 전달받은 날짜와 시간들 변수 받기
		String date = request.getParameter("selectedDate");
		String[] selectedTimes = request.getParameterValues("selectedTimes[]");
		String[] unselectedTimes = request.getParameterValues("unselectedTimes[]");

		//userId, itemId 받기
		String userId = principal.getName();
		String itemId = reservationUserService.getItemIdByUserId(userId);

		//선택된 시간 업데이트
		for (String time : selectedTimes) {

			ResidenceReservDTO dto = new ResidenceReservDTO();

			dto.setUserId(userId);
			dto.setItemId(itemId);
			dto.setAvailableDate(date);
			dto.setAvailableTime(time);
			dto.setAvailable("T");

			residentService.updateResidentReserv(dto);

		}
		
		//선택되지 않은 시간 업데이트
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

	
	//집 내놓기
	@GetMapping("/newzips/register_listing")
	public ModelAndView register_listing(Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		//userId 받아오기
		String userId = principal.getName();
		//userId로 itemId 받아오기
		String itemId = reservationUserService.getItemIdByUserId(userId);

		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
		ListingDTO dtoL = itemDetailService.getReadData_listing(itemId);
		WolseListingDTO dtoW = itemDetailService.getReadData_wol(itemId);
		JunsaeListingDTO dtoJ = itemDetailService.getReadData_jun(itemId);

		// mav에 데이터 담기
		mav.addObject("dtoV", dtoV);
		mav.addObject("dtoR", dtoR);
		mav.addObject("dtoL",dtoL);
		mav.addObject("dtoW",dtoW);
		mav.addObject("dtoJ",dtoJ);
		
		mav.setViewName("/user/register_listing");
		
		return mav;
		
	}
	
	//매물내놓은 날짜 기준으로 한달 예약 데이터 생성
	@GetMapping("/newzips/register_listing_ok/{itemId}")
	public ModelAndView register_listing_ok(@PathVariable("itemId") String itemId, Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		//userId 받아오기
		String userId = principal.getName();
		String[] timeList = {"09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"};
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		for(int i=1; i<=30; i++) {
			for (int j=0; j<timeList.length; j++) {
				
				ResidenceReservDTO dto = new ResidenceReservDTO();
				dto.setUserId(userId);
				dto.setItemId(itemId);
				dto.setAvailableDate(format.format(cal.getTime()));
				dto.setAvailableTime(timeList[j]);
				dto.setAvailable("F");
				
				residentService.insertResidentReserv(dto);
				
			}
			
			cal.add(Calendar.DAY_OF_MONTH, +1);
			
		}
		
		mav.setViewName("redirect:/newzips/reservation_resident");
		
		return mav;
		
	}


	// 방문자 예약
	@GetMapping("/newzips/reservation_user/{itemId}")
	public ModelAndView reservation_user(
			VisitorReservDTO visitorReservDTO,
			HttpServletRequest request,
			Model model,
			@PathVariable("itemId") String itemId,
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

		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
		ListingDTO dtoL = itemDetailService.getReadData_listing(itemId);
		WolseListingDTO dtoW = itemDetailService.getReadData_wol(itemId);
		JunsaeListingDTO dtoJ = itemDetailService.getReadData_jun(itemId);
		
		// mav에 데이터 담기
		mav.addObject("dtoR", dtoR);
		mav.addObject("dtoL",dtoL);
		mav.addObject("dtoW",dtoW);
		mav.addObject("dtoJ",dtoJ);

		mav.setViewName("user/reservation_user");

		return mav;

	}

	@PostMapping("/newzips/reservation_user/{itemId}")
	public ModelAndView reservation_user_ok(VisitorReservDTO visitorReservDTO, HttpServletRequest request, 
			@PathVariable("itemId") String itemId, Principal principal) throws Exception {
		
		ModelAndView mav = new ModelAndView();

		String visitDate = request.getParameter("visitDate");
		String visitTime = request.getParameter("visitTime");
		
		String userId = principal.getName();
		
		VisitorReservDTO dto = new VisitorReservDTO();
		
		dto.setReservNo(Integer.toString(reservationUserService.maxNum() + 1));
		dto.setUserId(userId);
		dto.setItemId(itemId);		
		dto.setRealtorId("hyeon"); //realtorId 랜덤으로 하는 것 추가해야됨,,!!
		dto.setVisitDate(visitDate);
		dto.setVisitTime(visitTime);
		dto.setConfirm("F");
		
		reservationUserService.insertReservation(dto);

		mav.setViewName("redirect:/newzips/reservation_user_complete/" + itemId);
		
		return mav;

	}

	@GetMapping("/newzips/reservation_user_complete/{itemId}")
	public ModelAndView reservation_user_complete(HttpServletRequest request,
			@PathVariable("itemId") String itemId) throws Exception {

		// 주소에서 itemId 받아오기
		// 해당 id에 대한 데이터 불러오기 

		// mav에 담기  

		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
		ListingDTO dtoL = contractUserService.getListing(itemId);
		WolseListingDTO dtoW = contractUserService.getWolse(itemId);
		JunsaeListingDTO dtoJ = contractUserService.getJunsae(itemId);
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("dtoV", dtoV);
		mav.addObject("dtoR", dtoR);
		mav.addObject("itemId", itemId);
		mav.addObject("visitDate", request.getParameter("visitDate"));
		mav.addObject("visitTime", request.getParameter("visitTime"));
		mav.addObject("dtoL",dtoL);
		mav.addObject("dtoW",dtoW);
		mav.addObject("dtoJ",dtoJ);
		mav.setViewName("user/reservation_user_complete");

		return mav;
			
	}

	
	@GetMapping("/newzips/reservation_status")
	public ModelAndView reservation_status(Principal principal,HttpServletRequest request) throws Exception {

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
		
		//System.out.println(reservationList.get(0).getConfirm());
		
		
		mav.addObject("reservationList", reservationList);
		mav.addObject("userId",userId);
		System.out.println("============================");
		System.out.println(reservationList);
		
		System.out.println(userId);

//		for(ReservationStatusDTO reservation : reservationList) {
//			System.out.println(reservation.getVisitDate());
//			System.out.println(reservation.getUserId());
//		}
		
	

		// VisitorReservDTO dtoV =
		// reservationUserService.selectReservationUserId(userId);
		// RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);

		// mav.addObject("dtoV",dtoV);

		mav.setViewName("user/reservation_status");
		
		return mav;

	}

}
