package com.boot.newzips.reservation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.ResidenceReservDTO;

@Mapper
public interface ReservationResidentMapper {

	public void insertResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public void updateResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public List<ResidenceReservDTO> selectResidenceReservUserId(String userId) throws Exception;
	
	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public List<ResidenceReservDTO> selectAvailableTimes(Map<String, Object> params) throws Exception;

}
