package com.boot.newzips.suggest;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.RealtorSuggestionDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.reservation.ReservationUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SuggestUserController {

	private final SuggestUserService suggestUserService;
	
	private final ReservationUserService reservationUserService;
	
	ModelAndView mav = new ModelAndView();
	
	@GetMapping("/newzips/insertOptionUser")
	public ModelAndView  getInsertForm(HttpServletRequest req, Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		String userId = null;
		
		try {
			userId = principal.getName();

		} catch (Exception e) {
			mav.setViewName("redirect:/newzips/login");
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

	
		List<RealtorSuggestionDTO> suggestionList = suggestUserService.getSuggestionList(userId);
		
		//System.out.println(reservationList.get(0).getConfirm());
		
		
		mav.addObject("suggestionList", suggestionList);
		mav.addObject("userId",userId);
		System.out.println("============================");
		System.out.println(suggestionList);
		
		System.out.println(userId);

		mav.setViewName("user/reservation_status");
		
		return mav;

	}
	
	
}
