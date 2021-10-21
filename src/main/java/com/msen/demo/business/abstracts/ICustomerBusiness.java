package com.msen.demo.business.abstracts;

import org.springframework.stereotype.Component;

import com.msen.demo.dto.AccountActivityResponseDTO;
import com.msen.demo.service.abstracts.IAccountActivitiesService;
import com.msen.demo.service.abstracts.ICustomerService;

@Component
public interface ICustomerBusiness extends ICustomerService, IAccountActivitiesService {
	AccountActivityResponseDTO getCustomerActivities(Long customerId);
	
}
