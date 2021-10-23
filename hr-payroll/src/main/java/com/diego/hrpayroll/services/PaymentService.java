package com.diego.hrpayroll.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.diego.hrpayroll.entities.Payment;
import com.diego.hrpayroll.entities.Worker;
import com.diego.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	public Payment getPayment(long workerId, int days) {
		try {
			ResponseEntity<Worker> worker = workerFeignClient.findById(workerId);
			logger.info("info " + worker.getStatusCode());
			logger.info("worker " + worker.getStatusCode());
			return new Payment(worker.getBody().getName(), worker.getBody().getDailyIncome(), days);
		} catch (Exception e) {
			logger.error("error " + e);
			return null;
		}

	}

}
