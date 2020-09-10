package com.loizenai.springboot.mongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loizenai.springboot.mongodb.exception.CustomException;
import com.loizenai.springboot.mongodb.model.Customer;
import com.loizenai.springboot.mongodb.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository repo;
	
	public Customer saveCustomer(Customer customer){
		return repo.save(customer);
	}
	
	public List<Customer> retrieveAllCustomers(){
		return repo.findAll();
	}
	
	public Customer updateCustomer(String id, Customer customer) throws CustomException {
		
		Optional<Customer> customerOpt = repo.findById(id);
		
		if(!customerOpt.isPresent()) {
			throw new CustomException("404", "Can not find a customer for updating with id = " + id);
		}
			
		Customer _customer = customerOpt.get();
		
		_customer.setFirstname(customer.getFirstname());
		_customer.setLastname(customer.getLastname());
		_customer.setAddress(customer.getAddress());
		_customer.setAge(customer.getAge());
		
		repo.save(_customer);
		
		return _customer;
	}
	
	public void deleteCustomerById(String id) {
		repo.deleteById(id);
	}
}