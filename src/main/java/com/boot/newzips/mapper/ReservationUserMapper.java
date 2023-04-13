package com.boot.newzips.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.VisitorReservDTO;

@Mapper
public interface ReservationUserMapper {
	
	public int maxReservNo() throws Exception;

	public void insertReservationUser(VisitorReservDTO dto) throws Exception;
	
	public VisitorReservDTO selectReservationUser(int userId) throws Exception;
	
	public void deleteReservationUser(int userId) throws Exception;
}
