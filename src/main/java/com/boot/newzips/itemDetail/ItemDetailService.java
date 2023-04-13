package com.boot.newzips.itemDetail;

import com.boot.newzips.dto.BuildingInfoDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RoomInfoDTO;

public interface ItemDetailService {
	
	public RoomInfoDTO getReadData_roomInfo(String itemId) throws Exception;
	
	public BuildingInfoDTO getReadData_buildingInfo(String itemId) throws Exception;
	
	public ListingDTO getReadData_listing(String itemId) throws Exception;

}
