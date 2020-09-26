package com.loizenai.excelfile.repository;

import org.springframework.data.repository.CrudRepository;

import com.loizenai.excelfile.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
}