package com.boot.newzips.reservation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.ResidenceReservDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ResidentServiceImpl implements ResidentService {

	@Autowired
	public ReservationResidentMapper reservationResidentMapper;

	@Override
	public void insertResidentReserv(ResidenceReservDTO dto) throws Exception {
		reservationResidentMapper.insertResidentReserv(dto);
	}

	@Override
	public void updateResidentReserv(ResidenceReservDTO dto) throws Exception {
		reservationResidentMapper.updateResidentReserv(dto);
	}

	@Override
	public List<ResidenceReservDTO> selectResidenceReservUserId(String userId) throws Exception {
		return reservationResidentMapper.selectResidenceReservUserId(userId);
	}

	@Override
	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception {
		reservationResidentMapper.deleteResidentReserv(dto);

	}

	@Override
	public List<ResidenceReservDTO> selectAvailableTimes(Map<String, Object> params) throws Exception {
		System.out.println("========================");
		System.out.println(params.get("availableDate"));
		return reservationResidentMapper.selectAvailableTimes(params);
	}

}
