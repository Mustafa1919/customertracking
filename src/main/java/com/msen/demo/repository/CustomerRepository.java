package com.msen.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.msen.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query("Select c from Customer c WHERE c.name=:name and c.lastName=:lastName")
	Optional<Customer> findByNameAndLastName(@Param("name") String customerName,@Param("lastName") String customerLastName);
	//List<Customer> findByNameAndLastName(String name,String lastName);
}
