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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("v1/payment")
@Api(value = "Müşteri Ödeme İşlemleri")
public class PaymentController {

	private final IPaymentService paymentService;

	public PaymentController(IPaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	@PostMapping("/pay")
	@ApiOperation(value = "Para Ödeme")
	public ResponseEntity<CustomerResponseDTO> payPrice(@Valid @RequestBody @ApiParam(value = "Müşteri Güncelleme Nesnesi") CustomerUpdateBalanceDTO balanceDTO){
		return ResponseEntity.ok(paymentService.payPrice(balanceDTO));
	}
	
	@PostMapping("/oncredit")
	@ApiOperation(value = "Ürün Alma")
	public ResponseEntity<CustomerResponseDTO> onCreditPrice(@Valid @RequestBody @ApiParam(value = "Müşteri Güncelleme Nesnesi") CustomerUpdateBalanceDTO balanceDTO){
		return ResponseEntity.ok(paymentService.onCreditPrice(balanceDTO));
	}
	
}
