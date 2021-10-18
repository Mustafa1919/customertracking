package com.msen.demo.service.concrete;

import java.util.List;

import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;
import com.msen.demo.exceptions.CustomerBalanceUpdateException;
import com.msen.demo.exceptions.CustomerNotFoundException;
import com.msen.demo.extensions.CustomerExtensions;
import com.msen.demo.model.Customer;
import com.msen.demo.repositort.CustomerRepository;
import com.msen.demo.service.abstracts.ICustomerService;

public class CustomerService implements ICustomerService{
	
	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerResponseDTO findById(Long customerId) {
		return CustomerExtensions.customerToResponse(this.customerRepository.findById(customerId).
				orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı")));
	}

	@Override
	public List<CustomerResponseDTO> findAll() {
		return customerRepository.findAll().stream().map(customer -> CustomerExtensions.customerToResponse(customer)).toList();
	}

	@Override
	public CustomerResponseDTO updateBalance(CustomerUpdateBalanceDTO balanceDTO) {
		Customer customer = this.customerRepository.findById(balanceDTO.getCustomerId())
			.orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı"));
		
		if(customer.getName().equals(balanceDTO.getCustomerName()) &  
				customer.getLastName().equals(balanceDTO.getCustomerLastName()) )
			throw new CustomerBalanceUpdateException("Güncelleme Yapılacak Kişi Verileri Hatalı");	
		
		return CustomerExtensions.customerToResponse(this.customerRepository.save(customer));
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer = this.customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı"));
		
		this.customerRepository.delete(customer);
		
	}

	@Override
	public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
		return CustomerExtensions.customerToResponse(this.customerRepository.
				save(CustomerExtensions.customerCreateToCustomer(customerCreateDTO)));
	}

	@Override
	public CustomerResponseDTO findByNameAndLastName(String customerName, String customerLastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
