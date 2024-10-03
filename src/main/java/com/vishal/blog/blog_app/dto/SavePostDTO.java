package com.vishal.blog.blog_app.dto;

public class SavePostDTO {
	
	private String title;
	private String body;
	public SavePostDTO(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}
	public SavePostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	

}
