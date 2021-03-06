package com.msen.demo.dto;

import java.time.Instant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(parent = BaseCustomerDTO.class, value = "Müşteri Nesnesi", description = "Create Model DTO")
public class CustomerCreateDTO extends BaseCustomerDTO{
	
	public CustomerCreateDTO(String customerName, String customerLastName, Double customerTotalBalance,
			Instant dateOfCustomerCreateAccount, String customerPhoneNumber) {
		super(customerName, customerLastName, customerTotalBalance);
		this.dateOfCustomerCreateAccount = dateOfCustomerCreateAccount;
		this.customerPhoneNumber = customerPhoneNumber;
	}
	@ApiModelProperty(value = "Hesap İşlem Tarihi")
	private Instant dateOfCustomerCreateAccount;
	@ApiModelProperty(value = "Müşteri Telefon Numarası")
	private String customerPhoneNumber;
	
}
