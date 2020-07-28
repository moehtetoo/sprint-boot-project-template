package com.springproject.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springproject.template.entity.LoginUser;
import com.springproject.template.service.LoginUserService;

@Component
public class JWTUserDetailService implements UserDetailsService{
	
	@Autowired
	private LoginUserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUser user = service.loadByEmail(username);
		return User.builder()
				.username(username)
				.password(user.getPassword())
				.roles("admin")
				.build();
	}

}
