package com.vishal.blog.blog_app.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterUserDTO {
	
	private String fullname;
	private String email;
	private String password;
	public RegisterUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterUserDTO(String fullname, String email, String password) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password =getPasswordEncrypt(password);
	}
	String getPasswordEncrypt(String password){
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}
