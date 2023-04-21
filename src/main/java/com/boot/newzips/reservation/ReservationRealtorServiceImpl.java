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
	public List<ReservInfoDTO> getReserverInfo() throws Exception {

		return reservationRealtorMapper.getReserverInfo();
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
	
	

	
	
}
