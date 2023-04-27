package com.boot.newzips.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;

@Mapper
public interface ReservationUserMapper {
	
		// �湮�� ����
		public void insertReservation(VisitorReservDTO dto) throws Exception;
		
		// ���� ������ ��¥ ��ȸ
		public String selectAvailableDate(Map<String, Object> map) throws Exception;
		
		// ���� ������ �ð� ��ȸ
		public String selectAvailableTime(Map<String, Object> map) throws Exception;
		
		// �����ȣ�� ��ȸ
		public VisitorReservDTO selectReservationReservNo(String reservNo) throws Exception;
		
		// �������̵�� ��ȸ
		public List<VisitorReservDTO> selectReservationUserId(String userId) throws Exception;
	
		//itemId�� ��ȸ
		public VisitorReservDTO selectReservationItemId(String itemId) throws Exception;
		
		//�� ����
		public RoomInfoDTO getReservationRoomInfo(String itemId) throws Exception;
		
		// ����
		public void deleteReservation(String reservNo) throws Exception;
}
