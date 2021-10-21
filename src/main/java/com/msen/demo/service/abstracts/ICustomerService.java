package com.msen.demo.service.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;
import com.msen.demo.model.Customer;

@Service
public interface ICustomerService {
		
	CustomerResponseDTO findById(Long customerId);
	Customer findByIdCustomer(Long customerId);
	List<CustomerResponseDTO> findAll();
	CustomerResponseDTO updateBalance(CustomerUpdateBalanceDTO balanceDTO);
	void deleteCustomer(Long id);
	CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO);
	CustomerResponseDTO findByNameAndLastName(String customerName, String customerLastName);

}
