package com.boot.newzips.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{

	private final AccountUserMapper accountUserMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		Optional<LoginForm> _user = null;
		try {
			_user = accountUserMapper.getUser(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("============================================================");
		System.out.println(_user.get().getUserId());

		if (!_user.isPresent()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		
		LoginForm user = _user.get();
		
		System.out.println(user.getUserRole());
		
		System.out.println(user.getUserPwd());
		
		System.out.println();
		System.out.println();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if("admin".equals(user.getUserRole().toString())) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
		}else {
			System.out.println("여기서 권한 추가");
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getRole()));
		}
		
		return new User(user.getUserId(), user.getUserPwd(), authorities);
		
	}

}
