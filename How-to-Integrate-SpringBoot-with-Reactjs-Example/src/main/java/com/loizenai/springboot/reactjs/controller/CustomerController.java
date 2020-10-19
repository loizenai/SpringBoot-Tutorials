package com.loizenai.springboot.reactjs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loizenai.springboot.reactjs.model.Customer;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @GetMapping("/customer")
    Customer customer() {
    	return new Customer("Jack", "Smith", "374 William S Canning Blvd", 23);
    }    
}