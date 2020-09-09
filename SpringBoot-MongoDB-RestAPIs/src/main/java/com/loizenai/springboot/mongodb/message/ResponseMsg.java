package com.loizenai.springboot.mongodb.message;

import java.util.ArrayList;
import java.util.List;

import com.loizenai.springboot.mongodb.model.Customer;

/**
 * Copyright by https://loizenai.com
 * Youtube loizenai
 * 
 */
public class ResponseMsg {
	private String message;
	private String url;
	private String error = "";
	private List<Customer> customers = new ArrayList<Customer>();
	
	public ResponseMsg(String message, String url, List<Customer> customers) {
		this.message = message;
		this.url = url;
		this.customers = customers;
	}
	
	public ResponseMsg(String message, String url, String error) {
		this.message = message;
		this.url = url;
		this.error = error;
	}
	
	public ResponseMsg(String message, String url) {
		this(message, url, List.of());
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public List<Customer> getCustomers() {
		return this.customers;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getError() {
		return this.error;
	}
}