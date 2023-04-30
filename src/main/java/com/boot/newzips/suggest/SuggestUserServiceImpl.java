package com.boot.newzips.suggest;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.newzips.dto.RealtorSuggestionDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
import com.boot.newzips.dto.SuggestionDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuggestUserServiceImpl implements SuggestUserService{

	private final SuggestUserMapper suggestUserMapper;
	
	@Override
	public String getUserPhone(String userId) throws Exception {
		return suggestUserMapper.getUserPhone(userId);
	}

	@Override
	public void setSuggestionInfo(String userId, String userPhone, String addrCity, String addrGu,
			String yearly_monthly, Integer deposit, Integer monthlyFee, Integer rooms, Integer area,
			Integer maintenanceCost) throws Exception {
		
		suggestUserMapper.setSuggestionInfo(userId, userPhone, addrCity, addrGu, yearly_monthly, deposit, monthlyFee, rooms, area, maintenanceCost);
		
	}

	@Override
	public SuggestionDTO getUserInfo(String userId) throws Exception {
		return suggestUserMapper.getUserInfo(userId);
		
	}

	@Override
	public void updateOption(SuggestionDTO suggestion) throws Exception {
		suggestUserMapper.updateOption(suggestion);
		
	}

	@Override
	public List<RealtorSuggestionDTO> getSuggestionList(String userId) throws Exception {
		return suggestUserMapper.getSuggestionList(userId);
	}

}
