package com.boot.newzips.account;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/newzips")
public class AccountUserController {
	
	private final AccountUserService accountUserService;

	@GetMapping("/join")
	public ModelAndView join(MemberDTO memberDTO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/join_user2");
		
		return mav;
		
	}
	
	@PostMapping("/join")
	public String join_ok(@Valid MemberDTO memberDTO, BindingResult bindingResult) throws Exception{

		// validation을 통해 생기는 오류 join_user페이지로 넘기기
		if(bindingResult.hasErrors()) {
			return "user/join_user2";
		}
		
		//비밀번호 & 비밀번호 재확인 비교 
		if(!memberDTO.getUserPwd().equals(memberDTO.getUserPwd2())) {
			bindingResult.rejectValue("userPwd2", "passwordInCorrect",
					"비밀번호가 일치하지 않습니다");
			
			return "user/join_user2";
			
		}
		
		try {
		
			accountUserService.createMember(memberDTO);

			
		} catch (DataIntegrityViolationException e) {
			
			e.printStackTrace();
			
			bindingResult.rejectValue("userId", "이미 등록된 사용자입니다. 다른 아이디를 사용해주세요.");
			
			return "user/join_user2";
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
			bindingResult.reject("signupFailed", e.getMessage());
			
			return "user/join_user2";
			
		}

		return "redirect:/newzips";
		
	}
	
	@GetMapping("/login")
	public ModelAndView login(MemberDTO memberDTO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/login_user");

		return mav;
		
	}
	
}
