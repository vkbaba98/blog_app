package com.vishal.blog.blog_app.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vishal.blog.blog_app.entity.Users;

public class UserIntoDetails implements UserDetails {
	String username;
	String password;
	
	UserIntoDetails(Optional<Users> user){
		Users users=user.get();
		username=users.getEmail();
		password=users.getPassword();
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return password;
	}

}
