package com.msen.demo.service.concrete;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;
import com.msen.demo.exceptions.CustomerBalanceUpdateException;
import com.msen.demo.exceptions.CustomerNotFoundException;
import com.msen.demo.exceptions.PriceException;
import com.msen.demo.extensions.CustomerExtensions;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.model.Customer;
import com.msen.demo.repository.AccountActivityRepository;
import com.msen.demo.repository.CustomerRepository;
import com.msen.demo.service.abstracts.IPaymentService;

@Service
public class PaymentService implements IPaymentService{
	
	private final CustomerRepository customerRepository;
	private final AccountActivityRepository accountActivityRepository;

	public PaymentService(CustomerRepository customerRepository, AccountActivityRepository accountActivityRepository) {
		this.customerRepository = customerRepository;
		this.accountActivityRepository = accountActivityRepository;
	}

	@Override
	public CustomerResponseDTO payPrice(CustomerUpdateBalanceDTO balanceDTO) {
		Customer customer = getCustomer(balanceDTO.getCustomerId());
		checkCustomerInformation(customer, balanceDTO);
		checkPriceBiggerThanZero(customer);
		checkPrice(customer, balanceDTO);
		
		customer.setBalance(customer.getBalance() - balanceDTO.getCustomerTotalBalance());
		
		updateCustomerAndSaveActivities(customer, balanceDTO);
		
		return CustomerExtensions.customerToResponse(this.customerRepository.save(customer));
	}

	@Override
	public CustomerResponseDTO onCreditPrice(CustomerUpdateBalanceDTO balanceDTO) {
		Customer customer = getCustomer(balanceDTO.getCustomerId());
		checkCustomerInformation(customer, balanceDTO);
		checkPriceBiggerThanZero(customer);
		
		customer.setBalance(customer.getBalance() + balanceDTO.getCustomerTotalBalance());
		
		updateCustomerAndSaveActivities(customer, balanceDTO);
		
		
		return CustomerExtensions.customerToResponse(this.customerRepository.save(customer));
	}
	
	
	private Customer getCustomer(final Long customerId) {
		return this.customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Müşteri Bulunamadı"));
	}
	
	private boolean checkCustomerInformation(final Customer customer, final CustomerUpdateBalanceDTO balanceDTO) {
		if( !(customer.getName().equals(balanceDTO.getCustomerName()) && 
				customer.getLastName().equals(balanceDTO.getCustomerLastName())) )
			throw new CustomerBalanceUpdateException("Güncelleme Yapılacak Kişi Verileri Hatalı");
		return true;
	}
	
	private boolean checkPrice(final Customer customer, final CustomerUpdateBalanceDTO balanceDTO) {
		if(customer.getBalance() < balanceDTO.getCustomerTotalBalance())
			throw new PriceException("Hatalı Borç Bilgisi Girdiniz.");
		return true;
	}
	
	private boolean checkPriceBiggerThanZero(final Customer customer) {
		if(customer.getBalance() <= 0)
			throw new PriceException("Borcunuz Bulunmamaktadır.");
		return true;
	}
	
	
	private Customer updateCustomerAndSaveActivities(Customer customer, CustomerUpdateBalanceDTO balanceDTO) {
		this.accountActivityRepository.save(AccountActivity.builder()
				.customerId(customer)
				.price(balanceDTO.getCustomerTotalBalance())
				.process(balanceDTO.getProcess())
				.timeOfActivity(Instant.now())
				.build());
		
		return this.customerRepository.save(customer);
	}
	

}
