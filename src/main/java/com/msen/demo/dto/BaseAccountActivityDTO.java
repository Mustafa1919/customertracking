package com.msen.demo.dto;

import java.time.Instant;

import com.msen.demo.model.MoneyProcess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Hesap Base Modeli", description = "Base Model DTO")
public class BaseAccountActivityDTO {
	
	@ApiModelProperty(value = "Müsteri Adı")
	private String customerName;
	
	@ApiModelProperty(value = "Müsteri Soyadı")
	private String customerLastName;
	
	@ApiModelProperty(value = "Hesap İşlem Tarihi")
	private Instant timeOfActivity;
	
	@ApiModelProperty(value = "İşlem Miktarı(TL)")
	private Double price;

	@ApiModelProperty(value = "Hesap İşlem Tipi")
	private MoneyProcess process;
	
}
