package com.msen.demo.service.concrete;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msen.demo.dto.AccountActivityCreateDTO;
import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.exceptions.ActivityNotFound;
import com.msen.demo.exceptions.CustomerNotFoundException;
import com.msen.demo.extensions.AccountExtensions;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;
import com.msen.demo.repository.AccountActivityRepository;
import com.msen.demo.repository.CustomerRepository;
import com.msen.demo.service.abstracts.IAccountActivitiesService;

@Service
public class AccountActivitiesService implements IAccountActivitiesService{
	
	private final AccountActivityRepository activityRepository;
	private final CustomerRepository customerRepository;
	private final AccountExtensions accountExtensions;

	public AccountActivitiesService(AccountActivityRepository activityRepository,
			CustomerRepository customerRepository, AccountExtensions accountExtensions) {
		super();
		this.activityRepository = activityRepository;
		this.customerRepository = customerRepository;
		this.accountExtensions = accountExtensions;
	}


	@Override
	public void createActivity(AccountActivityCreateDTO createDTO, Customer customer) {
		this.activityRepository.save( AccountActivity.builder()
				.customerId(customer)
				.price(createDTO.getPrice())
				.process(createDTO.getProcess())
				.timeOfActivity(createDTO.getProcessDate())
				.build() );
	}

	@Override
	public void delete(Long id) {
		this.activityRepository.delete(
				this.activityRepository.findById(id)
				.orElseThrow(() -> new ActivityNotFound("İşlem Bulunamadı")));
		
	}

	@Override
	public List<AccountActivityResponseDTO> getCustomerActivities(Long customerId) {
		
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı"));
		
		return this.accountExtensions.accountActivityListToResponseList(
				this.activityRepository.getCustomerActivities(customer));
		

	}


	@Override
	public void deleteCustomerAllActivities(Customer customer) {
		this.activityRepository.deleteCustomerAllActivities(customer);
		
	}


	@Override
	public List<AccountActivity> getAllActivities() {
		return this.activityRepository.findAll();
	}

}
