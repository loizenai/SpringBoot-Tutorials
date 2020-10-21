package com.loizenai.springboot.reactjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loizenai.springboot.reactjs.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstname(String firstname);
}