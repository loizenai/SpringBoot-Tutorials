package com.loizenai.springboot.pagingansorting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loizenai.springboot.pagingansorting.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>{	
	Slice<Customer> findAllBySalary (double salary, Pageable pageable);
	Page<Customer> findAllByAgeGreaterThan(int age, Pageable pageable);
}