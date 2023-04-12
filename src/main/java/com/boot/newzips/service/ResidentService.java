package com.boot.newzips.service;

import com.boot.newzips.dto.ResidenceReservDTO;

public interface ResidentService {
	
	public void insertReservation(ResidenceReservDTO dto) throws Exception;
	
	public ResidenceReservDTO selectReservation(int userId) throws Exception;
	
	public void deleteReservation(int userId) throws Exception;

}
