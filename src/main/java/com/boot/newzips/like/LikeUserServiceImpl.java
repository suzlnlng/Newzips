package com.boot.newzips.like;

import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.WishDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeUserServiceImpl implements LikeUserService{

	private final LikeUserMapper likeUserMapper; 
	
	@Override
	public void insertWish(Map<String, Object> params) throws Exception {
		likeUserMapper.insertWish(params);
	}

	@Override
	public List<ListAllDTO> selectWish(String userId) throws Exception {
		return likeUserMapper.selectWish(userId);
	}

	@Override
	public void deleteWish(Map<String, Object> params) throws Exception {
		likeUserMapper.deleteWish(params);
	}

}
