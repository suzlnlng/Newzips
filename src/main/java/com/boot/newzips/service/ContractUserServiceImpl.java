package com.boot.newzips.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.mapper.contractUserMapper;

@Service
public class ContractUserServiceImpl implements ContractUserService{

	@Autowired
	private contractUserMapper contractUserMapper;
	
	@Override
	public ListingDTO getListing(String itemId) throws Exception {
		return contractUserMapper.get
	}

}
