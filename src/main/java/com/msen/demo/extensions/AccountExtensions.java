package com.msen.demo.extensions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.model.AccountActivity;

@Component
public class AccountExtensions {
	
	public AccountActivityResponseDTO accountActivityToAccountResponse(AccountActivity activity) {
		return new AccountActivityResponseDTO.builder()
				.customerLastName(activity.getCustomerId().getLastName())
				.customerName(activity.getCustomerId().getName())
				.listOfActivities(activity.getProcess())
				.price(activity.getPrice())
				.timeOfActivity(activity.getTimeOfActivity())
				.build();
	}
	
	public List<AccountActivityResponseDTO> accountActivityListToResponseList(List<AccountActivity> acountActivityList) {
		return acountActivityList.stream()
				.map(c -> accountActivityToAccountResponse(c))
				.collect(Collectors.toList());
	}
	
}
