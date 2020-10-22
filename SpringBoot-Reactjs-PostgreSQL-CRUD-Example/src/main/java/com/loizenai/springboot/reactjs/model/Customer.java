package com.loizenai.springboot.reactjs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
	
    @Id
    @GeneratedValue
    private Long id;
	
	@NonNull
	private String firstname;
	@NonNull
	private String lastname;
	private String address;
	private int age;
	private String copyright = "https://loizenai.com";
		
	public String toString() {
		return String.format("id=%d, firstname='%s', lastname'%s', address=%s, age=%d", 
								id, firstname, lastname, address, age);	
	}
	
	public Customer(String firstname, String lastname, String address, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.age = age;
	}	
}