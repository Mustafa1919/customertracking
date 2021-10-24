package com.msen.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.model.AccountActivity;
import com.msen.demo.service.abstracts.IAccountActivitiesService;

@RestController
@RequestMapping("/v1/account")
public class AccountActivityController {

	private final IAccountActivitiesService service;

	public AccountActivityController(IAccountActivitiesService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<AccountActivityResponseDTO> getActivities(@PathVariable("customerId") Long customerId){
		return ResponseEntity.ok(service.getCustomerActivities(customerId));
	}
	
	@GetMapping
	public ResponseEntity<List<AccountActivity>> getActivities(){
		return ResponseEntity.ok(service.getAllActivities());
	}
	
}
