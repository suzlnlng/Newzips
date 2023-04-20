package com.boot.newzips.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.ResidenceReservDTO;
import com.boot.newzips.mapper.ReservationResidentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ResidentServiceImpl implements ResidentService{

	@Autowired
	public ReservationResidentMapper reservationResidentMapper;

	@Override
	public void insertResidentReserv(ResidenceReservDTO dto) throws Exception {
		reservationResidentMapper.insertResidentReserv(dto);
			}

	@Override
	public ResidenceReservDTO selectResidenceReservUserId(String userId) throws Exception {
		return reservationResidentMapper.selectResidenceReservUserId(userId);
	}

	@Override
	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception {
		reservationResidentMapper.deleteResidentReserv(dto);
		
	}
	
	
	

}
