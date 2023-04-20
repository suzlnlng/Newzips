package com.boot.newzips.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.mapper.ReservationUserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationUserServiceImpl implements ReservationUserService{
	
	
	@Autowired
	public ReservationUserMapper reservationUserMapper;

	@Override
	public void insertReservation(VisitorReservDTO dto) throws Exception {
		reservationUserMapper.insertReservation(dto);	
	}

	@Override
	public String selectAvailableDate(Map<String, Object> map) throws Exception {
		return reservationUserMapper.selectAvailableDate(map);
	}

	@Override
	public String selectAvailableTime(Map<String, Object> map) throws Exception {
		return reservationUserMapper.selectAvailableTime(map);
	}

	@Override
	public VisitorReservDTO selectReservationReservNo(String reservNo) throws Exception {
		return reservationUserMapper.selectReservationReservNo(reservNo);
	}

	@Override
	public VisitorReservDTO selectReservationUserId(String userId) throws Exception {
		return reservationUserMapper.selectReservationUserId(userId);
	}
	
	@Override
	public VisitorReservDTO selectReservationItemId(String itemId) throws Exception {
		return reservationUserMapper.selectReservationItemId(itemId);
	}

	@Override
	public RoomInfoDTO getReservationRoomInfo(String itemId) throws Exception {
		return reservationUserMapper.getReservationRoomInfo(itemId);
	}

	@Override
	public void deleteReservation(String reservNo) throws Exception {
		reservationUserMapper.deleteReservation(reservNo);
		
	}

	

	

	
	
	
	

	

}
