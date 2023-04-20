package com.boot.newzips.account;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.boot.newzips.dto.MemberDTO;

public interface AccountUserService {

	public void createMember(MemberDTO memberDTO) throws Exception;
	
	public void updateMember(MemberDTO memberDTO) throws Exception;
	
	public Optional<LoginForm> getUser(String userId) throws Exception;

	
}
