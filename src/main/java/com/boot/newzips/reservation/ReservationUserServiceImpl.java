package com.boot.newzips.reservation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationUserServiceImpl implements ReservationUserService {

	@Autowired
	public final ReservationUserMapper reservationUserMapper;

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
	public List<VisitorReservDTO> selectReservationUserId(String userId) throws Exception {
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

	@Override
	public List<RealtorDTO> getRealtorInfoByRealtorId() throws Exception {
		return reservationUserMapper.getRealtorInfoByRealtorId();
	}

	@Override
	public RealtorDTO getRealtorInfo(String realtorId) throws Exception {
		return reservationUserMapper.getRealtorInfo(realtorId);
	}

	@Override
	public List<ReservationStatusDTO> getReservationList(String userId) throws Exception {
		return reservationUserMapper.getReservationList(userId);
	}

	@Override
	public String getItemIdByUserId(String userId) throws Exception {
		return reservationUserMapper.getItemIdByUserId(userId);
	}

	@Override
	public int maxNum() throws Exception {
		return reservationUserMapper.maxNum();
	}

}
