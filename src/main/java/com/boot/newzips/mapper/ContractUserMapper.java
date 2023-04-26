package com.boot.newzips.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.ListingDTO;

@Mapper
public interface ContractUserMapper {
	
	public ListingDTO getListing(String itemId) throws Exception;

}
