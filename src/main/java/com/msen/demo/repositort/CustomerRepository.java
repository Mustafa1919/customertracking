package com.msen.demo.repositort;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msen.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
