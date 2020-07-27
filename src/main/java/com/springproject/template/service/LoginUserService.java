package com.springproject.template.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.template.controller.LoginUserRepository;
import com.springproject.template.entity.LoginUser;

@Service
public class LoginUserService {
	
	@Autowired
	private LoginUserRepository repo;
	
	public Optional<LoginUser> loadByEmail(String email) {
		LoginUser loginUser = this.repo.findByEmail(email);
		return Optional.of(loginUser);
	}
}
