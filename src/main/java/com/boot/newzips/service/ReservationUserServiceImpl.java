package com.boot.newzips.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.mapper.ReservationUserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationUserServiceImpl implements ReservationUserService{
	
	
	@Autowired
	public ReservationUserMapper reservationUserMapper;
	
	@Override
	public int maxReservNo() throws Exception {
		return reservationUserMapper.maxReservNo();
	}
	
	@Override
	public void insertReservationUser(VisitorReservDTO dto) throws Exception {
		reservationUserMapper.insertReservationUser(dto);
		
	}

	@Override
	public VisitorReservDTO selectReservationUser(int userId) throws Exception {
		return reservationUserMapper.selectReservationUser(userId);
	}

	@Override
	public void deleteReservationUser(int userId) throws Exception {
		reservationUserMapper.deleteReservationUser(userId);
		
	}

	

	

}
