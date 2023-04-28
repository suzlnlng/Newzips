package com.boot.newzips.like;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.WishDTO;

@Mapper
public interface LikeUserMapper {
	
	public void insertWish(Map<String, Object> params) throws Exception;
	
	public List<ListAllDTO> selectWish(String userId) throws Exception;
	
	public void deleteWish(Map<String, Object> params) throws Exception;

}
