package com.msen.demo.dto;

import com.msen.demo.model.MoneyProcess;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateBalanceDTO extends BaseCustomerDTO{

	private Long customerId;
	private MoneyProcess process;
}
