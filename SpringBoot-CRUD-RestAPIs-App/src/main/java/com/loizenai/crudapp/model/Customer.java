package com.loizenai.crudapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Copyright by https://loizenai.com
 * @author https://loizenai.com
 *	      youtube loizenai
 */
@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;

	@Column
	private String address;
	
	@Column
	private int age;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	protected Customer() {}
	
	public Customer(String firstname, String lastname, String address, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.age = age;
	}
	
	public String toString() {
		return String.format("id=%d, firstname='%s', lastname'%s', address=%s, age=%d", 
								id, firstname, lastname, address, age);	
	}
}