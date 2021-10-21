package com.msen.demo.service.abstracts;

import org.springframework.stereotype.Service;

import com.msen.demo.dto.AccountActivityCreateDTO;
import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.model.Customer;

@Service
public interface IAccountActivitiesService {

	void createActivity(AccountActivityCreateDTO createDTO, Customer customer);
	void delete(Long id);
	AccountActivityResponseDTO getCustomerActivities(Customer customerId);
	
}
