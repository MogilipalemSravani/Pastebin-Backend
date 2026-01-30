package com.example.DTO;

public class CreatePasteResponse {
	private String id;
	private String url;


	public CreatePasteResponse(String id, String url) {
	this.id = id;
	this.url = url;
	}


	public String getId() {
	return id;
	}


	public String getUrl() {
	return url;
}
}
