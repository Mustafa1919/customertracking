package com.msen.demo.service.concrete;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.exceptions.CustomerNotFoundException;
import com.msen.demo.extensions.CustomerExtensions;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;
import com.msen.demo.model.MoneyProcess;
import com.msen.demo.repository.AccountActivityRepository;
import com.msen.demo.repository.CustomerRepository;
import com.msen.demo.service.abstracts.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	private final CustomerRepository customerRepository;
	private final AccountActivityRepository accountActivityRepository;
	private final CustomerExtensions customerExtensions;
	
	
	public CustomerService(CustomerRepository customerRepository, AccountActivityRepository accountActivityRepository
			,CustomerExtensions customerExtensions) {
		this.customerRepository = customerRepository;
		this.accountActivityRepository = accountActivityRepository;
		this.customerExtensions = customerExtensions;
	}

	@Override
	public CustomerResponseDTO findById(Long customerId) {
		return customerExtensions.customerToResponse(this.customerRepository.findById(customerId).
				orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı")));
	}

	@Override
	public List<CustomerResponseDTO> findAll() {
		return customerExtensions.customerToResponseList(customerRepository.findAll()); //.stream().map(customer -> customerExtensions.customerToResponse(customer)).collect(Collectors.toList());
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer = this.customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı"));
		
		this.customerRepository.delete(customer);
		this.accountActivityRepository.deleteCustomerAllActivities(customer);
		
	}

	@Override
	public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
		Customer customer = this.customerRepository.
				save(customerExtensions.customerCreateToCustomer(customerCreateDTO));
		
		this.accountActivityRepository.save(AccountActivity.builder()
				.customerId(customer)
				.price(customerCreateDTO.getCustomerTotalBalance())
				.process(MoneyProcess.ON_CREDIT)
				.timeOfActivity(customerCreateDTO.getDateOfCustomerCreateAccount())
				.build());
		
		return customerExtensions.customerToResponse(customer);
	}

	@Override
	public CustomerResponseDTO findByNameAndLastName(String customerName, String customerLastName) {
		return customerExtensions.customerToResponse(this.customerRepository.findByNameAndLastName(customerName, customerLastName)
				.orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı")));
	}

}
