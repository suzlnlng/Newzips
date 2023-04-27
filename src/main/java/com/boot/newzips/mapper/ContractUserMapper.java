package com.boot.newzips.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.ContractDTO;
import com.boot.newzips.dto.ContractUserDTO;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.WolseListingDTO;

@Mapper
public interface contractUserMapper {
	
	public List<ContractUserDTO> getContract(String userId) throws Exception;

	//public ContractDTO test(String userId) throws Exception;
	
	public MemberDTO getUser(String userId) throws Exception;
	
	public ListingDTO getListing(String itemId) throws Exception;
	
	public WolseListingDTO getWolse(String itemId) throws Exception;
	
	public JunsaeListingDTO getJunsae (String itemId) throws Exception;
}
