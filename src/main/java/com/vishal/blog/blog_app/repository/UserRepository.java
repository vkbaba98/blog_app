package com.vishal.blog.blog_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.blog.blog_app.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByEmail(String email);
	

}
