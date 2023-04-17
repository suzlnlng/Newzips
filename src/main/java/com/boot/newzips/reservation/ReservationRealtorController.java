package com.boot.newzips.reservation;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.ReservInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.dto.WolseListingDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationRealtorController {

	private final ReservationRealtorService reservationRealtorService;
	
	
	
	//매물 방문 예약요청 리스트
	@GetMapping("/newzips/reservationRequestRealtor")
	public ModelAndView ReservList() throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		/*VisitorReserv에 다른 테이블을 Dto로 넣어놨을 때 사용 가능한 코드
		//예약자 정보 불러오기
		List<VisitorReservDTO> visitorReserv = 
				reservationRealtorService.getReserverInfo();
		
		Iterator<VisitorReservDTO> it = visitorReserv.iterator();
		
		while(it.hasNext()) {
			
			VisitorReservDTO visitor = it.next();
			
			if(reservationRealtorService.getJunsaeInfo(visitor.getItemId())!=null){
				
				visitor.setJunsae(reservationRealtorService.getJunsaeInfo(visitor.getItemId()));
				
			} else if(reservationRealtorService.getWolseInfo(visitor.getItemId())!=null) {
				
				visitor.setWolse(reservationRealtorService.getWolseInfo(visitor.getItemId()));
				
			}
			
			visitor.setMember(reservationRealtorService.getMemberInfo(visitor.getUserId()));
			visitor.setListing(reservationRealtorService.getItemInfo(visitor.getItemId()));
			
		}
		
		mav.addObject("VisitorReservDTO", visitorReserv);
		*/
		
		
		//뽑아온 데이터를 새로운 DTO에 다 담아서 통째로 넘길 때 사용하는 코드
		//예약자 정보 불러와서 list에 담기
		List<ReservInfoDTO> reservInfo = reservationRealtorService.getReserverInfo(); 
		
		Iterator<ReservInfoDTO> it = reservInfo.iterator();
		
		while(it.hasNext()) {
			ReservInfoDTO dto = it.next();
			ListingDTO listingDTO  = reservationRealtorService.getItemInfo(dto.getItemId());
			
			if(listingDTO!=null) {
				
				dto.setAddrDetail(listingDTO.getAddrDetail());
				dto.setYearly_monthly(listingDTO.getYearly_monthly());
			
				if(dto.getYearly_monthly().equals("전세")) {
					
					JunsaeListingDTO junsaeDTO = reservationRealtorService.getJunsaeInfo(dto.getItemId());
					dto.setYearlyFee(junsaeDTO.getYearlyFee());
				}
				else if(dto.getYearly_monthly().equals("월세")) {
					WolseListingDTO wolseDTO = reservationRealtorService.getWolseInfo(dto.getItemId());
					dto.setDeposit(wolseDTO.getDeposit());
					dto.setMonthlyFee(wolseDTO.getMonthlyFee());
				}
				
			}
			
			MemberDTO memberDTO = reservationRealtorService.getMemberInfo(dto.getUserId());
			
			dto.setUserName(memberDTO.getUserName());
			dto.setUserBirth(memberDTO.getUserBirth());
			dto.setUserPhone(memberDTO.getUserPhone());
			
		}

		mav.addObject("reservInfoDTO", reservInfo);
		mav.setViewName("realtor/reservationRequestRealtor");	
		
		return mav;
	}

	//방문 예약 현황-중개인
	@GetMapping("/newzips/reservationStateRealtor")
	public String reservationStateRealtor(){
		
//		ModelAndView mav = new ModelAndView();
		
	
		
		return "realtor/reservationStateRealtor";
	}

}

