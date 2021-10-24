package com.msen.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;

@Repository
public interface AccountActivityRepository extends JpaRepository<AccountActivity, Long>{
	
	@Query("SELECT c FROM AccountActivity c WHERE c.customerId = :customerId")
	List<AccountActivity> getCustomerActivities(@Param(value = "customerId") Customer customerId);
	
	@Query("DELETE FROM AccountActivity c WHERE c.customerId = :customerId")
	void deleteCustomerAllActivities(@Param(value = "customerId") Customer customerId);
}
