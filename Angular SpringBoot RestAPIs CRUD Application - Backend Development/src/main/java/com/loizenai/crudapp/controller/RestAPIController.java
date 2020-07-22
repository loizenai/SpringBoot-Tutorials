package com.loizenai.crudapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loizenai.crudapp.model.Customer;
import com.loizenai.crudapp.model.Message;
import com.loizenai.crudapp.service.CustomerServices;

/**
 * 
 * @Copyright by https://loizenai.com
 * @author https://loizenai.com
 * 			youtube: loizenai
 */

@RestController
@RequestMapping("/api/customer")
public class RestAPIController {
	
	@Autowired
	CustomerServices customerServices;
	
	@PostMapping("/create")
	public ResponseEntity<Message> addNewCustomer(@RequestBody Customer customer) {
		try {
			Customer returnedCustomer = customerServices.saveCustomer(customer);
			
			return new ResponseEntity<Message>(new Message("Upload Successfully!", 
											Arrays.asList(returnedCustomer), ""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail to post a new Customer!", 
											null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@GetMapping("/retrieveinfos")
	public ResponseEntity<Message> retrieveCustomerInfo() {
		
		try {
			List<Customer> customerInfos = customerServices.getCustomerInfos();
			
			return new ResponseEntity<Message>(new Message("Get Customers' Infos!", 
												customerInfos, ""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail!",
												null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<Message> getCustomerById(@PathVariable long id) {
		try {
			Optional<Customer> optCustomer = customerServices.getCustomerById(id);
			
			if(optCustomer.isPresent()) {
				return new ResponseEntity<Message>(new Message("Successfully! Retrieve a customer by id = " + id,
															Arrays.asList(optCustomer.get()), ""), HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(new Message("Failure -> NOT Found a customer by id = " + id,
						null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Message> updateCustomerById(@RequestBody Customer _customer, 
																	@PathVariable long id) {
		try {
			if(customerServices.checkExistedCustomer(id)) {
				Customer customer = customerServices.getCustomerById(id).get();
				
				//set new values for customer
				customer.setFirstname(_customer.getFirstname());
				customer.setLastname(_customer.getLastname());
				customer.setAddress(customer.getAddress());
				customer.setAge(_customer.getAge());
	
				// save the change to database
				customerServices.updateCustomer(customer);
				
				return new ResponseEntity<Message>(new Message("Successfully! Updated a Customer "
																		+ "with id = " + id,
																	Arrays.asList(customer), ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Customer "
						+ "with id = " + id,
					null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Message> deleteCustomerById(@PathVariable long id) {
		try {
			// checking the existed of a Customer with id
			if(customerServices.checkExistedCustomer(id)) {
				customerServices.deleteCustomerById(id);
				
				return new ResponseEntity<Message> (new Message("Successfully! Delete a Customer with id = " + id, 
														null, ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Customer "
														+ "with id = " + id, null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}