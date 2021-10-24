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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v1/customer")
@Api(value = "Müşteri API Dökümantasyonu")
public class CustomerController {
	
	private final ICustomerService service;
	
	public CustomerController(ICustomerService service) {
		this.service = service;
	}

	@GetMapping("/{customerId}")
	@ApiOperation(value = "Müşteri Kaydı Döner")
	public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable("customerId") @ApiParam(value = "Müşteri Numarası") Long customerId){
		return ResponseEntity.ok(service.findById(customerId));
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Tüm Müşteri Getirir")
	public ResponseEntity<List<CustomerResponseDTO>> getCustomerList(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	@ApiOperation(value = "Yeni Müşteri Ekleme")
	public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody @ApiParam(value = "Müşteri Nesnesi") CustomerCreateDTO createDTO){
		return ResponseEntity.ok(service.createCustomer(createDTO));
	}
	
	
	@DeleteMapping("/{customerId}")
	@ApiOperation(value = "Müşteri Silme")
	public ResponseEntity<Void> deleteCustomer(@Valid @PathVariable("customerId") @ApiParam(value = "Müşteri Numarası") Long customerId){
		service.deleteCustomer(customerId);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
