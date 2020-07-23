package com.loizenai.updownloadcsv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loizenai.updownloadcsv.model.Customer;


/**
 * 
 * Copyright by https://loizenai.com
 * @author loizenai.com
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
}