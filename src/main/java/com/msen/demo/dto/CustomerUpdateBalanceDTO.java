package com.msen.demo.dto;

import com.msen.demo.model.MoneyProcess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(parent = BaseCustomerDTO.class, value = "Müşteri Güncelleme Nesnesi", description = "Update Model DTO")
public class CustomerUpdateBalanceDTO extends BaseCustomerDTO{
	
	

	public CustomerUpdateBalanceDTO(String customerName, String customerLastName, Double customerTotalBalance,
			Long customerId, MoneyProcess process) {
		super(customerName, customerLastName, customerTotalBalance);
		this.customerId = customerId;
		this.process = process;
	}
	@ApiModelProperty(value = "Müşteri Numarası")
	private Long customerId;
	@ApiModelProperty(value = "İşlem Tipi")
	private MoneyProcess process;
}
