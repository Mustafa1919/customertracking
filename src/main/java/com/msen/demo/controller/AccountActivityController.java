package com.msen.demo.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msen.demo.business.abstracts.ICustomerBusiness;
import com.msen.demo.dto.AccountActivityResponseDTO;

@RestController
@RequestMapping("/v1/account")
public class AccountActivityController {

	private final ICustomerBusiness business;

	public AccountActivityController(ICustomerBusiness business) {
		super();
		this.business = business;
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<AccountActivityResponseDTO> getActivities(@Valid @RequestParam("customerId") Long customerId){
		return ResponseEntity.ok(business.getCustomerActivities(customerId));
	}
	
	
}
