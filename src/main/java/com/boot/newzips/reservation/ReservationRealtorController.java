package com.boot.newzips.reservation;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	private final HttpSession httpSession;
	
	private final ReservationRealtorService reservationRealtorService;

	@GetMapping("/newzips/reservationRequestRealtor")
	public ModelAndView ReservList(Principal principal) throws Exception {

		/*
		List<VisitorReservDTO> visitorReserv = reservationRealtorService.getReserverInfo();
		
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
		
		ModelAndView mav = new ModelAndView();
		String realtorId = null;
		
		try {
			realtorId = principal.getName();
		} catch (Exception e) {
			System.out.println(e.toString());
			mav.setViewName("redirect:/newzips/realtor/login");
			return mav;
		}

		
		List<ReservInfoDTO> reservInfo = reservationRealtorService.getReserverInfo(realtorId); 
		
		List<ReservInfoDTO> notConfirmed = new ArrayList<ReservInfoDTO>();
		
		Iterator<ReservInfoDTO> it = reservInfo.iterator();
		
		while(it.hasNext()) {
			ReservInfoDTO dto = it.next();
			
			ListingDTO listingDTO  = reservationRealtorService.getItemInfo(dto.getItemId());
			
				if(listingDTO!=null) {
				
					if(dto.getConfirm().equals("F")) {
						
					dto.setAddrDetail(listingDTO.getAddrDetail());
					dto.setYearly_monthly(listingDTO.getYearly_monthly());
				
						if(dto.getYearly_monthly().equals("전세")) {
							
							JunsaeListingDTO junsaeDTO = reservationRealtorService.getJunsaeInfo(dto.getItemId());
							dto.setYearlyFee(junsaeDTO.getYearlyFee_web());
						}
						else if(dto.getYearly_monthly().equals("월세")) {
							WolseListingDTO wolseDTO = reservationRealtorService.getWolseInfo(dto.getItemId());
							dto.setDeposit(wolseDTO.getDeposit_web());
							dto.setMonthlyFee(wolseDTO.getMonthlyFee_web());
						}
			
			MemberDTO memberDTO = reservationRealtorService.getMemberInfo(dto.getUserId());
			
			dto.setUserName(memberDTO.getUserName());
			dto.setUserBirth(memberDTO.getUserBirth());
			dto.setUserPhone(memberDTO.getUserPhone());
			
			notConfirmed.add(dto);
			
					}				
				}
				
		}
	
		mav.addObject("realtorId",realtorId);
		mav.addObject("reservInfoDTO", notConfirmed);
		mav.setViewName("realtor/reservationRequestRealtor");	
		
		return mav;
	}

	
	@GetMapping("/newzips/reservationConfirmedRealtor")
	public ModelAndView reservationConfirmedRealtor(HttpServletRequest req, Principal principal) throws Exception {

		ModelAndView mav = new ModelAndView();
		String realtorId = null;
		
		try {
			realtorId = principal.getName();
		} catch (Exception e) {
			System.out.println(e.toString());
			mav.setViewName("redirect:/newzips/realtor/login");
			return mav;
		}
		
		String userId = req.getParameter("userId");
		String itemId = req.getParameter("itemId");

		ReservInfoDTO one = reservationRealtorService.getConfirmedInfo(userId, realtorId, itemId);
		
		if(one.getConfirm().equals("F")) {
		reservationRealtorService.setConfirmed(userId, realtorId, itemId);
		
		mav.addObject("reservInfo", one);
		
		mav.setViewName("realtor/reservationConfirmedRealtor");
		
		} else if(one.getConfirm().equals("T")){
			
		mav.addObject("realtorId", realtorId);	
			
		mav.setViewName("realtor/alreadyConfirmedRealtor");
		
		}
		
		return mav;
	}

	@GetMapping("/newzips/reservationStateRealtor")
	public ModelAndView reservationStateRealtor(HttpServletRequest req, Principal principal) throws Exception{

		ModelAndView mav = new ModelAndView();
		String realtorId = null;
		
		try {
			realtorId = principal.getName();
		} catch (Exception e) {
			System.out.println(e.toString());
			mav.setViewName("redirect:/newzips/realtor/login");
			return mav;
		}
		
		List<ReservInfoDTO> reserved = reservationRealtorService.getReserverInfo(realtorId);
		List<ReservInfoDTO> confirmedList = new ArrayList<ReservInfoDTO>();
		
		Iterator<ReservInfoDTO> it = reserved.iterator();
		
		while(it.hasNext()) {
			
			ReservInfoDTO confirmed = it.next();
			ListingDTO listing = reservationRealtorService.getItemInfo(confirmed.getItemId());
			JunsaeListingDTO junsae = reservationRealtorService.getJunsaeInfo(confirmed.getItemId());
			WolseListingDTO wolse = reservationRealtorService.getWolseInfo(confirmed.getItemId());
			MemberDTO member = reservationRealtorService.getMemberInfo(confirmed.getUserId());
			
			if(confirmed.getConfirm().equals("T")) {
				
				if(listing.getYearly_monthly().equals("전세")) {

					confirmed.setAddrDetail(listing.getAddrDetail());
					confirmed.setConfirm(confirmed.getConfirm());
					confirmed.setDeposit(junsae.getYearlyFee_web());
					confirmed.setItemId(confirmed.getItemId());
					confirmed.setRealtorId(confirmed.getRealtorId());
					confirmed.setReservNo(confirmed.getReservNo());
					confirmed.setUserName(member.getUserName());
					confirmed.setUserPhone(member.getUserPhone());
					confirmed.setVisitDate(confirmed.getVisitDate());
					confirmed.setVisitTime(confirmed.getVisitTime());
					confirmed.setYearly_monthly(listing.getYearly_monthly());
					
				}else if(listing.getYearly_monthly().equals("월세")) {

					confirmed.setAddrDetail(listing.getAddrDetail());
					confirmed.setConfirm(confirmed.getConfirm());
					confirmed.setDeposit(wolse.getDeposit_web());
					confirmed.setMonthlyFee(wolse.getMonthlyFee_web());
					confirmed.setItemId(confirmed.getItemId());
					confirmed.setRealtorId(confirmed.getRealtorId());
					confirmed.setReservNo(confirmed.getReservNo());
					confirmed.setUserName(member.getUserName());
					confirmed.setUserPhone(member.getUserPhone());
					confirmed.setVisitDate(confirmed.getVisitDate());
					confirmed.setVisitTime(confirmed.getVisitTime());
					confirmed.setYearly_monthly(listing.getYearly_monthly());
					
				}

				confirmedList.add(confirmed);
				
		}
		}

		mav.addObject("realtorId", realtorId);
		mav.addObject("reserved", reserved);
		mav.addObject("confirmedList", confirmedList);
		
		mav.setViewName("realtor/reservationStateRealtor");
		
		return mav;
	}

}

