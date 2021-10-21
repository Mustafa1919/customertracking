package com.msen.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msen.demo.business.abstracts.ICustomerBusiness;
import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
	
	private final ICustomerBusiness business;
	
	public CustomerController(ICustomerBusiness business) {
		this.business = business;
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponseDTO> getCustomer(@Valid @RequestParam("customerId") Long customerId){
		return ResponseEntity.ok(business.findById(customerId));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CustomerResponseDTO>> getCustomerList(){
		return ResponseEntity.ok(business.findAll());
	}
	
	@PostMapping
	public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerCreateDTO createDTO){
		return ResponseEntity.ok(business.createCustomer(createDTO));
	}
	
	@PutMapping
	public ResponseEntity<CustomerResponseDTO> updateBalance(@Valid @RequestBody CustomerUpdateBalanceDTO balanceDTO){
		return ResponseEntity.ok(business.updateBalance(balanceDTO));
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@Valid @RequestParam("customerId") Long customerId){
		business.deleteCustomer(customerId);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
