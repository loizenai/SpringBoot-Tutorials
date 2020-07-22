package com.loizenai.crudapp.model;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private String message = "";
	private List<Customer> customers = new ArrayList<Customer>();
	private String error = "";
	
	public Message(String message, List<Customer> customers, String error) {
		this.message = message;
		this.customers = customers;
		this.error = error;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Customer> getCustomers(){
		return this.customers;
	}
	
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getError() {
		return this.error;
	}
}