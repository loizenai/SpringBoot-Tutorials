package com.loizenai.springboot.pagination.mongodb.message;

import java.util.List;

import com.loizenai.springboot.pagination.mongodb.document.Customer;

public class Response {
	private List<Customer> customers;
	private int totalPages;
	private int pageNumber;
	private int pageSize;
	
	public Response(){}
	
	public Response(List<Customer> customers, int totalPages,
						int pageNumber, int pageSize) {
		this.customers  = customers;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public List<Customer> getCustomers() {
		return this.customers;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getTotalPages() {
		return this.totalPages;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getPageNumber() {
		return this.pageNumber;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageSize() {
		return this.pageSize;
	}
}