package com.springproject.template.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.template.entity.LoginUser;
import com.springproject.template.service.LoginUserService;

@RestController
public class HomeController {
	
	@Autowired
	private LoginUserService loginDao;

	@GetMapping("/")
	public String home() {
		Optional<LoginUser> optional = loginDao.loadByEmail("moehtetoo@gmail.com");
		
		return "Api starting ..... ";
	}
	
}
