package com.boot.newzips.account;

import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.MemberDTO;

@Mapper
public interface AccountUserMapper {
	
	public void createMember(MemberDTO memberDTO) throws Exception;
	
	public void createOauthMember(MemberDTO memberDTO) throws Exception;
	
	public void updateMember(MemberDTO memberDTO) throws Exception;
	
	public Optional<MemberDTO> getUserById(String userId) throws Exception;
	
	public Optional<MemberDTO> getUserByEmail(String userEmail) throws Exception;
	
	public String findId(Map<String, Object> params) throws Exception;
	
	public String findPwd(Map<String, Object> params) throws Exception;
	
	public boolean checkId(String userId) throws Exception;

}
