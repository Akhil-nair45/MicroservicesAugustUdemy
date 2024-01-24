package com.example.demo.External.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "PAYMENT-MICROSERVICE/Payment")
public interface PaymentService {

	
	@PostMapping()
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest pr);
	
}
