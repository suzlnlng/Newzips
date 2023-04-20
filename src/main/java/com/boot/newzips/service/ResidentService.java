package com.boot.newzips.service;

import com.boot.newzips.dto.ResidenceReservDTO;

public interface ResidentService {
	
public void insertResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public ResidenceReservDTO selectResidenceReservUserId(String userId) throws Exception;
	
	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception;

}
