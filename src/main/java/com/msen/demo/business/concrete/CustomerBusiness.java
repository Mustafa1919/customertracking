package com.msen.demo.business.concrete;

import java.util.List;

import org.springframework.stereotype.Component;

import com.msen.demo.business.abstracts.ICustomerBusiness;
import com.msen.demo.dto.AccountActivityCreateDTO;
import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;
import com.msen.demo.model.Customer;
import com.msen.demo.service.abstracts.IAccountActivitiesService;
import com.msen.demo.service.abstracts.ICustomerService;

@Component
public class CustomerBusiness implements ICustomerBusiness{
	
	
	private final ICustomerService customerService;
	private final IAccountActivitiesService accountActivitiesService;
	
	

	public CustomerBusiness(ICustomerService customerService, IAccountActivitiesService accountActivitiesService) {
		super();
		this.customerService = customerService;
		this.accountActivitiesService = accountActivitiesService;
	}

	@Override
	public CustomerResponseDTO findById(Long customerId) {
		return this.customerService.findById(customerId);
	}

	@Override
	public List<CustomerResponseDTO> findAll() {
		return this.customerService.findAll();
	}

	@Override
	public CustomerResponseDTO updateBalance(CustomerUpdateBalanceDTO balanceDTO) {
		return this.customerService.updateBalance(balanceDTO);
	}

	@Override
	public void deleteCustomer(Long id) {
		this.customerService.deleteCustomer(id);
	}

	@Override
	public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerResponseDTO findByNameAndLastName(String customerName, String customerLastName) {
		return this.customerService.findByNameAndLastName(customerName, customerLastName);
	}

	@Override
	public void createActivity(AccountActivityCreateDTO createDTO, Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		this.accountActivitiesService.delete(id);
		
	}

	@Override
	public AccountActivityResponseDTO getCustomerActivities(Customer customerId) {
		return this.accountActivitiesService.getCustomerActivities(customerId);
	}

	@Override
	public AccountActivityResponseDTO getCustomerActivities(Long customerId) {
		return this.accountActivitiesService.getCustomerActivities(
				this.findByIdCustomer(customerId));
	}

	@Override
	public Customer findByIdCustomer(Long customerId) {
		throw new RuntimeException();
	}

}
