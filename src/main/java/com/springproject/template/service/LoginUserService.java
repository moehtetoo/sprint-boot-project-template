package com.springproject.template.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springproject.template.dto.LoginResultDto;
import com.springproject.template.entity.LoginUser;
import com.springproject.template.repo.LoginUserRepository;

@Service
public class LoginUserService {
	
	@Autowired
	private LoginUserRepository repo;
	
	@Autowired
	private AuthenticationManager auth;
	
	public LoginResultDto login(String email, String password) {
		LoginResultDto dto = new LoginResultDto();
		SecurityContextHolder.getContext().setAuthentication(null);
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
			Authentication authentication = auth.authenticate(token);
			if (authentication != null && authentication.isAuthenticated()) {
				LoginUser user = this.loadByEmail(email);
				dto.setEmail(user.getEmail())
				.setName(user.getUsername())
				.setSuccess(true);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				dto.setMessage("Authentication fails!");
			}
		} catch(EntityNotFoundException | InternalAuthenticationServiceException e) {
			dto.setMessage("Please check your email.");
		} catch(BadCredentialsException e) {
			dto.setMessage("Please check your password.");
		} 
		return dto;
	}
	
	public LoginUser loadByEmail(String email) {
		return this.repo.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException());
	}
}
