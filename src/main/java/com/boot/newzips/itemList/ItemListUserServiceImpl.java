package com.boot.newzips.itemList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boot.newzips.dto.ListingDTO;

public class ItemListUserServiceImpl implements itemListUserService {
	
	@Autowired
	private ItemListUserMapper itemListUserMapper;
	
	public List<ListingDTO> selectListingDTO() {
	    
	    List<ListingDTO> listingDTOList = new ArrayList<>(); //빈 배열 객체를 만들고 listingDTOList에 집어넣음
	    List<ListingDTO> listingList = itemListUserMapper.selectListingDTO(); //selectListingDTO를 통해 조회된 값들이  List<ListingDTO>형태로 반환되고 listingList에 들어간다
	    for (ListingDTO listingDTO : listingList) {  //listingList안에 있는 listingDTO로 반복문을 돌려서 convertedListingDTO에 넣는다
	        ListingDTO convertedListingDTO = new ListingDTO();
	     
	        // ...
	        listingDTOList.add(convertedListingDTO); //convertedListingDTO를 아까만들어둔 빈객체(listingDTOList)에 넣는다
	    }
	    return listingDTOList;
	}
}
