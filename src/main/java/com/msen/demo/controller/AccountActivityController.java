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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v1/account")
@Api(value = "Müşteri Hesap Detayları")
public class AccountActivityController {

	private final IAccountActivitiesService service;

	public AccountActivityController(IAccountActivitiesService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/{customerId}")
	@ApiOperation(value = "Müşteri Hesap Detaylarını Listeler")
	public ResponseEntity<List<AccountActivityResponseDTO>> getActivities(@PathVariable("customerId") @ApiParam(value = "Müşteri Numarası") Long customerId){
		return ResponseEntity.ok(service.getCustomerActivities(customerId));
	}
	
	@GetMapping
	@ApiOperation(hidden = true, value = "")
	public ResponseEntity<List<AccountActivity>> getActivities(){
		return ResponseEntity.ok(service.getAllActivities());
	}
	
}
