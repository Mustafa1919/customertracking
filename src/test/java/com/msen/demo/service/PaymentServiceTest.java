package com.msen.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.msen.demo.TestHelper;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;
import com.msen.demo.exceptions.CustomerBalanceUpdateException;
import com.msen.demo.exceptions.CustomerNotFoundException;
import com.msen.demo.exceptions.PriceException;
import com.msen.demo.extensions.CustomerExtensions;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;
import com.msen.demo.model.MoneyProcess;
import com.msen.demo.repository.AccountActivityRepository;
import com.msen.demo.repository.CustomerRepository;
import com.msen.demo.service.abstracts.IPaymentService;
import com.msen.demo.service.concrete.PaymentService;

class PaymentServiceTest extends TestHelper{
	
	private CustomerRepository customerRepository;
	private AccountActivityRepository accountActivityRepository;
	private CustomerExtensions customerExtensions;
	
	private IPaymentService paymentService;

	@BeforeEach
	void setUp() throws Exception {
		customerRepository = mock(CustomerRepository.class);
		accountActivityRepository = mock(AccountActivityRepository.class);
		customerExtensions = mock(CustomerExtensions.class);
		
		paymentService = new PaymentService(customerRepository, accountActivityRepository, customerExtensions);
	
	}

	@Test
	void testPayPrice_whenItShouldSuccessfully() {
		Customer customer = createCustomer();
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.DISCHARG);
		Customer customer2 = new Customer(customerId, name, lastName, null, customer.getBalance()-balanceDTO.getCustomerTotalBalance(), phoneNumber, null);
		CustomerResponseDTO responseDTO = new CustomerResponseDTO(name, lastName, customer2.getBalance(), customerId);
		AccountActivity accountActivity = createAccountActivity(customer2, balanceDTO);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		when(accountActivityRepository.save(accountActivity)).thenReturn(accountActivity);
		when(customerRepository.save(customer2)).thenReturn(customer2);
		when(customerExtensions.customerToResponse(customer2)).thenReturn(responseDTO);
		
		
		CustomerResponseDTO resultDTO = paymentService.payPrice(balanceDTO); 
		
		assertEquals(responseDTO, resultDTO);
		verify(customerRepository).findById(customerId);
		verify(accountActivityRepository).save(accountActivity);
		verify(customerRepository).save(customer2);
		verify(customerExtensions).customerToResponse(customer2);
		
	}
	
	@Test
	void testPayPrice_whenItShouldCustomerDoesNotExist_ReturnCustomerNotFoundException() {

		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.DISCHARG);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());		
		
		assertThrows(CustomerNotFoundException.class, () -> 
			paymentService.payPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testPayPrice_whenItShouldCustomerNameAndLastNameNotMatch_ReturnCustomerBalanceUpdateException() {

		Customer customer = new Customer(1L, "test", "test",null,price,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.DISCHARG);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(CustomerBalanceUpdateException.class, () -> 
			paymentService.payPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testPayPrice_whenItShouldCustomerNameMatchAndLastNameNotMatch_ReturnCustomerBalanceUpdateException() {

		Customer customer = new Customer(1L, name, "test",null,price,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.DISCHARG);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(CustomerBalanceUpdateException.class, () -> 
			paymentService.payPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testPayPrice_whenItShouldCustomerNameNotMatchAndLastNameMatch_ReturnCustomerBalanceUpdateException() {

		Customer customer = new Customer(1L, "test", lastName,null,price,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.DISCHARG);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(CustomerBalanceUpdateException.class, () -> 
			paymentService.payPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testPayPrice_whenItShouldCustomerPriceLowerThanZero_ReturnPriceException() {

		Customer customer = new Customer(1L, name, lastName,null,0.0,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.DISCHARG);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(PriceException.class, () -> 
			paymentService.payPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testPayPrice_whenItShouldCustomerPriceLowerThanUpdateDTOPrice_ReturnPriceException() {

		Customer customer = new Customer(1L, name, lastName,null,5.0,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.DISCHARG);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(PriceException.class, () -> 
			paymentService.payPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	//********
	
	@Test
	void testOnCreditPrice_whenItShouldSuccessfully() {
		Customer customer = createCustomer();
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.ON_CREDIT);
		Customer customer2 = new Customer(customerId, name, lastName, null, customer.getBalance()+balanceDTO.getCustomerTotalBalance(), phoneNumber, null);
		CustomerResponseDTO responseDTO = new CustomerResponseDTO(name, lastName, customer2.getBalance(), customerId);
		AccountActivity accountActivity = createAccountActivity(customer2, balanceDTO);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		when(accountActivityRepository.save(accountActivity)).thenReturn(accountActivity);
		when(customerRepository.save(customer2)).thenReturn(customer2);
		when(customerExtensions.customerToResponse(customer2)).thenReturn(responseDTO);
		
		
		CustomerResponseDTO resultDTO = paymentService.onCreditPrice(balanceDTO); 
		
		assertEquals(responseDTO, resultDTO);
		verify(customerRepository).findById(customerId);
		verify(accountActivityRepository).save(accountActivity);
		verify(customerRepository).save(customer2);
		verify(customerExtensions).customerToResponse(customer2);
		
	}
	
	@Test
	void testOnCreditPrice_whenItShouldCustomerDoesNotExist_ReturnCustomerNotFoundException() {

		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.ON_CREDIT);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());		
		
		assertThrows(CustomerNotFoundException.class, () -> 
			paymentService.onCreditPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testOnCreditPrice_whenItShouldCustomerNameAndLastNameNotMatch_ReturnCustomerBalanceUpdateException() {

		Customer customer = new Customer(1L, "test", "test",null,price,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.ON_CREDIT);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(CustomerBalanceUpdateException.class, () -> 
			paymentService.onCreditPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testOnCreditPrice_whenItShouldCustomerNameMatchAndLastNameNotMatch_ReturnCustomerBalanceUpdateException() {

		Customer customer = new Customer(1L, name, "test",null,price,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.ON_CREDIT);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(CustomerBalanceUpdateException.class, () -> 
			paymentService.onCreditPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testOnCreditPrice_whenItShouldCustomerNameNotMatchAndLastNameMatch_ReturnCustomerBalanceUpdateException() {

		Customer customer = new Customer(1L, "test", lastName,null,price,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.ON_CREDIT);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(CustomerBalanceUpdateException.class, () -> 
			paymentService.onCreditPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}
	
	@Test
	void testOnCreditPrice_whenItShouldCustomerPriceLowerThanZero_ReturnPriceException() {

		Customer customer = new Customer(1L, name, lastName,null,0.0,phoneNumber,null);
		CustomerUpdateBalanceDTO balanceDTO = createCustomerUpdateBalanceDTO(MoneyProcess.ON_CREDIT);
		
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));		
		
		assertThrows(PriceException.class, () -> 
			paymentService.onCreditPrice(balanceDTO)); 
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountActivityRepository);
		verifyNoMoreInteractions(customerRepository);
		verifyNoInteractions(customerExtensions);
		
	}

}
