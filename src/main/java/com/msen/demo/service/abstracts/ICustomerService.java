package com.msen.demo.service.abstracts;

import java.util.List;

import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;

public interface ICustomerService {
		
	CustomerResponseDTO findById(Long customerId);
	List<CustomerResponseDTO> findAll();
	void deleteCustomer(Long id);
	CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO);
	CustomerResponseDTO findByNameAndLastName(String customerName, String customerLastName);

}
