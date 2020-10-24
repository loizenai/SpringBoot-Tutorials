package com.loizenai.springboot.reactjs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.loizenai.springboot.reactjs.model.Customer;


public interface CustomerRepository extends MongoRepository<Customer, String>{
    Customer findByFirstname(String firstname);
}