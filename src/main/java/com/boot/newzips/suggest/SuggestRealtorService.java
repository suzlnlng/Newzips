package com.boot.newzips.suggest;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RealtorSuggestionDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.WolseListingDTO;


public interface SuggestRealtorService {

	public List<SuggestionDTO> getSuggestList() throws Exception;
	
	public SuggestionDTO getSuggestInfo(String userId) throws Exception;
	public List<RealtorSuggestionDTO> getRealtorSuggestion(String realtorId) throws Exception;
	
	public ListingDTO getItemInfo(String itemId) throws Exception;
	public List<ListingDTO> getSuggestItem(@Param("addrCity") String addrCity, @Param("addrGu") String addrGu) throws Exception;
	
	public JunsaeListingDTO getJunsaeInfo(String itemId) throws Exception;
	public WolseListingDTO getWolseInfo(String itemId) throws Exception;
	
	public RoomInfoDTO getRoomInfo(String itemId) throws Exception;
	
	
}
