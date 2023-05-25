package com.boot.newzips.suggest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RealtorListDTO;
import com.boot.newzips.dto.RealtorSuggestionDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import javassist.compiler.ast.NewExpr;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.GetProxy;


@Controller
@RequiredArgsConstructor
public class SuggestRealtorController {
	//고객의 매물 조건을 확인
	private final SuggestRealtorService suggestRealtorService;
	
	private final HttpSession httpSession;	
	
	ModelAndView mav = new ModelAndView();
	
	@GetMapping("/newzips/clientListRealtor")
	public ModelAndView clientList(Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		String realtorId = null;
		
		try {
			realtorId = principal.getName();
		} catch (Exception e) {
			System.out.println(e.toString());
			mav.setViewName("redirect:/newzips/realtor/login");
			return mav;
		}
		
		List<SuggestionDTO> suggestion = suggestRealtorService.getSuggestList();
		
		mav.addObject("suggestionDTO", suggestion);
		mav.setViewName("realtor/clientListRealtor");

		return mav;
		
		}
		
	@GetMapping("/newzips/suggestListRealtor")
	public ModelAndView suggestList(HttpServletRequest req) throws Exception{
		//고객 조건에 맞는 제안 가능한 매물리스트
		String userId = req.getParameter("userId");
		String addrCity = req.getParameter("addrCity");
		String addrGu = req.getParameter("addrGu");
		
		SuggestionDTO suggest = suggestRealtorService.getSuggestInfo(userId);
		List<ListingDTO> listing = suggestRealtorService.getSuggestItem(suggest.getAddrCity(), suggest.getAddrGu());
		List<RealtorListDTO> realtorList = new ArrayList<RealtorListDTO>();
		List<RealtorSuggestionDTO> realtorSuggestion = new ArrayList<RealtorSuggestionDTO>();
		
		Iterator<ListingDTO> it = listing.iterator();
		
		while(it.hasNext()) {

			ListingDTO dto=it.next();
			RealtorListDTO realtor = new RealtorListDTO();
			RealtorSuggestionDTO realtorSuggest = new RealtorSuggestionDTO();
			
			if(dto.getAddrCity().equals(suggest.getAddrCity())&&dto.getAddrGu().equals(suggest.getAddrGu())
					&&dto.getYearly_monthly().equals(suggest.getYearly_monthly())) {

				if(suggest.getYearly_monthly().equals("전세")) {
						
						int junsaeDeposit = (suggestRealtorService.getJunsaeInfo(dto.getItemId())).getYearlyFee();
						
						if(suggest.getDeposit()>=junsaeDeposit) {
							if(suggest.getRooms()==(suggestRealtorService.getRoomInfo(dto.getItemId())).getRooms()) {

								JunsaeListingDTO junsae = suggestRealtorService.getJunsaeInfo(dto.getItemId());
								RoomInfoDTO room = suggestRealtorService.getRoomInfo(dto.getItemId());
								
								realtor.setItemId(dto.getItemId());
								realtor.setAddrDetail(dto.getAddrDetail());
								realtor.setYearly_monthly(dto.getYearly_monthly());
								realtor.setDeposit(junsae.getYearlyFee_web());
								realtor.setMaintenanceCost(dto.getMaintenanceCost());
								realtor.setArea(room.getArea());
								realtor.setRoomType(room.getRoomType());
								realtor.setRooms(room.getRooms());
								realtorSuggest.setUserId(suggest.getUserId());
								realtorSuggest.setItemId(dto.getItemId());
						}
						
					} else if(suggest.getYearly_monthly().equals("월세")) {

						int monthlyFee = (suggestRealtorService.getWolseInfo(dto.getItemId())).getMonthlyFee();
						
						if(suggest.getMonthlyFee()>=monthlyFee) {
							if(suggest.getRooms()==(suggestRealtorService.getRoomInfo(dto.getItemId())).getRooms()) {
								
								WolseListingDTO wolse = suggestRealtorService.getWolseInfo(dto.getItemId());
								RoomInfoDTO room = suggestRealtorService.getRoomInfo(dto.getItemId());
								
								
								realtor.setItemId(dto.getItemId());
								realtor.setAddrDetail(dto.getAddrDetail());
								realtor.setYearly_monthly(dto.getYearly_monthly());
								realtor.setMonthlyFee(wolse.getMonthlyFee_web());
								realtor.setMaintenanceCost(dto.getMaintenanceCost());
								realtor.setArea(room.getArea());
								realtor.setRoomType(room.getRoomType());
								realtor.setRooms(room.getRooms());
								realtorSuggest.setUserId(suggest.getUserId());
								realtorSuggest.setItemId(dto.getItemId());
						}
						
					}
				}
			}
					
					realtorList.add(realtor);
					realtorSuggestion.add(realtorSuggest);
		}
			
		}

		mav.addObject("suggest", suggest);
		mav.addObject("realtorListDTO", realtorList);
		mav.addObject("realtorSuggestion", realtorSuggestion);
		
		mav.setViewName("realtor/suggestListRealtor");
		
		return mav;
		
	}
	
		@PostMapping("/newzips/suggestListRealtor")
		public ModelAndView suggestList_post(HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			String[] suggestItems = request.getParameterValues("checkedValues[]");
			String userId = request.getParameter("userId");
			
			System.out.println(Arrays.toString(suggestItems));
			
			for (String itemId : suggestItems) {
				RealtorSuggestionDTO dto = new RealtorSuggestionDTO();
				dto.setRealtorId("hyeon");
				dto.setUserId(userId);
				dto.setItemId(itemId);
				suggestRealtorService.insertSuggestion(dto);
			}
			
			mav.addObject("userId", userId);
			
			mav.setViewName("/realtor/suggestListRealtor_ok");
			
			return mav;
			
		}
		
		
		@GetMapping("/newzips/suggestListRealtor_ok/{userId}")
		public ModelAndView suggestList_ok(@PathVariable("userId") String userId, 
				HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("userId", userId);
			mav.setViewName("/realtor/suggestListRealtor_ok");
			
			return mav;
			
		}
		
	
		@GetMapping("/newzips/suggestedItemRealtor")
		public ModelAndView SuggestedItem(Principal principal) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			String realtorId = null;
			
			try {
				realtorId = principal.getName();
			} catch (Exception e) {
				System.out.println(e.toString());
				mav.setViewName("redirect:/newzips/realtor/login");
				return mav;
			}
			
			//중개인 아이디, 제안받은 고객, 제안한 매물
			List<RealtorSuggestionDTO> realtorSuggestion= suggestRealtorService.getRealtorSuggestion(realtorId);

			//중개인이 제안한 매물번호로 불러온 RealtorListDTO을 모두 저장
			List<RealtorListDTO> realtorList = new ArrayList<RealtorListDTO>();
			
			Iterator<RealtorSuggestionDTO>  it = realtorSuggestion.iterator();
			
			while(it.hasNext()) {

				RealtorSuggestionDTO suggested = it.next();
				
				RealtorListDTO item = new RealtorListDTO();
				
				ListingDTO list = suggestRealtorService.getItemInfo(suggested.getItemId());
				JunsaeListingDTO junsae = suggestRealtorService.getJunsaeInfo(suggested.getItemId());
				WolseListingDTO wolse = suggestRealtorService.getWolseInfo(suggested.getItemId());
				RoomInfoDTO room = suggestRealtorService.getRoomInfo(suggested.getItemId());
				
				item.setUserId(suggested.getUserId());
				
				if(list.getYearly_monthly().equals("전세")) {
					
					item.setItemId(suggested.getItemId());
					item.setAddrDetail(list.getAddrDetail());
					item.setYearly_monthly(list.getYearly_monthly());
					item.setDeposit(junsae.getYearlyFee_web());					
					item.setMaintenanceCost(list.getMaintenanceCost());
					item.setArea(room.getArea());
					item.setRoomType(room.getRoomType());
					item.setRooms(room.getRooms());
					
					
				} else if(list.getYearly_monthly().equals("월세")) {

					item.setItemId(suggested.getItemId());
					item.setAddrDetail(suggested.getItemId());
					item.setYearly_monthly(list.getYearly_monthly());
					item.setDeposit(wolse.getDeposit_web());
					item.setMonthlyFee(wolse.getMonthlyFee_web());
					item.setMaintenanceCost(list.getMaintenanceCost());
					item.setArea(room.getArea());
					item.setRoomType(room.getRoomType());
					item.setRooms(room.getRooms());
					
				}
						
				realtorList.add(item);
			}
			
			mav.addObject("realtorId",realtorId);
			mav.addObject("realtorSuggestion", realtorSuggestion);
			mav.addObject("realtorList", realtorList);
			
			mav.setViewName("realtor/suggestedItemRealtor");
			
			return mav;
		}
		
}



