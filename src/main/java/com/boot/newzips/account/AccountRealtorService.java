package com.boot.newzips.account;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.boot.newzips.dto.RealtorDTO;

public interface AccountRealtorService {

	public void createMember(RealtorDTO realtorDTO) throws Exception;
	
	public void updateMember(RealtorDTO realtorDTO) throws Exception;
	
	public Optional<RealtorDTO> getUserById(String userId) throws Exception;
	
	public Optional<RealtorDTO> getUserByEmail(String userEmail) throws Exception;
	
	public String findId(Map<String, Object> params) throws Exception;
	
	public boolean checkId(String userId) throws Exception;

}
