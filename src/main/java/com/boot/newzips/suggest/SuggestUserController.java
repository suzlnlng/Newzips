package com.boot.newzips.suggest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.account.AccountRealtorService;
import com.boot.newzips.account.AccountUserService;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.RealtorSuggestionDTO;
import com.boot.newzips.dto.ReservInfoDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.boot.newzips.reservation.ReservationRealtorService;
import com.boot.newzips.reservation.ReservationUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SuggestUserController {

	private final SuggestUserService suggestUserService;
	
	private final ReservationUserService reservationUserService;
	
	private final ReservationRealtorService reservationRealtorService;
	
	private final AccountRealtorService accountRealtorService;
	
	private final AccountUserService accountUserService;
	
	ModelAndView mav = new ModelAndView();
	
	@GetMapping("/newzips/insertOptionUser")
	public ModelAndView  getInsertForm(SuggestionDTO suggestionDTO, HttpServletRequest req, Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		String userId = null;
		
		try {
			userId = principal.getName();

		} catch (Exception e) {
			mav.setViewName("redirect:/newzips/login");
			return mav;
		}
		
		SuggestionDTO getAll = suggestUserService.getUserInfo(userId);
		
		if(getAll != null) {
			Optional<MemberDTO> _user = accountUserService.getUserById(userId);
			MemberDTO user = _user.get();
			mav.addObject("user", user);
			mav.setViewName("user/suggestion_error");
			return mav;
		}
		
		SuggestionDTO suggestion = new SuggestionDTO();
		
		String userPhone = suggestUserService.getUserPhone(userId);
		
		suggestion.setUserId(userId);
		suggestion.setUserPhone(userPhone);
		
		mav.addObject("suggestion", suggestion);
		
		mav.setViewName("user/insertOptionUser");
		
		return mav;
	}
	
	@PostMapping("/newzips/insertCompletedUser")
	public ModelAndView  insertCompleted(@Valid SuggestionDTO suggestionDTO) 
					throws Exception{
		
		System.out.println(suggestionDTO.getUserId());
		
		
		suggestUserService.setSuggestionInfo(suggestionDTO.getUserId(), suggestionDTO.getUserPhone(),suggestionDTO.getAddrCity(), suggestionDTO.getAddrGu()
				,suggestionDTO.getYearly_monthly(), suggestionDTO.getDeposit(), suggestionDTO.getMonthlyFee(), suggestionDTO.getRooms(),
				suggestionDTO.getArea(),suggestionDTO.getMaintenanceCost());
		
		
		mav.setViewName("user/insertCompletedUser");
		
		return mav;
	}
	
	@GetMapping("newzips/updateOptionUser")
	public ModelAndView updateOption(HttpServletRequest req) throws Exception {
		
		String userId=req.getParameter("userId");
		
		SuggestionDTO getAll = suggestUserService.getUserInfo(userId);
		
		mav.addObject("getAll", getAll);
		
		mav.setViewName("user/updateOptionUser");
		
		return mav;
	}
	
	
	@PostMapping("newzips/updateOkUser")
	public ModelAndView updatedOk(@Valid SuggestionDTO suggestionDTO) throws Exception {
		
		System.out.println(suggestionDTO.getAddrCity()+suggestionDTO.getAddrGu());
		suggestUserService.updateOption(suggestionDTO);
		
		mav.setViewName("user/updateOkUser");
		
		return mav;
	}
	
	@GetMapping("/newzips/suggestion_status")
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

	
		List<RealtorSuggestionDTO> suggestListing = suggestUserService.getSuggestionList(userId);
		
		List<ReservInfoDTO> suggestionList = new ArrayList<ReservInfoDTO>();
		
		for (RealtorSuggestionDTO dto : suggestListing) {
			
			ReservInfoDTO suggestDTO = new ReservInfoDTO();
			
			ListingDTO listingDTO  = reservationRealtorService.getItemInfo(dto.getItemId());
			
			if(listingDTO!=null) {
					
				suggestDTO.setAddrDetail(listingDTO.getAddrDetail());
				suggestDTO.setYearly_monthly(listingDTO.getYearly_monthly());
			
				if(listingDTO.getYearly_monthly().equals("전세")) {
						
					JunsaeListingDTO junsaeDTO = reservationRealtorService.getJunsaeInfo(dto.getItemId());
					suggestDTO.setYearlyFee(junsaeDTO.getYearlyFee_web());
					
				}else if(listingDTO.getYearly_monthly().equals("월세")) {
					
					WolseListingDTO wolseDTO = reservationRealtorService.getWolseInfo(dto.getItemId());
					suggestDTO.setDeposit(wolseDTO.getDeposit_web());
					suggestDTO.setMonthlyFee(wolseDTO.getMonthlyFee_web());	
					
				}
				
			}
			
			Optional<RealtorDTO> realtorDTO = accountRealtorService.getUserById(dto.getRealtorId());
			
			if(realtorDTO.isPresent()) {
				RealtorDTO realtor = realtorDTO.get();
				suggestDTO.setUserId(realtor.getRealtorId());
				suggestDTO.setUserName(realtor.getRealtorName());
				System.out.println(realtor.getRealtorName());
				suggestDTO.setUserPhone(realtor.getRealtorPhone());
			}
			
			suggestDTO.setItemId(dto.getItemId());
		
			suggestionList.add(suggestDTO);
		
		}
		
		
		mav.addObject("suggestionList", suggestionList);
		mav.addObject("userId",userId);

		mav.setViewName("user/suggestion_status");
		
		return mav;

	}
	
	
}
