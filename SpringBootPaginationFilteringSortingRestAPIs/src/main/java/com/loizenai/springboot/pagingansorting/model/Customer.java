package com.loizenai.springboot.pagingansorting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column
	private double salary;
	
	@Column(columnDefinition = "varchar(255) default '@ https://loizenai.com'")
	private String copyrightBy;
	
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
	
	public double getSalary() {
		return this.salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setCopyrightBy(String copyrightBy) {
		this.copyrightBy = copyrightBy;
	}
	
	public String getCopyrightBy() {
		return this.copyrightBy;
	}
	
	protected Customer() {}
	
	public Customer(String firstname, String lastname, String address, int age, double salary) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.age = age;
		this.salary = salary;
		
		// default value
		this.copyrightBy = "@ https://loizenai.com";
	}
	
	public String toString() {
		return String.format("id=%d, firstname='%s', lastname'%s', address=%s, age=%d, salary=%d, copyrightBy=%s",  
								id, firstname, lastname, address, age, salary, copyrightBy);	
	}
}