package com.boot.newzips.suggest;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RealtorSuggestionDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.WolseListingDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuggestRealtorServiceImpl implements SuggestRealtorService{
	
	private final SuggestRealtorMapper suggestRealtorMapper;
	
	@Override
	public List<SuggestionDTO> getSuggestList() throws Exception {
		return suggestRealtorMapper.getSuggestList();
	}
	
	@Override
	public SuggestionDTO getSuggestInfo(String userId) throws Exception {
		return suggestRealtorMapper.getSuggestInfo(userId);
	}

	@Override
	public List<RealtorSuggestionDTO> getRealtorSuggestion(String realtorId) throws Exception {
		return suggestRealtorMapper.getRealtorSuggestion(realtorId);
	}
	
	@Override
	public ListingDTO getItemInfo(String itemId) throws Exception {
		return suggestRealtorMapper.getItemInfo(itemId);
	}

	@Override
	public JunsaeListingDTO getJunsaeInfo(String itemId) throws Exception {
		return suggestRealtorMapper.getJunsaeInfo(itemId);
	}

	@Override
	public WolseListingDTO getWolseInfo(String itemId) throws Exception {
		return suggestRealtorMapper.getWolseInfo(itemId);
	}

	@Override
	public List<ListingDTO> getSuggestItem(String addrCity, String addrGu) throws Exception {
		return suggestRealtorMapper.getSuggestItem(addrCity, addrGu);
	}

	@Override
	public RoomInfoDTO getRoomInfo(String itemId) throws Exception {
		return suggestRealtorMapper.getRoomInfo(itemId);
	}

	@Override
	public void insertSuggestion(RealtorSuggestionDTO dto) throws Exception {
		suggestRealtorMapper.insertSuggestion(dto);
	}

	
}
