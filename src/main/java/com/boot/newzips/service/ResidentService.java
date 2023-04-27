package com.boot.newzips.service;

import java.util.List;

import com.boot.newzips.dto.ResidenceReservDTO;

public interface ResidentService {
	
	public void insertResidentReserv(ResidenceReservDTO dto) throws Exception;

	public void updateResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public List<ResidenceReservDTO> selectResidenceReservUserId(String userId) throws Exception;
	
	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception;

}
