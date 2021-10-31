package com.msen.demo.extensions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.model.Customer;

@Component
public class CustomerExtensions {
	
	
	public CustomerResponseDTO customerToResponse(Customer customer) {
		return new CustomerResponseDTO.builder()
				.customerId(customer.getId())
				.customerLastName(customer.getLastName())
				.customerName(customer.getName())
				.customerTotalBalance(customer.getBalance())
				.build();
	}
	
	public Customer customerCreateToCustomer(CustomerCreateDTO createDTO) {
		return Customer.builder()
				.balance(createDTO.getCustomerTotalBalance())
				.createDate(createDTO.getDateOfCustomerCreateAccount())
				.lastName(createDTO.getCustomerLastName())
				.name(createDTO.getCustomerName())
				.phoneNumber(createDTO.getCustomerPhoneNumber())
				.build();
	}
	
	public List<CustomerResponseDTO> customerToResponseList(List<Customer> customerList) {
		return customerList.stream()
				.map(c -> customerToResponse(c))
				.collect(Collectors.toList());
	}

}
