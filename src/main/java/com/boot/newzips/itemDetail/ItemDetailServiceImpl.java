package com.boot.newzips.itemDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.BuildingInfoDTO;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.WolseListingDTO;

@Service
public class ItemDetailServiceImpl implements ItemDetailService{
	
	@Autowired
	private ItemDetailMapper itemdetailMapper;

	@Override
	public RoomInfoDTO getReadData_roomInfo(String itemId) throws Exception {
		
		return itemdetailMapper.getReadData_roomInfo(itemId);
	}

	@Override
	public BuildingInfoDTO getReadData_buildingInfo(String itemId) throws Exception {
		
		return itemdetailMapper.getReadData_buildingInfo(itemId);
	}

	@Override
	public ListingDTO getReadData_listing(String itemId) throws Exception {

		return itemdetailMapper.getReadData_listing(itemId);
	}

	@Override
	public JunsaeListingDTO getReadData_jun(String itemId) throws Exception {
		
		return itemdetailMapper.getReadData_jun(itemId);
	}

	@Override
	public WolseListingDTO getReadData_wol(String itemId) throws Exception {
		
		return itemdetailMapper.getReadData_wol(itemId);
	}

}
