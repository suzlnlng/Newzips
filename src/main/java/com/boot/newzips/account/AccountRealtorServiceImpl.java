package com.boot.newzips.account;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.RealtorDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountRealtorServiceImpl implements AccountRealtorService {
	
	@Autowired
	private AccountRealtorMapper accountRealtorMapper;

	private final PasswordEncoder realtorPasswordEncoder;

	@Override
	public void createMember(RealtorDTO realtorDTO) throws Exception {

		//realtorDTO.setRealtorPwd(passwordEncoder.encode(realtorDTO.getRealtorPwd()));
		realtorDTO.setRealtorAddr(realtorDTO.getRealtorAddr() + " " + realtorDTO.getRealtorDetailedAddr());

		accountRealtorMapper.createMember(realtorDTO);
		
	}
	

	@Override
	public void updateMember(RealtorDTO realtorDTO) throws Exception {
		accountRealtorMapper.updateMember(realtorDTO);
	}

	
	@Override
	public Optional<RealtorDTO> getUserById(String userId) throws Exception {
		
		Optional<RealtorDTO> searchUser = accountRealtorMapper.getUserById(userId);
		
		if(searchUser.isPresent()) {
			return searchUser;
		}else {
			System.out.println("유저 없음");
			throw new Exception("User Not Found!!");
		}
		
	}

	
	@Override
	public Optional<RealtorDTO> getUserByEmail(String userEmail) throws Exception {
		
		Optional<RealtorDTO> searchUser = accountRealtorMapper.getUserByEmail(userEmail);
		
		if(!searchUser.isPresent()) {
			return searchUser;
		}else {
			System.out.println("유저 없음");
			throw new Exception("User Not Found!!");
		}
		
	}

	@Override
	public String findId(Map<String, Object> params) throws Exception {
		return accountRealtorMapper.findId(params);
	}

	@Override
	public boolean checkId(String userId) throws Exception {
		
		Optional<RealtorDTO> searchUser = accountRealtorMapper.getUserById(userId);
		
		if (searchUser.isPresent()) 
			return true;
		else
			return false;

	}




}
