package com.boot.newzips.reservation;

import java.util.List;
import java.util.Map;

import com.boot.newzips.dto.RealtorDTO;
import com.boot.newzips.dto.ReservationStatusDTO;
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

	public RealtorDTO getRealtorInfo(String realtorId) throws Exception;

	public void deleteReservation(String reservNo) throws Exception;

	public List<RealtorDTO> getRealtorInfoByRealtorId() throws Exception;

	public List<ReservationStatusDTO> getReservationList(String userId) throws Exception;

	public String getItemIdByUserId(String userId) throws Exception;

	public int maxNum() throws Exception;

}
