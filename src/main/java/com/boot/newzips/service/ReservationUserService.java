package com.boot.newzips.service;

import com.boot.newzips.dto.VisitorReservDTO;

public interface ReservationUserService {
	
public void insertReservationUser(VisitorReservDTO dto) throws Exception;
	
	public VisitorReservDTO selectReservationUser(int userId) throws Exception;
	
	public void deleteReservationUser(int userId) throws Exception;

}
