package com.boot.newzips.itemDetail;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.BuildingInfoDTO;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.WolseListingDTO;

@RestController
public class ItemDetailRealtorController {
	
	@Resource
	private ItemDetailService itemDetailService;
	
	@GetMapping("/newzips/itemDetail_realtor/{itemId}")
	public ModelAndView itemDetail_user(HttpServletRequest request, @PathVariable("itemId") String itemId) throws Exception{
				
		ModelAndView mav = new ModelAndView();	
				
		BuildingInfoDTO dtoB = itemDetailService.getReadData_buildingInfo(itemId);
		ListingDTO dtoL = itemDetailService.getReadData_listing(itemId);
		RoomInfoDTO dtoR = itemDetailService.getReadData_roomInfo(itemId);
		WolseListingDTO dtoW = itemDetailService.getReadData_wol(itemId);
		JunsaeListingDTO dtoJ = itemDetailService.getReadData_jun(itemId);
		
		mav.addObject("dtoL",dtoL);
		mav.addObject("dtoB",dtoB);
		mav.addObject("dtoR",dtoR);
		mav.addObject("dtoW",dtoW);
		mav.addObject("dtoJ",dtoJ);
		
		mav.setViewName("realtor/itemDetail_realtor");		
				
		return mav;
	}
	
}
