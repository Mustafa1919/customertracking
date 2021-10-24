package com.msen.demo.extensions;

import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.model.Customer;

public class CustomerExtensions {
	
	
	public static CustomerResponseDTO customerToResponse(Customer customer) {
		return new CustomerResponseDTO.builder()
				.customerId(customer.getId())
				.customerLastName(customer.getLastName())
				.customerName(customer.getName())
				.customerTotalBalance(customer.getBalance())
				.build();
	}
	
	public static Customer customerCreateToCustomer(CustomerCreateDTO createDTO) {
		return Customer.builder()
				.balance(createDTO.getCustomerTotalBalance())
				.createDate(createDTO.getDateOfCustomerCreateAccount())
				.lastName(createDTO.getCustomerLastName())
				.name(createDTO.getCustomerName())
				.phoneNumber(createDTO.getCustomerPhoneNumber())
				.build();
	}

}
