package com.boot.newzips.like;

import java.util.List;
import java.util.Map;

import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.WishDTO;


public interface LikeUserService {

	public void insertWish(Map<String, Object> params) throws Exception;
	
	public List<ListAllDTO> selectWish(String userId) throws Exception;
	
	public void deleteWish(Map<String, Object> params) throws Exception;
}
