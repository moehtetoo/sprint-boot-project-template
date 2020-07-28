package com.springproject.template.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.template.entity.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, String>{
	Optional<LoginUser> findByEmail(String email);
}
