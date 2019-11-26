package com.spring.titlecharge.model;

public enum StatusTitle {
	PENDING("Pending"),
	RECIEVED("Recieved");
	
	private String description;
	
	StatusTitle(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
