package com.msen.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msen.demo.dto.CustomerCreateDTO;
import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.service.abstracts.ICustomerService;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
	
	private final ICustomerService service;
	
	public CustomerController(ICustomerService service) {
		this.service = service;
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable("customerId") Long customerId){
		return ResponseEntity.ok(service.findById(customerId));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CustomerResponseDTO>> getCustomerList(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerCreateDTO createDTO){
		return ResponseEntity.ok(service.createCustomer(createDTO));
	}
	
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@Valid @PathVariable("customerId") Long customerId){
		service.deleteCustomer(customerId);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
