package com.boot.newzips.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.RealtorDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/newzips")
public class AccountRealtorController {
	
	private final AccountRealtorService accountRealtorService;
	
	@GetMapping("/realtor/joinTerm")
	public ModelAndView joinTerm() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("realtor/joinTerm_realtor");
		
		return mav;
		
	}
	

	@GetMapping("/realtor/join")
	public ModelAndView join(RealtorDTO realtorDTO) throws Exception{
		
		System.out.println("=======================");
		System.out.println("join get...");
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("realtor/join_realtor");
		
		return mav;
		
	}
	
	@PostMapping("/realtor/join")
	public String join_ok(@Valid RealtorDTO realtorDTO, BindingResult bindingResult) throws Exception{
		
		// validation을 통해 생기는 오류 join_user페이지로 넘기기
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "realtor/join_realtor";
		}
		
		//비밀번호 & 비밀번호 재확인 비교 
		if(!realtorDTO.getRealtorPwd().equals(realtorDTO.getRealtorPwd2())) {
			bindingResult.rejectValue("realtorPwd2", "passwordInCorrect",
					"비밀번호가 일치하지 않습니다");
			return "realtor/join_realtor";
			
		}
		
		if(accountRealtorService.checkId(realtorDTO.getRealtorId())) {
			bindingResult.rejectValue("realtorId", "duplicateRealtorID", "이미 등록된 사용자입니다. 다른 아이디를 사용해주세요.");
			return "realtor/join_realtor"; 
		}

		try {
			
			//회원정보 입력
			accountRealtorService.createMember(realtorDTO);
			
		} catch (DataIntegrityViolationException e) {
			
			e.printStackTrace();
			
			bindingResult.rejectValue("userId", "이미 등록된 사용자입니다. 다른 아이디를 사용해주세요.");
			
			return "realtor/join_realtor";
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
			bindingResult.reject("signupFailed", e.getMessage());
			
			return "realtor/join_realtor";
			
		}

		return "redirect:/newzips/realtor";
		
	}
	
	@GetMapping("/realtor/login")
	public ModelAndView login(RealtorDTO realtorDTO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("realtor/login_realtor");

		return mav;
		
	}
	
	/*
	@PostMapping("/realtor/login")
	public ModelAndView login_ok(RealtorDTO realtorDTO, HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		RealtorDTO realtorUser = null; //아이디랑 이름만 받아오는 DTO로 수정해야 할듯
		
		System.out.println("==============================");
		System.out.println(realtorDTO.getRealtorId());
		
		Optional<RealtorDTO> _user = accountRealtorService.getUserById(realtorDTO.getRealtorId());
		
		if(_user.isPresent())
			realtorUser = _user.get();
		
		if(realtorUser.getRealtorPwd().equals(realtorDTO.getRealtorPwd())) {
			
			System.out.println("일치일치..");
		
			httpSession.setAttribute("realtorId", realtorUser.getRealtorId());
		
		}
		
		mav.setViewName("redirect:/newzips/realtor");

		return mav;
		
	}

	
	@GetMapping("/realtor/logout")
	public ModelAndView logout(HttpServletRequest request) throws Exception{
		
		String realtorId = (String)httpSession.getAttribute("realtorId");
		
		System.out.println("=============");
		System.out.println(realtorId);
		
		httpSession.invalidate();
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/newzips/realtor");

		return mav;
		
	}
	*/
	
	@PostMapping("/realtor/findId")
	public ModelAndView findId(HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		String realtorName = request.getParameter("realtorName");
		String realtorPhone = request.getParameter("realtorPhone");
		
		System.out.println("==================================");
		System.out.println("findId");
		
		System.out.println(realtorName);
		System.out.println(realtorPhone);
		
		String realtorId = null;
		
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("realtorName", realtorName);
		params.put("realtorPhone", realtorPhone);
		
		try {
			realtorId = accountRealtorService.findId(params);
			System.out.println("=================");
			System.out.println(realtorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("realtorId", realtorId);
		
		return mav;
		
	}
	
	
}
