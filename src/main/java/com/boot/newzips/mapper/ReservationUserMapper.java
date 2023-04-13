package com.boot.newzips.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.VisitorReservDTO;

@Mapper
public interface ReservationUserMapper {
	
		// 방문자 예약
		public void insertReservation(VisitorReservDTO dto) throws Exception;
		
		// 예약 가능한 날짜 조회
		public String selectAvailableDate(Map<String, Object> map) throws Exception;
		
		// 예약 가능한 시간 조회
		public String selectAvailableTime(Map<String, Object> map) throws Exception;
		
		// 예약번호로 조회
		public VisitorReservDTO selectReservationReservNo(int reservNo) throws Exception;
		
		// 유저아이디로 조회
		public VisitorReservDTO selectReservationUserId(String userId) throws Exception;
		
		// 삭제
		public void deleteReservation(int reservNo) throws Exception;
}
