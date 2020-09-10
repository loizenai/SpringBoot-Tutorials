package com.loizenai.springboot.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.loizenai.springboot.mongodb.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
}