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

import com.msen.demo.TestHelper;
import com.msen.demo.dto.AccountActivityCreateDTO;
import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.exceptions.ActivityNotFound;
import com.msen.demo.exceptions.CustomerNotFoundException;
import com.msen.demo.extensions.AccountExtensions;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;
import com.msen.demo.model.MoneyProcess;
import com.msen.demo.repository.AccountActivityRepository;
import com.msen.demo.repository.CustomerRepository;
import com.msen.demo.service.abstracts.IAccountActivitiesService;
import com.msen.demo.service.concrete.AccountActivitiesService;

class AccountActivitiesServiceTest extends TestHelper{
	
	private AccountActivityRepository activityRepository;
	private CustomerRepository customerRepository;
	private AccountExtensions accountExtensions;
	
	private IAccountActivitiesService accountActivitiesService;

	@BeforeEach
	void setUp() throws Exception {
		
		activityRepository = mock(AccountActivityRepository.class);
		customerRepository = mock(CustomerRepository.class);
		accountExtensions = mock(AccountExtensions.class);
		
		accountActivitiesService = new AccountActivitiesService(activityRepository, customerRepository, accountExtensions);
	}

	@Test
	void testCreateActivity_whenItShouldSuccessfully() {
		
		AccountActivityCreateDTO createDTO = createAccountActivityCreateDTO(MoneyProcess.ON_CREDIT);
		Customer customer = createCustomer();
		AccountActivity activity = createAccountActivity(customer, createDTO);
		
		when(activityRepository.save(activity)).thenReturn(activity);
		
		accountActivitiesService.createActivity(createDTO, customer);
		
		
		verify(activityRepository).save(activity);
	}
	
	
	@Test
	void testDelete_whenItShouldSuccessfully() {
		
		AccountActivityCreateDTO createDTO = createAccountActivityCreateDTO(MoneyProcess.ON_CREDIT);
		Customer customer = createCustomer();
		AccountActivity activity = createAccountActivity(customer, createDTO);
		
		when(activityRepository.findById(customerId)).thenReturn(Optional.of(activity));
		
		accountActivitiesService.delete(customerId);
		
		verify(activityRepository).delete(activity);
		verify(activityRepository).findById(customerId);
	}
	
	@Test
	void testDelete_whenItShouldAccountDoesNotExist_ReturnActivityNotFoundException() {
		
		when(activityRepository.findById(customerId)).thenReturn(Optional.empty());
		
		assertThrows(ActivityNotFound.class, 
				() -> accountActivitiesService.delete(customerId));
		
		verify(activityRepository).findById(customerId);
		verifyNoMoreInteractions(activityRepository);
	}
	
	@Test
	void testGetCustomerActivities_whenItShouldReturnResponseDTOList() {
		
		Customer customer = createCustomer();
		List<AccountActivity> accountList = createAccountActivityList();
		List<AccountActivityResponseDTO> responseList = createAccountActivityResponseDTOList();
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		when(activityRepository.getCustomerActivities(customer)).thenReturn(accountList);
		when(accountExtensions.accountActivityListToResponseList(accountList)).thenReturn(responseList);
		
		
		List<AccountActivityResponseDTO> resultList = accountActivitiesService.getCustomerActivities(customerId);
		
		assertEquals(responseList, resultList);
		
		verify(customerRepository).findById(customerId);
		verify(activityRepository).getCustomerActivities(customer);
		verify(accountExtensions).accountActivityListToResponseList(accountList);
	}
	
	@Test
	void testGetCustomerActivities_whenItShouldCustomerDoesNotExist_ReturnCustomerNotFoundException() {
		
		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
		
		assertThrows(CustomerNotFoundException.class, 
				() -> accountActivitiesService.getCustomerActivities(customerId));
		
		verify(customerRepository).findById(customerId);
		verifyNoInteractions(accountExtensions);
		verifyNoInteractions(activityRepository);
	}
	
	@Test
	void testDeleteCustomerAllActivities_whenItShouldSuccessfully() {
		
		Customer customer = createCustomer();
		
		accountActivitiesService.deleteCustomerAllActivities(customer);
		
		verify(activityRepository).deleteCustomerAllActivities(customer);

	}
	
	@Test
	void testGetAllActivities_whenItShouldReturnAccountActivitiesList() {
		
		List<AccountActivity> accountList = createAccountActivityList();
		
		when(activityRepository.findAll()).thenReturn(accountList);
		
		List<AccountActivity> resultList = accountActivitiesService.getAllActivities();
		
		assertEquals(accountList, resultList);
		
		verify(activityRepository).findAll();

	}

}
