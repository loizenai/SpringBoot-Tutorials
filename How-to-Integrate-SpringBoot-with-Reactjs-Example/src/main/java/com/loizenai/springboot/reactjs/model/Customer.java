package com.loizenai.springboot.reactjs.model;

import lombok.Data;

@Data
public class Customer {
	private String firstname;
	private String lastname;
	private String address;
	private int age;
	private String copyright = "https://loizenai.com";
			
	public Customer(String firstname, String lastname, String address, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.age = age;
	}	
}