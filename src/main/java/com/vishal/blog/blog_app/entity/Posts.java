package com.vishal.blog.blog_app.entity;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="post_title")
	@Nonnull
	private String postTitle;
	@Column(name="post_body")
	@Nonnull
	private String postBody;
	@Column(name="created_on")
	private Date createdOn;
	@Column(name="updated_on")
	private Date updateOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id" ,nullable=false)
	private Users publishedBy;
	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Posts(int id, String postTitle, String postBody, Date createdOn, Date updateOn, Users publishedBy) {
		super();
		this.id = id;
		this.postTitle = postTitle;
		this.postBody = postBody;
		this.createdOn = createdOn;
		this.updateOn = updateOn;
		this.publishedBy = publishedBy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostBody() {
		return postBody;
	}
	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdateOn() {
		return updateOn;
	}
	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}
	public Users getPublishBy() {
		return publishedBy;
	}
	public void setPublishBy(Users publishedBy) {
		this.publishedBy = publishedBy;
	}
	
	
	

}
