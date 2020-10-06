package com.loizenai.springboot.uploadcsv.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.loizenai.springboot.uploadcsv.mongodb.document.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
}