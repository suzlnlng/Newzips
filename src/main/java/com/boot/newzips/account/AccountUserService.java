package com.boot.newzips.account;

import com.boot.newzips.dto.MemberDTO;

public interface AccountUserService {

	public void createMember(MemberDTO dto) throws Exception;
	
	public void updateMember(MemberDTO dto) throws Exception;
}
