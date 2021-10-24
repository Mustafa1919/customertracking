package com.msen.demo.extensions;

import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.model.AccountActivity;

public class AccountExtensions {
	
	public static AccountActivityResponseDTO accountActivityToAccountResponse(AccountActivity activity) {
		return new AccountActivityResponseDTO.builder()
				.customerLastName(activity.getCustomerId().getLastName())
				.customerName(activity.getCustomerId().getName())
				.listOfActivities(activity.getProcess())
				.price(activity.getPrice())
				.timeOfActivity(activity.getTimeOfActivity())
				.build();
	}
	
}
