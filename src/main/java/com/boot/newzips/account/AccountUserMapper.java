package com.boot.newzips.account;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.MemberDTO;

@Mapper
public interface AccountUserMapper {
	
	public void createMember(MemberDTO memberDTO) throws Exception;
	
	public void updateMember(MemberDTO memberDTO) throws Exception;
	
	public Optional<LoginForm> getUser(String userId) throws Exception;

}
