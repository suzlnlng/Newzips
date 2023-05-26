package com.boot.newzips.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	//사용자 회원가입 약관동의
	@GetMapping("/joinTerm")
	public ModelAndView joinTerm() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/joinTerm_user");
		
		return mav;
		
	}
	

	//사용자 회원가입 페이지
	@GetMapping("/join")
	public ModelAndView join(MemberDTO memberDTO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("user/join_user");
		
		return mav;
		
	}
	
	//사용자 회원가입 진행
	@PostMapping("/join")
	public String join_ok(@Valid MemberDTO memberDTO, BindingResult bindingResult) throws Exception{

		// validation을 통해 생기는 오류 join_user페이지로 넘기기
		if(bindingResult.hasErrors()) {
			return "user/join_user";
		}
		
		//비밀번호 & 비밀번호 재확인 비교 
		if(!memberDTO.getUserPwd().equals(memberDTO.getUserPwd2())) {
			
			bindingResult.rejectValue("userPwd2", "passwordInCorrect", "비밀번호가 일치하지 않습니다");
			return "user/join_user";
			
		}
		
		//중복ID 검색
		if(accountUserService.checkId(memberDTO.getUserId())) {
			bindingResult.rejectValue("userId", "duplicateUserID", "이미 등록된 사용자입니다. 다른 아이디를 사용해주세요.");
			return "user/join_user"; 
		}

		
		//회원가입 진행
		try {
			
			accountUserService.createMember(memberDTO);
			
		} catch (DataIntegrityViolationException e) {
			
			e.printStackTrace();
			
			bindingResult.rejectValue("userId", "이미 등록된 사용자입니다. 다른 아이디를 사용해주세요.");
			
			return "user/join_user";
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
			bindingResult.reject("signupFailed", e.getMessage());
			
			return "user/join_user";
			
		}

		return "redirect:/newzips";
		
	}
	
	//로그인 페이지 창 띄우기
	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request, MemberDTO memberDTO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		try {
			if(request.getParameter("error").equals("true")) {
				String errorMsg = request.getParameter("exception");
				if(errorMsg.equals("badcredential")) {
					mav.addObject("error", "아이디 또는 비밀번호를 확인해주세요.");	
				}
			}
			
		} catch (Exception e) {
			System.out.println("에러: " + request.getParameter("error"));
			System.out.println(e.toString());
		}
				
		mav.setViewName("user/login_user");

		return mav;
		
	}
	
	//아이디 찾기
	@PostMapping("/findId")
	public ModelAndView findId(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		
		String userId = null;
		
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("userPhone", userPhone);
		
		try {
			userId = accountUserService.findId(params);
			System.out.println(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("userId", userId);
		
		return mav;
		
	}
	
	//비밀번호 찾기
	@PostMapping("/findPwd")
	public ModelAndView findPwd(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		
		String userPwd = null;

		Map<String, Object> params= new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("userName", userName);
		params.put("userPhone", userPhone);
		
		try {
			userPwd = accountUserService.findPwd(params);
			System.out.println("비밀번호 찾기");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("userPwd", userPwd);
		
		return mav;
		
	}
	
	
}
