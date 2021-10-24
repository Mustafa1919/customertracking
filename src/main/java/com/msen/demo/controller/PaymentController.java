package com.msen.demo.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;
import com.msen.demo.service.abstracts.IPaymentService;

@RestController
@RequestMapping("v1/payment")
public class PaymentController {

	private final IPaymentService paymentService;

	public PaymentController(IPaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	@PostMapping("/pay")
	public ResponseEntity<CustomerResponseDTO> payPrice(@Valid @RequestBody CustomerUpdateBalanceDTO balanceDTO){
		return ResponseEntity.ok(paymentService.payPrice(balanceDTO));
	}
	
	@PostMapping("/oncredit")
	public ResponseEntity<CustomerResponseDTO> onCreditPrice(@Valid @RequestBody CustomerUpdateBalanceDTO balanceDTO){
		return ResponseEntity.ok(paymentService.onCreditPrice(balanceDTO));
	}
	
}
