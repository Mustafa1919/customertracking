package com.msen.demo.dto;

import java.time.Instant;

import com.msen.demo.utils.AccountLists;

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
public class BaseAccountActivityDTO {
	
	private String customerName;
	private String customerLastName;
	private AccountLists<Double, Instant> listOfActivities;
	
	
}
