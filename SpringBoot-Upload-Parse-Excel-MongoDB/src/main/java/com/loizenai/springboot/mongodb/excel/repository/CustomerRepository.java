package com.loizenai.springboot.mongodb.excel.repository;

import org.springframework.data.repository.CrudRepository;

import com.loizenai.springboot.mongodb.excel.document.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
}