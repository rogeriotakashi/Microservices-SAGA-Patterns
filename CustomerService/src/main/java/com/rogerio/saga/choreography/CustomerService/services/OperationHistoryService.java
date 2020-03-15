package com.rogerio.saga.choreography.CustomerService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.CustomerService.enums.OperationTypeEnum;
import com.rogerio.saga.choreography.CustomerService.models.OperationHistory;
import com.rogerio.saga.choreography.CustomerService.models.dtos.ChargebackDTO;
import com.rogerio.saga.choreography.CustomerService.repositories.OperationHistoryRepository;

@Service
public class OperationHistoryService {

	@Autowired
	OperationHistoryRepository repo;
	
	public void debtOperation(Long orderId, String username, double totalOperation ) {
		OperationHistory operation = new OperationHistory(orderId,username,totalOperation);
		operation.setOperationType(OperationTypeEnum.DEBIT.toString());
		
		repo.saveAndFlush(operation);
	}
	
	public void chargebackOperation(Long orderId) {
		Optional<OperationHistory> history = repo.findByOrderId(orderId);
		Optional<OperationHistory> chargebackOperationOpt = history.map((operation) -> {
			OperationHistory chargeback = new OperationHistory(operation.getId(), operation.getUsername(), operation.getTotalOperation());
			chargeback.setOperationType(OperationTypeEnum.CHARGEBACK.toString());
			return chargeback;
		});
		chargebackOperationOpt.ifPresent(chagebackOperation -> repo.saveAndFlush(chagebackOperation));
	}
	
	public ChargebackDTO getChargebackByOrderId(Long orderId) {
		Optional<OperationHistory> historyOpt = repo.findByOrderId(orderId);
		Optional<ChargebackDTO> chargebackOpt = historyOpt.map( (history) -> {
			return new ChargebackDTO(history.getUsername(), history.getTotalOperation());
		});
		
		return chargebackOpt.get();
	}
}
