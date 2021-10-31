package com.msen.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import com.msen.demo.TestHelper;
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
import com.msen.demo.service.concrete.CustomerService;

@TestInstance(Lifecycle.PER_CLASS)
class CustomerServiceTest extends TestHelper{
	
	private CustomerRepository customerRepository;
	private AccountActivityRepository accountActivityRepository;
	private CustomerExtensions customerExtensions;
	
	private ICustomerService customerService;

	@BeforeEach
	void setUp() throws Exception {
		customerRepository = mock(CustomerRepository.class);
		accountActivityRepository = mock(AccountActivityRepository.class);
		customerExtensions = mock(CustomerExtensions.class);
		
		customerService = new CustomerService(customerRepository, accountActivityRepository, customerExtensions);
		
	}

	@Test
	void testFindById_itShouldReturnResponseDTO() {
		Customer customerOptional = createCustomer();
		CustomerResponseDTO customerResponseDTO = createCustomerResponseDTO(customerOptional);
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerOptional));
		when(customerExtensions.customerToResponse(customerOptional)).thenReturn(customerResponseDTO);
		
		CustomerResponseDTO responseDTO = customerService.findById(customerId);
		
		assertEquals(responseDTO, customerResponseDTO);
		verify(customerRepository).findById(customerId);
		verify(customerExtensions).customerToResponse(customerOptional);
	}
	
	@Test
	void testFindById_whenUserIdDoesNotExist_itShouldReturnCustomerNotFoundException() {

		Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class,() ->
			customerService.findById(customerId)
		);
	
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(customerExtensions);
		//verifyNoInteractions(converter); Çalışanları verify ile çalışmaması gerekenleride bu şekilde kontrol edilir.
		
		
	}
	
	@Test
	void testFindByNameAndLastName_itShouldReturnResponseDTO() {
		Customer customerOptional = createCustomer();
		CustomerResponseDTO customerResponseDTO = createCustomerResponseDTO(customerOptional);
		
		when(customerRepository.findByNameAndLastName(name,lastName)).thenReturn(Optional.of(customerOptional));
		when(customerExtensions.customerToResponse(customerOptional)).thenReturn(customerResponseDTO);
		
		CustomerResponseDTO responseDTO = customerService.findByNameAndLastName(name,lastName);
		
		assertEquals(responseDTO, customerResponseDTO);
		verify(customerRepository).findByNameAndLastName(name,lastName);
		verify(customerExtensions).customerToResponse(customerOptional);
	}

	@Test
	void testFindByNameAndLastName_whenUserIdDoesNotExist_itShouldReturnCustomerNotFoundException() {

		Mockito.when(customerRepository.findByNameAndLastName(name,lastName)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class,() ->
			customerService.findByNameAndLastName(name,lastName)
		);
	
		verify(customerRepository).findByNameAndLastName(name,lastName);
		verifyNoInteractions(customerExtensions);
		
		
	}

	@Test
	void testCreateCustomer_itShouldReturnResponseDTO() {
		Customer customerOptional = createCustomer();
		CustomerResponseDTO customerResponseDTO = createCustomerResponseDTO(customerOptional);
		CustomerCreateDTO createDTO = createCustomerCreateDTO();
		AccountActivity accountActivity = createAccountActivity(customerOptional, createDTO, MoneyProcess.ON_CREDIT);
		
		when(customerExtensions.customerCreateToCustomer(createDTO)).thenReturn(customerOptional);
		when(customerRepository.save(customerOptional)).thenReturn(customerOptional);
		when(accountActivityRepository.save(accountActivity)).thenReturn(accountActivity);
		when(customerExtensions.customerToResponse(customerOptional)).thenReturn(customerResponseDTO);
		
		CustomerResponseDTO responseDTO = customerService.createCustomer(createDTO);
		
		assertEquals(responseDTO, customerResponseDTO);
		verify(customerExtensions).customerCreateToCustomer(createDTO);
		verify(customerRepository).save(customerOptional);
		verify(accountActivityRepository).save(accountActivity);
		verify(customerExtensions).customerToResponse(customerOptional);
	}
	
	@Test
	void testDeleteCustomer_itShouldSuccesfull() {
		Customer customerOptional = createCustomer();
			
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerOptional));
		
		customerService.deleteCustomer(customerId);
		
		verify(customerRepository).findById(customerId);
		verify(customerRepository).delete(customerOptional);
		verify(accountActivityRepository).deleteCustomerAllActivities(customerOptional);
		
	}

	@Test
	void testDeleteCustomer_whenUserIdDoesNotExist_itShouldReturnCustomerNotFoundException() {

		Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class,() ->
			customerService.deleteCustomer(customerId)
		);
	
		verify(customerRepository).findById(customerId);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(accountActivityRepository);
		
		
	}
	
	@Test
	void testFindAll_itShouldReturnCustomerResponseDTOList() {
		List<Customer> customerList = createCustomerList();
		List<CustomerResponseDTO> responseList = createCustomerResponseDTOList();
		
		when(customerRepository.findAll()).thenReturn(customerList);
		when(customerExtensions.customerToResponseList(customerList)).thenReturn(responseList);
		
		List<CustomerResponseDTO> resultList = customerService.findAll();
		
		assertEquals(resultList, responseList);
		verify(customerRepository).findAll();
		verify(customerExtensions).customerToResponseList(customerList);

		
	}
}
