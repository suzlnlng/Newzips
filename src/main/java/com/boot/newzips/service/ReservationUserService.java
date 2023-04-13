package com.boot.newzips.service;

import java.util.Map;

import com.boot.newzips.dto.VisitorReservDTO;

public interface ReservationUserService {
	
	
			public void insertReservation(VisitorReservDTO dto) throws Exception;
			
			public String selectAvailableDate(Map<String, Object> map) throws Exception;
			
			public String selectAvailableTime(Map<String, Object> map) throws Exception;
			
			public VisitorReservDTO selectReservationReservNo(int reservNo) throws Exception;
			
			public VisitorReservDTO selectReservationUserId(String userId) throws Exception;
			
			public void deleteReservation(int reservNo) throws Exception;

}
