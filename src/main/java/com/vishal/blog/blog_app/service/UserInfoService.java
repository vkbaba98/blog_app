package com.vishal.blog.blog_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vishal.blog.blog_app.entity.Users;
import com.vishal.blog.blog_app.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Users> user=userRepo.findByEmail(username);
		
		if(user.isPresent()) {
			return new UserIntoDetails(user);
		}else {
			throw new UsernameNotFoundException("User not found");
		}
	

	}

}
