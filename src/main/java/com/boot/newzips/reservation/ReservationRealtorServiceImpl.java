package com.boot.newzips.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.ReservInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.dto.WolseListingDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationRealtorServiceImpl implements ReservationRealtorService{

	
	private final ReservationRealtorMapper reservationRealtorMapper;
	
	
	@Override
	public List<ReservInfoDTO> getReserverInfo(String realtorId) throws Exception {

		return reservationRealtorMapper.getReserverInfo(realtorId);
	}

	@Override
	public ListingDTO getItemInfo(String itemId) throws Exception {
		return reservationRealtorMapper.getItemInfo(itemId);
	}

	@Override
	public JunsaeListingDTO getJunsaeInfo(String itemId) throws Exception {
		return reservationRealtorMapper.getJunsaeInfo(itemId);
	}

	@Override
	public WolseListingDTO getWolseInfo(String itemId) throws Exception {
		return reservationRealtorMapper.getWolseInfo(itemId);
	}

	@Override
	public MemberDTO getMemberInfo(String userId) throws Exception {
		return reservationRealtorMapper.getMemberInfo(userId);
	}

	@Override
	public void setConfirmed(String userId, String realtorId, String itemId) throws Exception {
		reservationRealtorMapper.setConfirmed(userId, realtorId, itemId);

	}

	@Override
	public ReservInfoDTO getConfirmedInfo(String userId, String realtorId, String itemId) throws Exception {
		return reservationRealtorMapper.getConfirmedInfo(userId, realtorId,itemId);
	}
	
	

	
	
}
