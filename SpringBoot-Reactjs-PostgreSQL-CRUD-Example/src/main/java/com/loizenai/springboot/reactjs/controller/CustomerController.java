package com.loizenai.springboot.reactjs.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.loizenai.springboot.reactjs.model.Customer;
import com.loizenai.springboot.reactjs.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController {


    private final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private CustomerRepository customerRepository;
    
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @GetMapping("/customers")
    Collection<Customer> customers() {
        return customerRepository.findAll();
    }
    
    @GetMapping("/customer/{id}")
    ResponseEntity<?> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/customer")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
        log.info("Request to create customer: {}", customer);
        Customer result = customerRepository.save(customer);
        return ResponseEntity.created(new URI("/api/group/" + result.getId()))
                .body(result);
    }
    
    @PutMapping("/customer")
    ResponseEntity<Customer> updateGroup(@RequestBody Customer customer) {
        log.info("Request to update customer: {}", customer);
        Customer result = customerRepository.save(customer);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        log.info("Request to delete customer: {}", id);
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}