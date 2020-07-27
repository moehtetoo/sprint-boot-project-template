package com.springproject.template.security;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTokenProvider {
	
	private static long EXPIRATION_TIME = 12 * 60 * 60 * 1000;
	private static SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	private static String HEADER_STRING = "Authorization";
	private static String ISSUER = "template-app";
	
	public String generate(String username) {
		Jwts.builder()
		.setIssuedAt(new Date())
		.setIssuer(ISSUER)
		.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		.setSubject(username)
		.signWith(SECRET_KEY)
		.compact();
		return username;
		
	}
	
	public String username(HttpServletRequest req) {
		String reqToken = req.getHeader(HEADER_STRING);
		if(!StringUtils.isEmpty(reqToken)) {
			return Jwts.parserBuilder()
			.requireIssuer(ISSUER)
			.setSigningKey(SECRET_KEY)
			.build()
			.parseClaimsJws(HEADER_STRING)
			.getBody().getSubject();
		}
		return null;
	}
	
	
}
