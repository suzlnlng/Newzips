package com.boot.newzips.suggest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.SuggestionDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SuggestUserController {

	private final SuggestUserService suggestUserService;
	
	ModelAndView mav = new ModelAndView();
	
	@GetMapping("/newzips/insertOptionUser")
	public ModelAndView  getInsertForm(HttpServletRequest req) throws Exception{
		
		String userId=req.getParameter("userId");
		
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
	
	
	
}
