package com.msen.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.msen.demo.dto.AccountActivityCreateDTO;
import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;
import com.msen.demo.model.MoneyProcess;

public class TestHelper {
	
	public static final Long customerId = 100L;
	public static final String name = "testName";
	public static final String lastName = "testLastName";
	public static final String phoneNumber = "5555";
	public static final Double price = 12.0;
	
	
	public CustomerResponseDTO createCustomerResponseDTO(Customer customerOptional) {
		return new CustomerResponseDTO.builder()
			.customerId(customerOptional.getId())
			.customerLastName(customerOptional.getLastName())
			.customerName(customerOptional.getName())
			.customerTotalBalance(customerOptional.getBalance())
			.build();
	}
	
	public Customer createCustomer() {
		return new Customer(customerId, name, lastName, null, price, phoneNumber, null);
	}
	
	public CustomerCreateDTO createCustomerCreateDTO() {
		return new CustomerCreateDTO(name, lastName, price, null, phoneNumber);
	}
	
	
	public AccountActivity createAccountActivity(Customer customer, CustomerCreateDTO createDTO, MoneyProcess process) {
		return AccountActivity.builder()
				.customerId(customer)
				.price(createDTO.getCustomerTotalBalance())
				.process(process)
				.timeOfActivity(createDTO.getDateOfCustomerCreateAccount())
				.build();
	}
	
	public AccountActivity createAccountActivity(Customer customer, AccountActivityCreateDTO createDTO) {
		return AccountActivity.builder()
				.customerId(customer)
				.price(createDTO.getPrice())
				.process(createDTO.getProcess())
				.timeOfActivity(createDTO.getProcessDate())
				.build();
	}
	
	public AccountActivity createAccountActivity(Customer customer, CustomerUpdateBalanceDTO createDTO) {
		return AccountActivity.builder()
				.customerId(customer)
				.price(createDTO.getCustomerTotalBalance())
				.process(createDTO.getProcess())
				.timeOfActivity(null)
				.build();
	}
	
	public List<Customer> createCustomerList(){
		return IntStream.range(0, 5).mapToObj(i -> 
			new Customer((long)i, i+name, i+lastName, null, price, phoneNumber+i, null))
				.collect(Collectors.toList());
	}
	
	public List<CustomerResponseDTO> createCustomerResponseDTOList(){
		return createCustomerList().stream()
				.map(c -> createCustomerResponseDTO(c))
				.collect(Collectors.toList());
	}
	
	
	public AccountActivityResponseDTO createAccountResponse(AccountActivity activity) {
		return new AccountActivityResponseDTO.builder()
				.customerLastName(activity.getCustomerId().getLastName())
				.customerName(activity.getCustomerId().getName())
				.listOfActivities(activity.getProcess())
				.price(activity.getPrice())
				.timeOfActivity(activity.getTimeOfActivity())
				.build();
	}
	
	public List<AccountActivity> createAccountActivityList(){
		return IntStream.range(0, 5).mapToObj(i -> new AccountActivity((long)i,
				new Customer((long)i, i+name, i+lastName, null, price, phoneNumber+i, null),
				null, price , MoneyProcess.ON_CREDIT)).collect(Collectors.toList());
	}
	
	public List<AccountActivityResponseDTO> createAccountActivityResponseDTOList(){
		return createAccountActivityList().stream().map(a -> createAccountResponse(a))
				.collect(Collectors.toList());
	}
	
	public AccountActivityCreateDTO createAccountActivityCreateDTO(MoneyProcess process) {
		return new AccountActivityCreateDTO(customerId,null,price,process);
	}
	
	public CustomerUpdateBalanceDTO createCustomerUpdateBalanceDTO(MoneyProcess process) {
		return new CustomerUpdateBalanceDTO(name, lastName, price, customerId, process);
	}

}
