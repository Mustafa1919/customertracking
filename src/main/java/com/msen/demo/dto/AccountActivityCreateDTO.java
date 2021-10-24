package com.msen.demo.dto;

import java.time.Instant;

import javax.validation.constraints.Min;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Hesap Oluşturma Modeli", description = "Create Model DTO")
public class AccountActivityCreateDTO {

	@ApiModelProperty(value = "Müşteri Numarası")
	private Long customerId;
	
	@ApiModelProperty(value = "Hesap İşlem Tarihi")
	private Instant processDate;
	
	@Min(value = 0, message = "Ücret Sıfırdan Küçük Olamaz")
	@ApiModelProperty(value = "İşlem Miktarı(TL)")
	private Double price;
	
	@ApiModelProperty(value = "Hesap İşlem Tipi")
	private MoneyProcess process;
}
