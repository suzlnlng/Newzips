package com.boot.newzips.contract;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.boot.newzips.service.ContractUserService;
import com.boot.newzips.service.ReservationUserService;



@Controller
public class ContractUserController {
	
	@Resource
	private ReservationUserService reservationUserService;
	
	@Resource
	private ContractUserService contractUserService;
	
	@GetMapping("newzips/contract_user/{itemId}")
	public ModelAndView contractUser(HttpServletRequest request, @PathVariable("itemId") String itemId, 
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

		MemberDTO member = contractUserService.getUser(userId);
		ListingDTO listing = contractUserService.getListing(itemId);
		WolseListingDTO wolse = contractUserService.getWolse(itemId);
		JunsaeListingDTO junsae = contractUserService.getJunsae(itemId);
		RealtorDTO realtor = contractUserService.getRealtor("서울");
		
		mav.addObject("member",member);
		mav.addObject("listing",listing);
		mav.addObject("wolse",wolse);
		mav.addObject("junsae",junsae);
		mav.addObject("realtor",realtor);
		
		System.out.println(member);
		
		System.out.println(realtor.getRealtorName());
		
		System.out.println("출력해라");
		
		mav.setViewName("/user/reservation_user_contract");
		
		return mav;
		
	}
	


}
