package com.boot.newzips.itemDetail;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.BuildingInfoDTO;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.WolseListingDTO;

@Mapper
public interface ItemDetailMapper {
	
	public RoomInfoDTO getReadData_roomInfo(String itemId) throws Exception;
	
	public BuildingInfoDTO getReadData_buildingInfo(String itemId) throws Exception;
	
	public ListingDTO getReadData_listing(String itemId) throws Exception;
	
	public JunsaeListingDTO getReadData_jun(String itemId) throws Exception;
	
	public WolseListingDTO getReadData_wol(String itemId) throws Exception;

}
