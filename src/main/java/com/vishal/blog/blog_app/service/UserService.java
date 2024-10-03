package com.vishal.blog.blog_app.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vishal.blog.blog_app.dto.LoginDTO;
import com.vishal.blog.blog_app.dto.RegisterUserDTO;
import com.vishal.blog.blog_app.entity.Users;
import com.vishal.blog.blog_app.repository.UserRepository;

import io.jsonwebtoken.Header;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JwtService jwtService;
	@Autowired
	AuthenticationManager authenticationManager;
	
	public ResponseEntity<String> saveUser(RegisterUserDTO userDto){
		Users user=new Users();
		user.setName(userDto.getFullname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		userRepo.save(user);
		Users tempUser=userRepo.findByEmail(userDto.getEmail()).get();
		if(tempUser==null) {
			return new ResponseEntity<String>("Registration Failed!!", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<String>("Registration done", HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<String> getLogin(LoginDTO loginDTO) {
		System.out.println("Login service...");
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		if(authentication.isAuthenticated()) {
			HttpHeaders header=new HttpHeaders();
			header.set("Authorization", "Bearer "+jwtService.generateToken(loginDTO.getEmail()) );
			return  ResponseEntity.ok().headers(header).body("Login Successfully!!");
		}
		else {
			return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
		}
	}
	
	String encriptPassword(String pass){
		return new BCryptPasswordEncoder().encode(pass);
	}
	

}
