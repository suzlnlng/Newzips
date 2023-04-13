package com.boot.newzips.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.ResidenceReservDTO;

@Mapper
public interface ResidentMapper {
	
	
	
	public void insertReservation(ResidenceReservDTO dto) throws Exception;
	
	public ResidenceReservDTO selectReservation(int userId) throws Exception;

	public void deleteReservation(int userId) throws Exception;
}
