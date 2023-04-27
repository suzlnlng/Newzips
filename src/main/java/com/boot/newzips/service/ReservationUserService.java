package com.boot.newzips.service;

import java.util.List;
import java.util.Map;

import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;

public interface ReservationUserService {
	
	
			public void insertReservation(VisitorReservDTO dto) throws Exception;
			
			public String selectAvailableDate(Map<String, Object> map) throws Exception;
			
			public String selectAvailableTime(Map<String, Object> map) throws Exception;
			
			public VisitorReservDTO selectReservationReservNo(String reservNo) throws Exception;
			
			public List<VisitorReservDTO> selectReservationUserId(String userId) throws Exception;
			
			public VisitorReservDTO selectReservationItemId(String itemId) throws Exception;

			public RoomInfoDTO getReservationRoomInfo(String itemId) throws Exception;
			
			public void deleteReservation(String reservNo) throws Exception;

}
