package com.vishal.blog.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.blog.blog_app.entity.Posts;

public interface PostRepository extends JpaRepository<Posts, Integer>{

}
