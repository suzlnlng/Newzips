package com.boot.newzips.itemList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.ListingDTO;

import com.boot.newzips.dto.WolseListingDTO;

@Mapper
public interface ItemListUserMapper {

    public List<ListingDTO> YMListingDTO(String itemId) throws Exception;

    public List<WolseListingDTO> getread_wolse(String itemId) throws Exception;

    public List<JunsaeListingDTO> getread_junsae(String itemId) throws Exception;

    public List<ListAllDTO> getreadDataAll(Map<String, Object> params) throws Exception;
    
    public List<ListAllDTO> getreadDatapage() throws Exception;
    
    public List<ListAllDTO> getreadlist();

	public List<ListAllDTO> getsearchlist(Map<String, Object> params) throws Exception;
}
	
	


