package com.loizenai.springboot.mongodb.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;

	public CustomException(String status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return this.status;
	}

	public String getMessage() {
		return this.message;
	}
}