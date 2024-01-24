package com.example.demo.External.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-MICROSERVICE/Product")
public interface ProductService {

	
	@PutMapping("reduceQuantity/{productId}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable long productId,@RequestParam long quantity);
}
