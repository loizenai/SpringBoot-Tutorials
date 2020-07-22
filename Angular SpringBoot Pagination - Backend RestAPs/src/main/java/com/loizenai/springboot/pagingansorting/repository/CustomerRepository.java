package com.loizenai.springboot.pagingansorting.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loizenai.springboot.pagingansorting.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>{	
	Page<Customer> findAllBySalary (double salary, Pageable pageable);
	
	@Query("SELECT DISTINCT c.salary FROM Customer c")
	List<Double> findDistinctSalary();
}