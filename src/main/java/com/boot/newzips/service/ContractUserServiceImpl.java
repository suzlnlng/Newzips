package com.boot.newzips.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.mapper.ContractUserMapper;

@Service
public class ContractUserServiceImpl implements ContractUserService{

	@Autowired
	private ContractUserMapper contractUserMapper;
	
	@Override
	public ListingDTO getListing(String itemId) throws Exception {
		return contractUserMapper.getListing(itemId);
	}

}
