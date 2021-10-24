package com.msen.demo.service.abstracts;

import com.msen.demo.dto.CustomerResponseDTO;
import com.msen.demo.dto.CustomerUpdateBalanceDTO;

public interface IPaymentService {
	
	CustomerResponseDTO payPrice(CustomerUpdateBalanceDTO balanceDTO);
	CustomerResponseDTO onCreditPrice(CustomerUpdateBalanceDTO balanceDTO);
	
}
