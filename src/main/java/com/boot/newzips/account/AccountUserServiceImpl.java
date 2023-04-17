package com.boot.newzips.account;

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
		
		System.out.println("service");		

		memberDTO.setUserPwd(passwordEncoder.encode(memberDTO.getUserPwd()));
		memberDTO.setUserRole("USER");
		memberDTO.setMarketing("T");//제거하기

		accountUserMapper.createMember(memberDTO);
		
	}

	@Override
	public void updateMember(MemberDTO dto) throws Exception {
		accountUserMapper.updateMember(dto);
	}


	@Override
	public Optional<LoginForm> getUser(String userId) throws Exception {
		
		Optional<LoginForm> searchUser = accountUserMapper.getUser(userId);
		
		if(!searchUser.isPresent()) {
			return searchUser;
		}else {
			System.out.println("유저 없음");
			throw new Exception("User Not Found!!");
		}
		
	}
	


}
