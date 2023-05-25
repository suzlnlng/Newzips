package com.boot.newzips.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.newzips.dto.MemberDTO;

import lombok.RequiredArgsConstructor;


@Service
public class UserSecurityService implements UserDetailsService{

	@Autowired
	private AccountUserMapper accountUserMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		Optional<MemberDTO> _user = null;
		
		try {
			_user = accountUserMapper.getUserById(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!_user.isPresent()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		
		MemberDTO user = _user.get();
		
		//유저에 권한부여
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if("admin".equals(user.getUserRole().toString())) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
		}else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getRole()));
		}
		
		return new User(user.getUserId(), user.getUserPwd(), authorities);
		
	}

}
