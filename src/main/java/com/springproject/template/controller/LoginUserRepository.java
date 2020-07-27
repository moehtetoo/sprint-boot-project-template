package com.springproject.template.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.template.entity.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, String>{
	
	LoginUser findByEmail(String email);
}
