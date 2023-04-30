package com.boot.newzips.reservation;

import java.util.List;
import java.util.Map;

import com.boot.newzips.dto.ResidenceReservDTO;

public interface ResidentService {

	public void insertResidentReserv(ResidenceReservDTO dto) throws Exception;

	public void updateResidentReserv(ResidenceReservDTO dto) throws Exception;

	public List<ResidenceReservDTO> selectResidenceReservUserId(String userId) throws Exception;

	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public List<ResidenceReservDTO> selectAvailableTimes(Map<String, Object> params) throws Exception;

}
