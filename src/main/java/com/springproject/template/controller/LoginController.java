package com.springproject.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.template.dto.LoginDto;
import com.springproject.template.dto.LoginResultDto;
import com.springproject.template.service.LoginUserService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginUserService service;
	
	@GetMapping("/students")
	public String studentsList() {
		return "Student list";
	}

	@PostMapping("/login")
	public LoginResultDto login(@RequestBody LoginDto dto) {
		return service.login(dto.getEmail(), dto.getPassword());
	}
}
