package com.msen.demo.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO extends BaseCustomerDTO{

	private Instant dateOfCustomerCreateAccount;
	private String customerPhoneNumber;
	
}
