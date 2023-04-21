package com.boot.newzips.reservation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.ReservInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.dto.WolseListingDTO;

@Mapper
public interface ReservationRealtorMapper {


	public List<ReservInfoDTO> getReserverInfo() throws Exception;
	
	public ListingDTO getItemInfo(String itemId) throws Exception;
	
	public JunsaeListingDTO getJunsaeInfo(String itemId) throws Exception;
	public WolseListingDTO getWolseInfo(String itemId) throws Exception;
	
	public MemberDTO getMemberInfo(String userId) throws Exception;

}
