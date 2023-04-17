package com.boot.newzips.suggest;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.SuggestionDTO;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class SuggestRealtorController {

	private final SuggestRealtorService suggestRealtorService;
	
	@GetMapping("/newzips/clientListRealtor")
	public ModelAndView suggestList() throws Exception{
		
		ModelAndView mav = new ModelAndView();

		List<SuggestionDTO> suggestion = suggestRealtorService.getSuggestInfo();
		
		mav.addObject("suggestionDTO", suggestion);
		mav.setViewName("realtor/clientListRealtor");

		return mav;
		
		}
		
}
