package com.msen.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(parent = BaseCustomerDTO.class, value = "Müsteri Response Modeli", description = "Response Model DTO")
public class CustomerResponseDTO extends BaseCustomerDTO{
	
	@ApiModelProperty(value = "Müşteri Numarası")
	private Long customerId;
	
	
	
	public CustomerResponseDTO() {	}
	
	@Override
	public boolean equals(Object obj) {
		CustomerResponseDTO tmpDTO = (CustomerResponseDTO) obj;
		return (this.getCustomerId().equals(tmpDTO.getCustomerId())
				&& this.getCustomerLastName().equals(tmpDTO.getCustomerLastName())
				&& this.getCustomerName().equals(tmpDTO.getCustomerName())
				&& this.getCustomerTotalBalance() == tmpDTO.getCustomerTotalBalance());
	}
	
	
	public CustomerResponseDTO(String customerName, String customerLastName, Double customerTotalBalance,
			Long customerId) {
		super(customerName, customerLastName, customerTotalBalance);
		this.customerId = customerId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public static class builder{
		
		private String customerName;
		private String customerLastName;
		private Double customerTotalBalance;
		private Long customerId;
		
		
		public builder customerName(String customerName) {
			this.customerName = customerName;
			return this;
			
		}
		
		public builder customerLastName(String customerLastName) {
			this.customerLastName = customerLastName;
			return this;
			
		}
		
		public builder customerTotalBalance(Double customerTotalBalance) {
			this.customerTotalBalance = customerTotalBalance;
			return this;
			
		}
		
		public builder customerId(Long customerId) {
			this.customerId = customerId;
			return this;
			
		}
		
		
		
		public CustomerResponseDTO build() {
			return new CustomerResponseDTO(this.customerName, this.customerLastName, this.customerTotalBalance, this.customerId);
		}
	}
	
}
