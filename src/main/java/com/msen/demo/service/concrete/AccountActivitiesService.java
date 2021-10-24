package com.msen.demo.service.concrete;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msen.demo.dto.AccountActivityCreateDTO;
import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.exceptions.ActivityNotFound;
import com.msen.demo.exceptions.CustomerNotFoundException;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;
import com.msen.demo.repository.AccountActivityRepository;
import com.msen.demo.repository.CustomerRepository;
import com.msen.demo.service.abstracts.IAccountActivitiesService;
import com.msen.demo.utils.AccountLists;

@Service
public class AccountActivitiesService implements IAccountActivitiesService{
	
	private final AccountActivityRepository activityRepository;
	private final CustomerRepository customerRepository;
	

	public AccountActivitiesService(AccountActivityRepository activityRepository,
			CustomerRepository customerRepository) {
		super();
		this.activityRepository = activityRepository;
		this.customerRepository = customerRepository;
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
	public AccountActivityResponseDTO getCustomerActivities(Long customerId) {
		
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı"));
		
		List<AccountActivity> activities = this.activityRepository.getCustomerActivities(customer);
		
		AccountLists<Double, Instant> listAccountLists = new AccountLists<>();
		for (AccountActivity accountActivity : activities) {
			listAccountLists.add(accountActivity.getPrice(), accountActivity.getTimeOfActivity());
		}
		
		return (AccountActivityResponseDTO) AccountActivityResponseDTO.builder()
				.customerLastName(customer.getLastName())
				.customerName(customer.getName())
				.listOfActivities(listAccountLists)
				.build();
		
	}


	@Override
	public void deleteCustomerAllActivities(Customer customer) {
		this.activityRepository.delete(null);
		
	}


	@Override
	public List<AccountActivity> getAllActivities() {
		return this.activityRepository.findAll();
	}

}
