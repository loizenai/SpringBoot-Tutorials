package com.loizenai.springboot.uploadcsv.mongodb.message;

/**
 * Copyright by https://loizenai.com
 * @author loizenai.com
 *
 */
public class Message {
	private String filename;
	private String message;
	private String status;
	
	public Message(String filename, String message, String status) {
		this.filename = filename;
		this.message = message;
		this.status = status;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return this.filename;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}