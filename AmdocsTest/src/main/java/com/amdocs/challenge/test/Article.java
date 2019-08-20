package com.amdocs.challenge.test;

public class Article {

	private static int generateId;
	private int Id;
	private String Title;
	private String Body;
	
	public Article(String title){
		Id = ++generateId;
		this.Title = title;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

}
