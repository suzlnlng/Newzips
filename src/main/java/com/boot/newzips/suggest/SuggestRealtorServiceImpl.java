package com.boot.newzips.suggest;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.WolseListingDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuggestRealtorServiceImpl implements SuggestRealtorService{
	
	private final SuggestRealtorMapper suggestRealtorMapper;
	
	@Override
	public List<SuggestionDTO> getSuggestInfo() throws Exception {
		return suggestRealtorMapper.getSuggestInfo();
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

}
