package com.boot.newzips.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.ContractDTO;
import com.boot.newzips.dto.ContractUserDTO;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.WolseListingDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContractUserServiceImpl implements ContractUserService{
	
	@Autowired
	public ContractUserMapper contractUserMapper;

	@Override
	public List<ContractUserDTO> getContract(String userId) throws Exception {
		return contractUserMapper.getContract(userId);
	}

	@Override
	public MemberDTO getUser(String userId) throws Exception {
		return contractUserMapper.getUser(userId);
	}

	@Override
	public ListingDTO getListing(String itemId) throws Exception {
		return contractUserMapper.getListing(itemId);
	}

	@Override
	public WolseListingDTO getWolse(String itemId) throws Exception {
		return contractUserMapper.getWolse(itemId);
	}

	@Override
	public JunsaeListingDTO getJunsae(String itemId) throws Exception {
		return contractUserMapper.getJunsae(itemId);
	}

	@Override
	public RealtorDTO getRealtor(String city) {
		return contractUserMapper.getRealtor(city);
	}

	

}
