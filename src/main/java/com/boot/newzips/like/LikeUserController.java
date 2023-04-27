package com.boot.newzips.like;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.WishDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/newzips")
public class LikeUserController {
	
	private final LikeUserService likeUserService;
	
	//관심목록 페이지
	@GetMapping("/wish")
	public ModelAndView wish(Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("관심목록");
		String userId = null;
		
		
		//로그인 정보 존재하면 관심목록띄우고 아니면 로그인 진행
		try {
			
			userId = principal.getName();
			System.out.println(userId);
			
		} catch (Exception e) {
			
			mav.setViewName("redirect:/newzips/login");
			return mav;
			
		}
		
		List<ListAllDTO> lists = likeUserService.selectWish(userId);
		
		mav.setViewName("user/like_user");
		mav.addObject("lists", lists);
	
		return mav;
		
	}
	
	//관심목록에 추가
	@GetMapping("/addWish/{itemId}")
	public ModelAndView addWish(@PathVariable("itemId") String itemId,
			Principal principal) throws Exception{
		
		System.out.println(itemId);
		System.out.println(principal.getName());
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("userId", principal.getName());
		
		try {
			likeUserService.insertWish(params);
			mav.addObject("msg", "관심목록에 담았습니다.");
		} catch (DuplicateKeyException duplicate) {
			mav.addObject("msg", "이미 관심목록에 등록되어있는 상품입니다.");
		} catch (Exception e) {
			System.out.println(e);
		}

		return mav;
		
	}
	
	//관심목록에서 삭제
	@GetMapping("/deleteWish/{itemId}")
	public ModelAndView deleteWish(@PathVariable("itemId") String itemId,
			Principal principal) throws Exception{
		
		System.out.println("관심목록 삭제");
		System.out.println(itemId);
		System.out.println(principal.getName());
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemId", itemId);
		params.put("userId", principal.getName());
		
		try {
			likeUserService.deleteWish(params);
			mav.addObject("msg", "관심목록에서 삭제했습니다.");
		} catch (Exception e) {
			
		}
		
		
		mav.setViewName("redirect:/newzips/wish");
		
		return mav;
		
	}

}
