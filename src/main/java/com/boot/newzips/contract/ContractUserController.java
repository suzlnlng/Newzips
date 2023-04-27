package com.boot.newzips.contract;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.boot.newzips.service.contractUSerService;



@Controller
public class ContractUserController {
	
	@Resource
	private contractUSerService contractUserService;
	
	@GetMapping("newzips/contract_user")
	public ModelAndView contractUser(HttpServletRequest request) throws Exception {
		
		
		String userId = request.getParameter("userId");
		userId = "suzi";
		
		String itemId = request.getParameter("itemId");
		itemId = "2396640";
		
		ModelAndView mav = new ModelAndView();

		MemberDTO member = contractUserService.getUser(userId);
		ListingDTO listing = contractUserService.getListing(itemId);
		WolseListingDTO wolse = contractUserService.getWolse(itemId);
		JunsaeListingDTO junsae = contractUserService.getJunsae(itemId);
		
		mav.addObject("member",member);
		mav.addObject("listing",listing);
		mav.addObject("wolse",wolse);
		mav.addObject("junsae",junsae);
		
		System.out.println(member);
		
		System.out.println("출력해라");
		
		mav.setViewName("/user/reservation_user_contract1");
		
		return mav;
		
	}
	


}
