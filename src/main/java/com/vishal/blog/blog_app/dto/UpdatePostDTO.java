package com.vishal.blog.blog_app.dto;

public class UpdatePostDTO {
	private String body;
	private String title;
	public UpdatePostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdatePostDTO(String body, String title) {
		super();
		this.body = body;
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
