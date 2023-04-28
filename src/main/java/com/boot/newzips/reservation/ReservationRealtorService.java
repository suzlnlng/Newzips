package com.boot.newzips.reservation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.ReservInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.dto.WolseListingDTO;

public interface ReservationRealtorService {

	
	public List<ReservInfoDTO> getReserverInfo(@Param("realtorId")String realtorId) throws Exception;
	
	public ListingDTO getItemInfo(String itemId) throws Exception;
	
	public JunsaeListingDTO getJunsaeInfo(String itemId) throws Exception;
	public WolseListingDTO getWolseInfo(String itemId) throws Exception;
	
	public MemberDTO getMemberInfo(String userId) throws Exception;
	
	public void setConfirmed(String userId,String realtorId,String itemId) throws Exception;
	public ReservInfoDTO getConfirmedInfo(String userId,String realtorId, String itemId) throws Exception;
	
}
