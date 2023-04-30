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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountUserServiceImpl implements AccountUserService {
	
	@Autowired
	private AccountUserMapper accountUserMapper;

	private final PasswordEncoder passwordEncoder;

	@Override
	public void createMember(MemberDTO memberDTO) throws Exception {

		memberDTO.setUserPwd(passwordEncoder.encode(memberDTO.getUserPwd()));
		memberDTO.setEmailReceive("T");
		memberDTO.setUserRole("USER");

		accountUserMapper.createMember(memberDTO);
		
	}
	
	@Override
	public void createOauthMember(MemberDTO memberDTO) throws Exception {
		
		memberDTO.setUserPwd(passwordEncoder.encode(memberDTO.getUserPwd()));
		
		accountUserMapper.createOauthMember(memberDTO);
		
	}
	

	@Override
	public void updateMember(MemberDTO dto) throws Exception {
		accountUserMapper.updateMember(dto);
	}

	
	@Override
	public Optional<MemberDTO> getUserById(String userId) throws Exception {
		
		Optional<MemberDTO> searchUser = accountUserMapper.getUserById(userId);
		
		if(searchUser.isPresent()) {
			return searchUser;
		}else {
			System.out.println("유저 없음");
			throw new Exception("User Not Found!!");
		}
		
	}

	
	@Override
	public Optional<MemberDTO> getUserByEmail(String userEmail) throws Exception {
		
		Optional<MemberDTO> searchUser = accountUserMapper.getUserByEmail(userEmail);
		
		if(searchUser.isPresent()) {
			return searchUser;
		}else {
			System.out.println("유저 없음");
			throw new Exception("User Not Found!!");
		}
		
	}

	@Override
	public String findId(Map<String, Object> params) throws Exception {
		return accountUserMapper.findId(params);
	}

	@Override
	public boolean checkId(String userId) throws Exception {
		
		Optional<MemberDTO> searchUser = accountUserMapper.getUserById(userId);
		
		if (searchUser.isPresent()) 
			return true;
		else
			return false;

	}

	@Override
	public String findPwd(Map<String, Object> params) throws Exception {
		return accountUserMapper.findPwd(params);
	}




}
