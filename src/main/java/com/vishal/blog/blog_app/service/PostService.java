package com.vishal.blog.blog_app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.vishal.blog.blog_app.dto.SavePostDTO;
import com.vishal.blog.blog_app.entity.Posts;
import com.vishal.blog.blog_app.entity.Users;
import com.vishal.blog.blog_app.filter.JwtFilter;
import com.vishal.blog.blog_app.repository.PostRepository;
import com.vishal.blog.blog_app.repository.UserRepository;

import io.jsonwebtoken.Header;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	
	public ResponseEntity<String> savePosts(SavePostDTO postDto){
		Posts post=new Posts();
		post.setPostBody(postDto.getBody());
		post.setPostTitle(postDto.getTitle());
        post.setCreatedOn(new Date(System.currentTimeMillis()));
		post.setPublishBy(userRepo.findByEmail(JwtFilter.username).get());
		postRepo.save(post);
		return new ResponseEntity<String>("Post Created",HttpStatus.CREATED);
		
	}
	
	public Posts getPost(int id) throws Exception {
		Posts post=postRepo.findById(id).get();
		Users user=post.getPublishBy();
		if(user.getEmail().equals(JwtFilter.username)) {
			return post;
		}
		else {
			throw new Exception("Not Eligible");
		}
	}
	
	
	
}
