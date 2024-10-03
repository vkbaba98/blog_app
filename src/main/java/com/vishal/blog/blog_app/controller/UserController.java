package com.vishal.blog.blog_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.blog.blog_app.dto.LoginDTO;
import com.vishal.blog.blog_app.dto.RegisterUserDTO;
import com.vishal.blog.blog_app.service.JwtService;
import com.vishal.blog.blog_app.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO userDTO){
		return userService.saveUser(userDTO);
	
	}
	@PostMapping("/login")
	public ResponseEntity<String> getLogin(@RequestBody LoginDTO login){
		
		
		return userService.getLogin(login);
	}
}
