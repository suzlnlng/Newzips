package com.boot.newzips.suggest;

import com.boot.newzips.dto.SuggestionDTO;

public interface SuggestUserService {

	public String getUserPhone(String userId) throws Exception;
	
	public void setSuggestionInfo(String userId,String userPhone,String addrCity,String addrGu,
			String yearly_monthly,Integer deposit, Integer monthlyFee,Integer rooms, Integer area, Integer maintenanceCost)
	throws Exception;
	
	public SuggestionDTO getUserInfo(String userId) throws Exception;
	
	public void updateOption(SuggestionDTO suggestion) throws Exception;
	
	
	
}
