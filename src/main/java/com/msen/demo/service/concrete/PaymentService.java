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
	private final CustomerExtensions customerExtensions;

	public PaymentService(CustomerRepository customerRepository, AccountActivityRepository accountActivityRepository
			,CustomerExtensions customerExtensions) {
		this.customerRepository = customerRepository;
		this.accountActivityRepository = accountActivityRepository;
		this.customerExtensions = customerExtensions;
	}

	@Override
	public CustomerResponseDTO payPrice(CustomerUpdateBalanceDTO balanceDTO) {
		Customer customer = getCustomer(balanceDTO.getCustomerId());
		checkCustomerInformation(customer, balanceDTO);
		checkPriceBiggerThanZero(customer);
		checkPrice(customer, balanceDTO);
		
		customer.setBalance(customer.getBalance() - balanceDTO.getCustomerTotalBalance());
		
//		updateCustomerAndSaveActivities(customer, balanceDTO);
		
		return customerExtensions.customerToResponse(updateCustomerAndSaveActivities(customer, balanceDTO));
	}

	@Override
	public CustomerResponseDTO onCreditPrice(CustomerUpdateBalanceDTO balanceDTO) {
		Customer customer = getCustomer(balanceDTO.getCustomerId());
		checkCustomerInformation(customer, balanceDTO);
		checkPriceBiggerThanZero(customer);
		
		customer.setBalance(customer.getBalance() + balanceDTO.getCustomerTotalBalance());
		
//		updateCustomerAndSaveActivities(customer, balanceDTO);
		
		
		return customerExtensions.customerToResponse(updateCustomerAndSaveActivities(customer, balanceDTO));
	}
	
	
	private Customer getCustomer(final Long customerId) {
		return this.customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("M????teri Bulunamad??"));
	}
	
	private boolean checkCustomerInformation(final Customer customer, final CustomerUpdateBalanceDTO balanceDTO) {
		if( !(customer.getName().equals(balanceDTO.getCustomerName()) && 
				customer.getLastName().equals(balanceDTO.getCustomerLastName())) )
			throw new CustomerBalanceUpdateException("G??ncelleme Yap??lacak Ki??i Verileri Hatal??");
		return true;
	}
	
	private boolean checkPrice(final Customer customer, final CustomerUpdateBalanceDTO balanceDTO) {
		if(customer.getBalance() < balanceDTO.getCustomerTotalBalance())
			throw new PriceException("Hatal?? Bor?? Bilgisi Girdiniz.");
		return true;
	}
	
	private boolean checkPriceBiggerThanZero(final Customer customer) {
		if(customer.getBalance() <= 0)
			throw new PriceException("Borcunuz Bulunmamaktad??r.");
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
