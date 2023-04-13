package com.boot.newzips.account;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void createMember(MemberDTO dto) throws Exception {
		
		dto.setUserPwd(passwordEncoder.encode(dto.getUserPwd()));
		
		accountUserMapper.createMember(dto);
		
	}

	@Override
	public void updateMember(MemberDTO dto) throws Exception {
		accountUserMapper.updateMember(dto);
	}
	
	
	

}
