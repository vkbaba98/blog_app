package com.vishal.blog.blog_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.blog.blog_app.dto.SavePostDTO;
import com.vishal.blog.blog_app.entity.Posts;
import com.vishal.blog.blog_app.service.PostService;

import io.jsonwebtoken.Header;

@RestController
@RequestMapping("/post")
public class GlobalPostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/save")
	public ResponseEntity<String> savePost(@RequestBody SavePostDTO postDto){
		return postService.savePosts(postDto);
	}
	
	@GetMapping("/id")
	public Posts getPost(@PathVariable int id) throws Exception {
		return postService.getPost(id);
	}

}
