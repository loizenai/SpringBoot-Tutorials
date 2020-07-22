package com.loizenai.crudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loizenai.crudapp.model.Customer;

/**
 * @Copyright by https://loizenai.com
 * @author https://loizenai.com
 *	      youtube loizenai
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}