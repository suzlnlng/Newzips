package com.boot.newzips.account;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.MemberDTO;

@Mapper
public interface AccountUserMapper {
	
	public void createMember(MemberDTO dto) throws Exception;
	
	public void updateMember(MemberDTO dto) throws Exception;

}
