package com.boot.newzips.suggest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.VisitorReservDTO;

@Mapper
public interface SuggestUserMapper {

	public String getUserPhone(String userId) throws Exception;
	
	public void setSuggestionInfo(@Param("userId") String userId,@Param("userPhone")String userPhone,@Param("addrCity")String addrCity,
			@Param("addrGu") String addrGu,@Param("yearly_monthly")String yearly_monthly,@Param("deposit")Integer deposit, 
			@Param("monthlyFee")Integer monthlyFee,@Param("rooms")Integer rooms, @Param("area")Integer area,
			@Param("maintenanceCost") Integer maintenanceCost) throws Exception;
	
	public SuggestionDTO getUserInfo(String userId) throws Exception;
	
	public void updateOption(SuggestionDTO suggestion) throws Exception;
	
	
	
	
	}
	
