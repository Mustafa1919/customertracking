package com.msen.demo.dto;


public class CustomerResponseDTO extends BaseCustomerDTO{
	
	private Long customerId;
	
	
	
	public CustomerResponseDTO() {	}
	
	
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
