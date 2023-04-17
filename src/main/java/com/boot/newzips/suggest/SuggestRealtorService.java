package com.boot.newzips.suggest;

import java.util.List;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.WolseListingDTO;


public interface SuggestRealtorService {

	public List<SuggestionDTO> getSuggestInfo() throws Exception;
	
	public ListingDTO getItemInfo(String itemId) throws Exception;
	
	public JunsaeListingDTO getJunsaeInfo(String itemId) throws Exception;
	public WolseListingDTO getWolseInfo(String itemId) throws Exception;
	
	
}
