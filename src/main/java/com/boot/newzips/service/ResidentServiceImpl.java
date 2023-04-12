package com.boot.newzips.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.ResidenceReservDTO;
import com.boot.newzips.mapper.ResidentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ResidentServiceImpl implements ResidentService{

	@Autowired
	public ResidentMapper residentMapper;
	
	@Override
	public void insertReservation(ResidenceReservDTO dto) throws Exception {
		residentMapper.insertReservation(dto);
		
	}

	@Override
	public ResidenceReservDTO selectReservation(int userId) throws Exception {
		return residentMapper.selectReservation(userId);
	}

	@Override
	public void deleteReservation(int userId) throws Exception {
		residentMapper.deleteReservation(userId);
		
	}
	

}
