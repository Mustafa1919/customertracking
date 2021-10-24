package com.msen.demo.dto;

import javax.validation.constraints.Min;

public class BaseCustomerDTO {
	private String customerName;
	private String customerLastName;
	@Min(value = 0, message = "Ücret Sıfırdan Küçük Olamaz")
	private Double customerTotalBalance;
	
	public BaseCustomerDTO(String customerName, String customerLastName, Double customerTotalBalance) {
		this.customerName = customerName;
		this.customerLastName = customerLastName;
		this.customerTotalBalance = customerTotalBalance;
	}

	public BaseCustomerDTO() {
	}
	

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public Double getCustomerTotalBalance() {
		return customerTotalBalance;
	}

	public void setCustomerTotalBalance(Double customerTotalBalance) {
		this.customerTotalBalance = customerTotalBalance;
	}
	
	
	
	


	
}
