package com.msen.demo.dto;

import java.time.Instant;

import com.msen.demo.model.MoneyProcess;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(parent = BaseAccountActivityDTO.class, value = "Hesap Response Modeli", description = "Response Model DTO")
public class AccountActivityResponseDTO extends BaseAccountActivityDTO{

	

	public AccountActivityResponseDTO() {
		super();
	}

	public AccountActivityResponseDTO(String customerName, String customerLastName, Instant timeOfActivity,
			Double price, MoneyProcess process) {
		super(customerName, customerLastName, timeOfActivity, price, process);
	}

	public static class builder{
		
		private String customerName;
		private String customerLastName;
		private Instant timeOfActivity;
		private Double price;
		private MoneyProcess process;
		
		public builder customerName(String customerName) {
			this.customerName = customerName;
			return this;
		}
		
		public builder customerLastName(String customerLastName) {
			this.customerLastName = customerLastName;
			return this;
		}
		
		public builder listOfActivities(MoneyProcess process) {
			this.process = process;
			return this;
		}
		
		public builder timeOfActivity(Instant timeOfActivity) {
			this.timeOfActivity = timeOfActivity;
			return this;
		}
		
		public builder price(Double price) {
			this.price = price;
			return this;
		}
		
		public AccountActivityResponseDTO build() {
			return new AccountActivityResponseDTO(customerName, customerLastName, timeOfActivity, price, process);
		}
		
	}
}
