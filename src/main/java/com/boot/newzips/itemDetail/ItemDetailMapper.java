package com.boot.newzips.itemDetail;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.BuildingInfoDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RoomInfoDTO;

@Mapper
public interface ItemDetailMapper {
	
	public RoomInfoDTO getReadData_roomInfo(String itemId) throws Exception;
	
	public BuildingInfoDTO getReadData_buildingInfo(String itemId) throws Exception;
	
	public ListingDTO getReadData_listing(String itemId) throws Exception;

}
