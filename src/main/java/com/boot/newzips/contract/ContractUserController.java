package com.boot.newzips.contract;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.itemDetail.ItemDetailService;
import com.boot.newzips.itemList.itemListUserService;
import com.boot.newzips.service.ContractUserService;

@Controller
public class ContractUserController {
	
	
	@Resource
	private ContractUserService contractUserService;
	
	@GetMapping("newzips/contract_user")
	public ModelAndView contractUser(HttpServletRequest request) throws Exception {
		
//		String userId = request.getParameter("userId");
//		userId = "karina";
		
		String itemId = request.getParameter("itemId");
		itemId = "35738667";
		
		ModelAndView mav = new ModelAndView();
		
		ListingDTO dtoL = contractUserService.getListing(itemId);

		
		mav.setViewName("/user/reservation_user_contract1");
		
		return mav;
	}
	


}
