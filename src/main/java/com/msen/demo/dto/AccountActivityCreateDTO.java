package com.msen.demo.dto;

import java.time.Instant;

import javax.validation.constraints.Min;

import com.msen.demo.model.MoneyProcess;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountActivityCreateDTO {

	private Long customerId;
	private Instant processDate;
	@Min(value = 0, message = "Ücret Sıfırdan Küçük Olamaz")
	private Double price;
	private MoneyProcess process;
}
