package com.boot.newzips.service;

import com.boot.newzips.dto.ResidenceReservDTO;

public interface ResidentService {
	
public void insertResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public ResidenceReservDTO selectResidentReserv(String userId, String itemId) throws Exception;
	
	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception;

}
