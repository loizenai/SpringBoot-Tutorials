package com.loizenai.springboot.pagingansorting.restapis;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loizenai.springboot.pagingansorting.model.Customer;
import com.loizenai.springboot.pagingansorting.model.Response;
import com.loizenai.springboot.pagingansorting.repository.CustomerRepository;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class RestController {
		
	@Autowired
	CustomerRepository customerRepository;
		
	@GetMapping("/custom/pageable")
	public Response retrieveCustomer(@Param(value = "salary") int salary,
										@Param(value = "page") int page, 
										@Param(value = "size") int size,
										@Param(value = "agesorting") boolean agesorting,
										@Param(value = "desc") boolean desc){
		
		Page<Customer> customers = null;
		
		// not filtering with salary
		if(salary < 0) {
			// not sorting with age
			if(agesorting == false) {
				Pageable requestedPage = PageRequest.of(page, size);
				customers = customerRepository.findAll(requestedPage);	
			}else {
				// sorting with age and ascending
				if(false == desc) {
					Pageable requestedPage = PageRequest.of(page, size, Sort.by("age"));
					customers  = customerRepository.findAll(requestedPage);
				}
				// sorting with age and descending
				else {
					Pageable requestedPage = PageRequest.of(page, size, 
												Sort.by("age").descending());
					customers  = customerRepository.findAll(requestedPage);
				}
			}
		// Filtering with salary
		} else {			
			// not sorting with age
			if(agesorting == false) {
				Pageable requestedPage = PageRequest.of(page, size);
				// fitering request
				customers = customerRepository.findAllBySalary(salary, requestedPage);	
			}else {
				// sorting with age and ascending
				if(false == desc) {
					Pageable requestedPage = PageRequest.of(page, size, Sort.by("age"));
					// filtering request
					customers  = customerRepository.findAllBySalary(salary, requestedPage);
				}
				// sorting with age and descending
				else {
					Pageable requestedPage = PageRequest.of(page, size, 
												Sort.by("age").descending());
					// filtering request
					customers  = customerRepository.findAllBySalary(salary, requestedPage);
				}
			}
		}
		
		Response res = new Response(customers.getContent(), customers.getTotalPages(),
										customers.getNumber(), customers.getSize());
		
		return res;
	}
		
	@GetMapping("/salaries")
	public List<Double> getListSalaries() {
		try {
			return customerRepository.findDistinctSalary();
		}catch(Exception e) {
			// Log errors to user monitoring
			System.out.println(e);
			return Arrays.asList();
		}
	}
}