package com.vishal.blog.blog_app.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.vishal.blog.blog_app.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	public static final String KEY ="demo_blog_application_using_user_post";
	public String generateToken(String username) {
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,username);
	}
	
	private String createToken(Map<String,Object> claims,String username) {
		return Jwts.builder()
				.claims(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+150000))
				.signWith(getSignKey())
				.compact();
	}

	
	private Key getSignKey() {
		// TODO Auto-generated method stub
		return Keys.hmacShaKeyFor(KEY.getBytes());
	}


	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
    private Date extractTime(String token) {
    	return extractClaims(token,Claims::getExpiration);
    }

	private <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
		// TODO Auto-generated method stub
		Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}


	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser()
				.verifyWith((SecretKey) getSignKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	public Boolean validateToken(String token ,String username) {
		String user=extractUsername(token);
		return username.equals(user);
	}
	private Boolean isTokenExpire(String token) {
		
		return extractTime(token).after(new Date(System.currentTimeMillis()));
	}

}
