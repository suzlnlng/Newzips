package com.boot.newzips.itemList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.WolseListingDTO;

@Service
public class ItemListUserServiceImpl implements ItemListUserService {

    @Autowired
    private ItemListUserMapper itemListUserMapper;

    @Override
    public List<ListingDTO> YMListingDTO(String itemId) throws Exception {
        return itemListUserMapper.YMListingDTO(itemId);
    }

    @Override
    public List<WolseListingDTO> getread_wolse(String itemId) throws Exception {
        return itemListUserMapper.getread_wolse(itemId);
    }

    @Override
    public List<JunsaeListingDTO> getread_junsae(String itemId) throws Exception {
        return itemListUserMapper.getread_junsae(itemId);
    }

    @Override
    public List<ListAllDTO> getreadDatapage() throws Exception {
        return itemListUserMapper.getreadDatapage();
    }

	@Override
	public List<ListAllDTO> getreadDataAll(Map<String, Object> params) throws Exception {
		return itemListUserMapper.getreadDataAll(params);
	}
	
	@Override
	public List<ListAllDTO> getsearchlist(Map<String, Object> params) throws Exception {
		return itemListUserMapper.getsearchlist(params);
	}
	
	
}
