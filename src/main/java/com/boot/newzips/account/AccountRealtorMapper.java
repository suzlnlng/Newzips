package com.boot.newzips.account;

import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.RealtorDTO;

@Mapper
public interface AccountRealtorMapper {
	
	public void createMember(RealtorDTO realtorDTO) throws Exception;
	
	public void updateMember(RealtorDTO realtorDTO) throws Exception;
	
	public Optional<RealtorDTO> getUserById(String userId) throws Exception;
	
	public Optional<RealtorDTO> getUserByEmail(String userEmail) throws Exception;
	
	public String findId(Map<String, Object> params) throws Exception;
	
	public boolean checkId(String userId) throws Exception;

}
