package com.rogerio.saga.choreography.CustomerService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.CustomerService.enums.ReserveStatusEnum;
import com.rogerio.saga.choreography.CustomerService.exceptions.CustomerNotFoundException;
import com.rogerio.saga.choreography.CustomerService.models.Customer;
import com.rogerio.saga.choreography.CustomerService.models.dtos.ChargebackDTO;
import com.rogerio.saga.choreography.CustomerService.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public ReserveStatusEnum reserveCredit(String user, double total) {
		try {
			Optional<Customer> customerOpt = customerRepo.findByUsername(user);
			Customer customer = customerOpt.orElseThrow(CustomerNotFoundException::new);

			boolean hasEnoughCredit = total <= customer.getTotalAvailible();
			if (hasEnoughCredit) {
				customer.setTotalAvailible(customer.getTotalAvailible() - total);
				customerRepo.saveAndFlush(customer);
				return ReserveStatusEnum.RESERVED;
			} else {
				return ReserveStatusEnum.INSUFICIENT_CREDIT;
			}
		} catch (CustomerNotFoundException e) {
			return ReserveStatusEnum.CUSTOMER_NOT_FOUND;
		}

	}

	public void chargebackCustomerByUsername(ChargebackDTO chargeback) {
		Optional<Customer> customerOpt = customerRepo.findByUsername(chargeback.getUsername());

		customerOpt.ifPresent(customer -> {
			double currentTotal = customer.getTotalAvailible();
			customer.setTotalAvailible( currentTotal + chargeback.getTotal());
			customerRepo.saveAndFlush(customer);
		});
	}
}
